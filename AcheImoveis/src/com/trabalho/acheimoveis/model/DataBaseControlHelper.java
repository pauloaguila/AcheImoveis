package com.trabalho.acheimoveis.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.trabalho.acheimoveis.utils.Constants;

public class DataBaseControlHelper extends OrmLiteSqliteOpenHelper {

	// private Dao<Book, Integer> modelBookDAO = null;

	// private Dao<Verse, Integer> modelVerseDAO = null;

	// private Dao<Favorite, Integer> modelFavoriteDAO = null;

	public DataBaseControlHelper(Context context) {
		super(context, Constants.DATABASE_NAME, null,
				Constants.DATABASE_NEW_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource arg1) {
		// try {
		//
		// TableUtils.createTableIfNotExists(connectionSource, Book.class);
		// Log.i(Constants.TAG, "Table Book created");
		// Log.i(Constants.TAG, "Table Chapter created");
		// TableUtils.createTableIfNotExists(connectionSource, Verse.class);
		// Log.i(Constants.TAG, "Table Verse created");
		// Log.i(Constants.TAG, "List of table created");
		//
		// } catch(SQLiteException e) {
		// Log.e(Constants.TAG, "Can't crate database", e);
		// throw new RuntimeException(e);
		//
		// } catch(SQLException e) {
		// Log.e(Constants.TAG, "Can't crate database", e);
		// throw new RuntimeException(e);
		//
		// } catch(java.sql.SQLException e) {
		// e.printStackTrace();
		// }
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource arg1,
			int arg2, int arg3) {
		// try {
		//
		// Log.i(Book.class.getName(), "onUpgrade");
		// TableUtils.dropTable(connectionSource, Book.class, true);
		//
		// Log.i(Verse.class.getName(), "onUpgrade");
		// TableUtils.dropTable(connectionSource, Verse.class, true);
		//
		// Log.i(Constants.TAG, "List of table dropped");
		//
		// // after we drop the old databases, we create the new ones
		// onCreate(database, connectionSource);
		//
		// } catch(SQLException e) {
		//
		// Log.e(DataBaseControlHelper.class.getName(),
		// "Can't drop databases", e);
		// throw new RuntimeException(e);
		//
		// } catch(java.sql.SQLException e) {
		// Log.e(DataBaseControlHelper.class.getName(),
		// "Can't drop databases", e);
		// e.printStackTrace();
		// }
	}
	/*
	 * public Dao<Book, Integer> getModelBookDAO() { if (null == modelBookDAO) {
	 * try { modelBookDAO = getDao(Book.class); } catch(java.sql.SQLException e)
	 * { e.printStackTrace(); }
	 * 
	 * } return modelBookDAO; }
	 * 
	 * public Dao<Verse, Integer> getModelVerseDAO() { if (null ==
	 * modelVerseDAO) { try { modelVerseDAO = getDao(Verse.class); }
	 * catch(java.sql.SQLException e) { e.printStackTrace(); } } return
	 * modelVerseDAO; }
	 * 
	 * public Dao<Favorite, Integer> getModelFavoriteDAO() { if (null ==
	 * modelFavoriteDAO) { try { modelFavoriteDAO = getDao(Favorite.class); }
	 * catch(java.sql.SQLException e) { e.printStackTrace(); } } return
	 * modelFavoriteDAO; }
	 */
}
