package com.razorthink.model.db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.razorthink.model.pojo.ModelDetail;

public class ModelDetailDao {

	public static void insertToDB4O( ModelDetail modelDetail, String dbName )
	{
		ObjectContainer db = null;
		try
		{
			EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
			config.common().objectClass(modelDetail.getClass().getName());
			db = Db4oEmbedded.openFile(config, dbName);
			db.store(modelDetail);
			db.commit();

		}
		finally
		{
			if( db != null )
				db.close();
		}
	}

	public static void printAllDBObject( String dbmane )
	{
		System.out.println("DB : " + dbmane + "\n===============================");
		ObjectContainer db = null;
		try
		{
			EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
			config.common().objectClass(ModelDetail.class.getName());
			db = Db4oEmbedded.openFile(config, dbmane);

			com.db4o.query.Query q = db.query();
			q.constrain(ModelDetail.class);

			ObjectSet<ModelDetail> result = q.execute();

			ModelDetail.printHeader();
			System.out.println("\n");
			while( result.hasNext() )
			{
				// Print Player
				ModelDetail p = result.next();
				p.print();
			}
		}
		finally
		{
			if( db != null )
				db.close();
		}
	}
}
