package com.razorthink.model.db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;
import com.razorthink.model.pojo.MutationDetail;

public class MutationDetailDao {

	private static ObjectContainer db = null;

	public static void insertToDB4O(MutationDetail mutationDetail, String dbName) {
		if (db == null) {
			initDb(dbName);
		}
		db.store(mutationDetail);
		db.commit();
	}

	private static void initDb(String dbName) {
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().objectClass(MutationDetail.class.getName());
		db = Db4oEmbedded.openFile(config, dbName);

	}

	public static void printAllDBObject(String dbmane) {

		try {
			if (db == null)
				initDb(dbmane);

			Query q = db.query();
			q.constrain(MutationDetail.class);

			ObjectSet<MutationDetail> result = q.execute();
			MutationDetail.printHeader();
			int i = 0;
			while (result.hasNext()) {
				System.out.println(i++);
				MutationDetail p = result.next();
				p.print();

			}
		} finally {
			if (db != null)
				db.close();
		}
	}
}
