package com.example.breezer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class PrescriptionDatabaseActivity{
	// To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
	public PrescriptionDatabaseActivity(){}
	
	/* Inner class that defines the table contents 
	public static abstract class FeedEntry implements BaseColumns{
	public static final String TABLE_NAME = "prescription";
	public static final String COLUMN_NAME_ID = "username";
	public static final String COLUMN_NAME_PNAME = "prescriptionName";
	public static final String COLUMN_NAME_PSIZE = "prescriptionSize";
	public static final String COLUMN_NAME_PCOLOR = "prescriptionColor";
	public static final String COLUMN_NAME_PFREQUENCY = "prescriptionFrequency";
	public static final String COLUMN_NAME_PAMOUNT = "prescriptionAmount";
	public static final String COLUMN_NAME_PSTARTDATE = "prescriptionStartdate";
	public static final String COLUMN_NAME_PREMAINING = "prescriptionRemaining";
	private static final String TEXT_TYPE ="TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES = 
	"CREATE TABLE " + FeedEntry.TABLE_NAME + " (" + 
	FeedEntry._ID + " INTERGER PRIMARY KEY," +
	
	FeedEntry.COLUMN_NAME_PNAME + TEXT_TYPE + COMMA_SEP + 
	FeedEntry.COLUMN_NAME_PSIZE + TEXT_TYPE + COMMA_SEP +
	FeedEntry.COLUMN_NAME_PCOLOR + TEXT_TYPE + COMMA_SEP +
	FeedEntry.COLUMN_NAME_PFREQUENCY + TEXT_TYPE + COMMA_SEP +
	FeedEntry.COLUMN_NAME_PAMOUNT + TEXT_TYPE + COMMA_SEP +
	FeedEntry.COLUMN_NAME_PSTARTDATE + TEXT_TYPE + COMMA_SEP +
	FeedEntry.COLUMN_NAME_PREMAINING + TEXT_TYPE + COMMA_SEP + 
	//Any other options for the CREATE command
	")";
	
	private static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
	
	public class PrescriptionDatabase extends SQLiteOpenHelper {
	    // If you change the database schema, you must increment the database version.
	    public static final int DATABASE_VERSION = 1;
	    public static final String DATABASE_NAME = "FeedReader.db";

	    public PrescriptionDatabase(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }
	    public void onCreate(SQLiteDatabase db) {
	        db.execSQL(SQL_CREATE_ENTRIES);
	    }
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        // This database is only a cache for online data, so its upgrade policy is
	        // to simply to discard the data and start over
	        db.execSQL(SQL_DELETE_ENTRIES);
	        onCreate(db);
	    }
	    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        onUpgrade(db, oldVersion, newVersion);
	    }
	}
	
	//FeedReaderDbHelperActivity mDbHelper = new FeedReaderDbHelperActivity(getContext());
	
	//putting info into database
	//public long insert (String prescription, String nullColumnHack, ContentValues values){
	
	//Gets the data repository in write mode
	//SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
	//New map of values, where column names are the keys
	//ContentValues values = new ContentValues();
	//values.put(FeedEntry.COLUMN_NAME_ID, username);
	//values.put(FeedEntry.COLUMN_NAME_PNAME, prescriptionName);
	//values.put(FeedEntry.COLUMN_NAME_PSIZE, prescriptionSize);
	//values.put(FeedEntry.COLUMN_NAME_PCOLOR, prescriptionColor);
	//values.put(FeedEntry.COLUMN_NAME_PFREQUENCY, prescriptionFrequency);
	//values.put(FeedEntry.COLUMN_NAME_PAMOUNT, prescriptionAmount);
	//values.put(FeedEntry.COLUMN_NAME_PSTARTDATE, prescriptionStartdate);
	//values.put(FeedEntry.COLUMN_NAME_PREMAINING, prescriptionRemining);
	
	// New row, returning the primary key value of the new row
	//long newRowId;
	//newRowId = db.insert(
	         FeedEntry.TABLE_NAME,
	         FeedEntry.COLUMN_NAME_NULLABLE,
	         values);
	
		}
	
	public Cursor query (boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit){
	//SQLiteDatabase db = mDbHelper.getReadableDatabase();

	// Define a projection that specifies which columns from the database
	// you will actually use after this query.
	String[] projection = {
	    FeedEntry._ID,
	    FeedEntry.COLUMN_NAME_ID,
		FeedEntry.COLUMN_NAME_PNAME,
		FeedEntry.COLUMN_NAME_PSIZE,
		FeedEntry.COLUMN_NAME_PCOLOR,
		FeedEntry.COLUMN_NAME_PFREQUENCY,
		FeedEntry.COLUMN_NAME_PAMOUNT,
		FeedEntry.COLUMN_NAME_PSTARTDATE,
		FeedEntry.COLUMN_NAME_PREMAINING,

	    };

	// How you want the results sorted in the resulting Cursor
	String sortOrder =
	    FeedEntry.COLUMN_NAME_UPDATED + " DESC";

	Cursor c = db.query(
	    FeedEntry.TABLE_NAME,  // The table to query
	    projection,                               // The columns to return
	    selection,                                // The columns for the WHERE clause
	    selectionArgs,                            // The values for the WHERE clause
	    null,                                     // don't group the rows
	    null,                                     // don't filter by row groups
	    sortOrder                                 // The sort order
	    );
	}
	}*/
	
}
