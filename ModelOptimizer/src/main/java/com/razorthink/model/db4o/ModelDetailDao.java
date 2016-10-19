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
import com.razorthink.model.pojo.ModelDetail;
import com.razorthink.model.pojo.MutationDetail;

public class ModelDetailDao {

	private static ObjectContainer db = null;

	private static void initDb( String dbName )
	{
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().objectClass(MutationDetail.class.getName());
		db = Db4oEmbedded.openFile(config, dbName);

	}

	public static void insertToDB4O( ModelDetail modelDetail, String dbName )
	{
		if( db == null )
			initDb(dbName);

		db.store(modelDetail);
		db.commit();

	}

	public static List<ModelDetail> getAllModel( String dbName )
	{
		if( db == null )
			initDb(dbName);

		Query q = db.query();
		q.constrain(ModelDetail.class);

		ObjectSet<ModelDetail> result = q.execute();

		List<ModelDetail> results = new ArrayList<>();
		while( result.hasNext() )
		{
			results.add(result.next());
		}
		return results;
	}

	public static void printAllDBObject( String dbName )
	{
		System.out.println("DB : " + dbName + "\n===============================");

		if( db == null )
			initDb(dbName);

		com.db4o.query.Query q = db.query();
		q.constrain(ModelDetail.class);

		ObjectSet<ModelDetail> result = q.execute();

		ModelDetail.printHeader();
		while( result.hasNext() )
		{
			// Print Player
			ModelDetail p = result.next();
			p.print();
		}

	}

	public static void truncateOrDelete( String dbName )
	{
		File f = new File(Constant.TARGET_DIR + "/" + dbName);
		f.delete();
	}
}
