package com.razorthink.model.db4o;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;
import com.razorthink.model.constant.Constant;
import com.razorthink.model.pojo.MutationDetail;

public class MutationDetailDao {

	private static ObjectContainer db = null;

	public static void insertToDB4O( MutationDetail mutationDetail, String dbName )
	{
		if( db == null )
		{
			initDb(dbName);
		}
		db.store(mutationDetail);
		db.commit();
	}

	private static void initDb( String dbName )
	{
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().objectClass(MutationDetail.class.getName());
		db = Db4oEmbedded.openFile(config, dbName);

	}

	public static List<MutationDetail> rankByAttribute( String dbName, String attributeName )
	{

		if( db == null )
			initDb(dbName);
		Query q = db.query();
		q.constrain(MutationDetail.class);
		q.descend(attributeName).orderDescending();

		ObjectSet<MutationDetail> result = q.execute();
		List<MutationDetail> sortedMutationDetails = new ArrayList<>();
		int rank = 1;
		while( result.hasNext() )
		{
			MutationDetail p = result.next();
			p.setRank(rank++);
			sortedMutationDetails.add(p);

		}

		truncateAndDelete(dbName);

		for( MutationDetail mutationDetail : sortedMutationDetails )
		{
			db.store(mutationDetail);
			db.commit();
		}
		return sortedMutationDetails;
	}

	public static void printAllDBObject( String dbmane )
	{
		System.out.println("DB : " + dbmane + "\n===============================");
		if( db == null )
			initDb(dbmane);

		Query q = db.query();
		q.constrain(MutationDetail.class);
		//			q.descend("accuracy").orderDescending();

		ObjectSet<MutationDetail> result = q.execute();
		MutationDetail.printHeader();
		while( result.hasNext() )
		{
			MutationDetail p = result.next();
			p.print();

		}

	}

	public static void truncateAndDelete( String dbName )
	{
		File f = new File(Constant.TARGET_DIR + "/" + dbName);
		if( f.exists() )
			f.delete();
		if( db == null )
			return;
		Query q = db.query();
		q.constrain(MutationDetail.class);

		ObjectSet<MutationDetail> result = q.execute();
		while( result.hasNext() )
		{
			MutationDetail p = result.next();
			db.delete(p);
		}

	}

}
