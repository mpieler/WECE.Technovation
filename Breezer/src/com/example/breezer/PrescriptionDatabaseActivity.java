package com.example.breezer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


//FeedReaderContract
public final class PrescriptionDatabaseActivity extends SQLiteOpenHelper{

	public static final String TABLE_NAME = "prescription";
	public static final String COLUMN_NAME_ID = "username";
	public static final String COLUMN_NAME_PNAME = "prescriptionName";
	public static final String COLUMN_NAME_PSIZE = "prescriptionSize";
	public static final String COLUMN_NAME_PCOLOR = "prescriptionColor";
	public static final String COLUMN_NAME_PFREQUENCY = "prescriptionFrequency";
	public static final String COLUMN_NAME_PAMOUNT = "prescriptionAmount";
	public static final String COLUMN_NAME_PSTARTDATE = "prescriptionStartdate";
	public static final String COLUMN_NAME_PREMAINING = "prescriptionRemaining";
	public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Prescription.db";
	private static final String TEXT_TYPE ="TEXT";
	private static final String COMMA_SEP = ",";
	
    //empty constructor
	//public PrescriptionDatabaseActivity(){}
	public PrescriptionDatabaseActivity(Context context){
		//context, database name, cursor factory, version
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase db){
		
		db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + 
				COLUMN_NAME_ID + " INTERGER PRIMARY KEY," +
				COLUMN_NAME_PNAME + TEXT_TYPE + COMMA_SEP + 
				COLUMN_NAME_PSIZE + TEXT_TYPE + COMMA_SEP +
				COLUMN_NAME_PCOLOR + TEXT_TYPE + COMMA_SEP +
				COLUMN_NAME_PFREQUENCY + TEXT_TYPE + COMMA_SEP +
				COLUMN_NAME_PAMOUNT + TEXT_TYPE + COMMA_SEP +
				COLUMN_NAME_PSTARTDATE + TEXT_TYPE + COMMA_SEP +
				COLUMN_NAME_PREMAINING + TEXT_TYPE + COMMA_SEP + " )");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(PrescriptionDatabaseActivity.class.getName(), "Upgrading db from version " + oldVersion + " to" 
				+ newVersion + ", which will destroy all old data.");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
}
