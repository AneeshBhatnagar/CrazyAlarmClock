package com.example.crazyyalarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

	// ///////////////////////////////////////////////////////////////////
	// Constants & Data
	// ///////////////////////////////////////////////////////////////////
	// For logging:
	private static final String TAG = "DBAdapter";

	// DB Fields
	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;
	/*
	 * CHANGE 1:
	 */
	// TODO: Setup your fields here:
	//Fields Of Table 1:
	public static final String KEY_NAME = "name";
	public static final String KEY_TIME = "time";
	
	//Fields Of Table 2:
	public static final String KEY_PIMON = "monday_pintent";
	public static final String KEY_PITUE = "tuesday_pintent";
	public static final String KEY_PIWED = "wednesday_pintent";
	public static final String KEY_PITHU = "thursday_pintent";
	public static final String KEY_PIFRI = "friday_pintent";
	public static final String KEY_PISAT = "saturday_pintent";
	public static final String KEY_PISUN = "sunday_pintent";
	public static final String KEY_PIALL = "allday_pintent";
	

	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	//Columns Of Table 1:
	public static final int COL_NAME = 1;
	public static final int COL_TIME = 2;
	
	//Columns Of Table 2:
	public static final int COL_PIMON = 1 ;
	public static final int COL_PITUE = 2 ;
	public static final int COL_PIWED = 3 ;
	public static final int COL_PITHU = 4 ;
	public static final int COL_PIFRI = 5 ;
	public static final int COL_PISAT = 6 ;
	public static final int COL_PISUN = 7 ;
	public static final int COL_PIALL = 8 ;

	//Table 1:
	public static final String[] ALL_KEYS_OF_TABLE1 = new String[] { KEY_ROWID, KEY_NAME,
			KEY_TIME };
	
	//Table 2:
	public static final String[] ALL_KEYS_OF_TABLE2 = new String[] { KEY_ROWID, KEY_PIMON,
		 KEY_PITUE, KEY_PIWED, KEY_PITHU, KEY_PIFRI, KEY_PISAT, KEY_PISUN, KEY_PIALL,
	};
	
	// DB info: it's name, and the table we are using (just one).
	public static final String DATABASE_NAME = "MyDb";
	public static final String DATABASE_TABLE_MAIN = "mainTable";
	public static final String DATABASE_TABLE_ALARMS = "alarmsTable";
	
	// Track DB version if a new version of your app changes the format.
	public static final int DATABASE_VERSION = 2;
	
	// Creating Table 1 i.e MAIN_TABLE:
	private static final String DATABASE_CREATE_SQL_MAIN = "create table "
			+ DATABASE_TABLE_MAIN
			+ " ("
			+ KEY_ROWID
			+ " integer primary key autoincrement, "
			+ KEY_NAME + " text not null, " + KEY_TIME + " string not null "
			+ ");";
	// Creating Table 2 i.e ALARMS_TABLE:
	private static final String DATABASE_CREATE_SQL_ALARM = "create table "
			+ DATABASE_TABLE_ALARMS
			+ " ("
			+ KEY_ROWID
			+ " integer primary key autoincrement, "
			+ KEY_PIMON + " string, "
			+ KEY_PITUE + " string, "
			+ KEY_PIWED + " string, "
			+ KEY_PITHU + " string, "
			+ KEY_PIFRI + " string, "
			+ KEY_PISAT + " string, "
			+ KEY_PISUN + " string, "
			+ KEY_PIALL + " string "
			+ ");";
	
			/*
			 * CHANGE 2:
			 */
			// TODO: Place your fields here!
			// + KEY_{...} + " {type} not null"
			// - Key is the column name you created above.
			// - {type} is one of: text, integer, real, blob
			// (http://www.sqlite.org/datatype3.html)
			// - "not null" means it is a required field (must be given a
			// value).
			// NOTE: All must be comma separated (end of line!) Last one must
			// have NO comma!!
				
	// Context of application who uses us.
	private final Context context;
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;

	// ///////////////////////////////////////////////////////////////////
	// Public methods:
	// ///////////////////////////////////////////////////////////////////

	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}

	// Open the database connection.
	public DBAdapter open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}

	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}

	// Add a new set of values to the TABLE 1 MAIN_TABLE
	public long insertRow(String name, String time) {
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_TIME, time);
		// initialValues.put(KEY_FAVCOLOUR, favColour);

		// Insert it into the database.
		return db.insert(DATABASE_TABLE_MAIN, null, initialValues);
	}
	// Add a new set of values to the TABLE 2 MAIN_ALARMS
	public long insertPendingIntent(String pimon,
									String pitue,
									String piwed,
									String pithu,
									String pifri,
									String pisat,
									String pisun,
									String piall)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_PIMON, pimon);
		initialValues.put(KEY_PITUE, pitue);
		initialValues.put(KEY_PIWED, piwed);
		initialValues.put(KEY_PITHU, pithu);
		initialValues.put(KEY_PIFRI, pifri);
		initialValues.put(KEY_PISAT, pisat);
		initialValues.put(KEY_PISUN, pisun);
		initialValues.put(KEY_PIALL, piall);
		
		return db.insert(DATABASE_TABLE_ALARMS, null, initialValues);
		
	}

	// Delete a row from the TABLE 1 MAIN_TABLE, by rowId (primary key)
	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE_MAIN, where, null) != 0;
	}

	//Delete a row from the TABLE 2 ALARMS_TABLE, by rowId (primary key)
	public boolean deleteRowOfAlarmTable(long rowId){
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE_ALARMS,where,null) != 0; 
	}
	
	
	public void deleteAll() {
		Cursor c = getAllRows();
		Cursor d = getAllRowsOfAlarmTable();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		long rowId1 = d.getColumnIndexOrThrow(KEY_ROWID);
		
		if ((c.moveToFirst())&&(d.moveToFirst())) {
			do {
				deleteRow(c.getLong((int) rowId));
				deleteRow(d.getLong((int) rowId1));
			} while ((c.moveToNext())&&(d.moveToNext()));
		}
		c.close();
		d.close();
	}

	// Return all data in the TABEL 1 .
	public Cursor getAllRows() {
		String where = null;
		Cursor c = db.query(true, DATABASE_TABLE_MAIN, ALL_KEYS_OF_TABLE1, where, null, null,
				null, null, null);
		
		if (c != null) {
			c.moveToFirst();
		}
		return c ;
	}
	
	// Return all data in the TABLE 2.
		public Cursor getAllRowsOfAlarmTable() {
			String where = null;
			Cursor d = db.query(true, DATABASE_TABLE_ALARMS, ALL_KEYS_OF_TABLE2, where, null, null,
					null, null, null);
			if (d != null) {
				d.moveToFirst();
			}
			return d ;
		}


	// Get a specific row of TABLE 1 (by rowId)
	public Cursor getRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = db.query(true, DATABASE_TABLE_MAIN, ALL_KEYS_OF_TABLE1, where, null, null,
				null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	// Get a specific row of TABLE 1 (by rowId)
	public Cursor getRowFromAlarmTable(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor d = db.query(true, DATABASE_TABLE_ALARMS, ALL_KEYS_OF_TABLE2, where, null, null,
				null, null, null);
		if (d != null) {
			d.moveToFirst();
		}
		return d;
	}
	
	// Change an existing row to be equal to new data.
	public boolean updateRow(long rowId, String name, String time) {
		String where = KEY_ROWID + "=" + rowId;
		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_NAME, name);
		newValues.put(KEY_TIME, time);
		//newValues.put(KEY_FAVCOLOUR, favColour);

		// Insert it into the database.
		return db.update(DATABASE_TABLE_MAIN, newValues, where, null) != 0;
	}

	// ///////////////////////////////////////////////////////////////////
	// Private Helper Classes:
	// ///////////////////////////////////////////////////////////////////

	/**
	 * Private class which handles database creation and upgrading. Used to
	 * handle low-level database access.
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(DATABASE_CREATE_SQL_MAIN);
			_db.execSQL(DATABASE_CREATE_SQL_ALARM);
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version "
					+ oldVersion + " to " + newVersion
					+ ", which will destroy all old data!");

			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_MAIN);
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ALARMS);
			

			// Recreate new database:
			onCreate(_db);
		}
	}
}


























/*
 * import android.content.ContentValues; import android.content.Context; import
 * android.database.Cursor; import android.database.SQLException; import
 * android.database.sqlite.SQLiteDatabase; import
 * android.database.sqlite.SQLiteOpenHelper;
 * 
 * public class DatabaseAdapter{
 * 
 * DatabaseHelper helper; public DatabaseAdapter(Context context) { // TODO
 * Auto-generated constructor stub helper = new DatabaseHelper(context); String
 * key = helper.giveKey_id(); } public long insertdata(String name,String time)
 * { SQLiteDatabase db = helper.getWritableDatabase(); ContentValues content =
 * new ContentValues(); content.put(DatabaseHelper.NAME, name);
 * content.put(DatabaseHelper.TIME, time); long id =
 * db.insert(DatabaseHelper.TABLE_NAME, null, content); return id;
 * 
 * }
 * 
 * public String getAllData() { SQLiteDatabase db =
 * helper.getWritableDatabase(); String[] columns =
 * {DatabaseHelper.KEY_ID,DatabaseHelper.NAME,DatabaseHelper.TIME}; Cursor
 * cursor = db.query(DatabaseHelper.TABLE_NAME, columns,
 * null,null,null,null,null); StringBuffer buffer = new StringBuffer();
 * while(cursor.moveToNext()) { /*int index1=
 * cursor.getColumnIndex(DatabaseHelper.KEY_ID); int cid1 =
 * cursor.getInt(index1); String name = cursor.getString(DatabaseHelper.NAME);
 * String time = cursor.getString(DatabaseHelper.TIME);
 * 
 * int cid = cursor.getInt(0); String name = cursor.getString(1); String time =
 * cursor.getString(2); buffer.append(cid+" "+name+" "+time+"\n");
 * 
 * } return buffer.toString(); }
 * 
 * public void deleteData() {
 * 
 * }
 * 
 * 
 * static class DatabaseHelper extends SQLiteOpenHelper{ private static final
 * String NAME = "name"; private static final String DAYS = "days"; private
 * static final String TIME="time"; private static final String DATABASE_NAME =
 * "mydatabase"; private static final String TABLE_NAME = "mytable"; private
 * static final int VERSION = 10; private static final String KEY_ID = "_id";
 * private static final String CREATE_TABLE
 * ="CREATE TABLE "+TABLE_NAME+" ("+KEY_ID
 * +" INTEGER PRIMARY KEY AUTOINCREMENT, "
 * +NAME+" VARCHAR(100), "+DAYS+" VARCHAR(100), "
 * +TIME+" VARCHAR(20)); ";//"create table "
 * +TABLE_NAME+"("+KEY_ID+"integer primary key autoincrement,"
 * +NAME+"text not null,"+TIME+"text not null);"; //
 * "create table mytable(_id text not null,name text not null, time text not null);"
 * ; private static final String PENDING_INTENT ="pending_intent"; private
 * static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME; private
 * Context context;
 * 
 * public DatabaseHelper(Context context){//, String name,CursorFactory factory,
 * int version) { super(context, NAME, null, VERSION); this.context = context;
 * // TODO Auto-generated constructor stub }
 * 
 * @Override public void onCreate(SQLiteDatabase db) { // TODO Auto-generated
 * method stub try { db.execSQL(CREATE_TABLE);
 * Message.message(context,"ON_CREATE "); } catch(SQLException e) {
 * Message.message(context," "+e); //e.printStackTrace(); //Toast.makeText(null,
 * "exception occuring rohan", Toast.LENGTH_SHORT).show(); }/*
 * db.execSQL(TABLE_CREATE); }
 * 
 * }
 * 
 * @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int
 * newVersion) { // TODO Auto-generated method stub try {
 * db.execSQL(DROP_TABLE); Message.message(context,"On_Upgrade"); onCreate(db);
 * } catch (SQLException e) { // TODO Auto-generated catch block
 * Message.message(context," "+e); //e.printStackTrace(); } }
 * 
 * public String giveKey_id() { return KEY_ID; } } }
 */