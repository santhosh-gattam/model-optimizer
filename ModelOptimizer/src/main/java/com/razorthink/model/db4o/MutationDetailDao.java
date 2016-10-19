package com.razorthink.model.db4o;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;
import com.db4o.query.Query;
import com.razorthink.model.constant.Constant;
import com.razorthink.model.pojo.ModelDetail;
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

	public static void rankByAttribute( String modelDB, String mutateDBName, String attributeName )
	{

		if( db == null )
			initDb(mutateDBName);

		List<ModelDetail> parentModels = ModelDetailDao.getAllModel(modelDB);

		if( parentModels.isEmpty() )
			return;
		// for every model, get corresponding mutations and apply rank based on accuary value
		for( final ModelDetail model : parentModels )
		{
			Query q = db.query();
			q.constrain(MutationDetail.class);
			q.descend(attributeName).orderDescending();

			// get all mutations for current model
			List<MutationDetail> result = db.query(new Predicate<MutationDetail>() {

				@Override
				public boolean match( MutationDetail mutationDetail )
				{

					return mutationDetail.getParentId().equals(model.getModelId());
				}
			});

			// assign rank to each mutation based on accuarcy.
			List<MutationDetail> sortedMutationDetails = new ArrayList<>();
			int rank = 1;
			for( MutationDetail mutationDetail : result )
			{
				mutationDetail.setRank(rank++);
				sortedMutationDetails.add(mutationDetail);

			}
			// delete previous elements and rewrite with updated rank
			deleteElements(mutateDBName, result);
			for( MutationDetail mutationDetail : sortedMutationDetails )
			{
				db.store(mutationDetail);
			}
			db.commit();
		}
	}

	private static void deleteElements( String mutateDBName, List<MutationDetail> result )
	{
		if( db == null )
			initDb(mutateDBName);

		for( MutationDetail mutationDetail : result )
			db.delete(mutationDetail);

	}

	public static void printAllDBObject( String dbmane )
	{
		System.out.println("DB : " + dbmane + "\n===============================");
		if( db == null )
			initDb(dbmane);

		Query q = db.query();
		q.constrain(MutationDetail.class);
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
			System.out.println("Deleted file " + f.getAbsolutePath() + " : " + f.delete());
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
