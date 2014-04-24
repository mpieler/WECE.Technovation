package com.example.breezer;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PrescriptionDataSource {
	// Database fields
	  private SQLiteDatabase database;
	  private PrescriptionDatabaseActivity dbHelper;
	  
	  private String[] allColumns = { 
			  PrescriptionDatabaseActivity.COLUMN_NAME_PNAME,
			  PrescriptionDatabaseActivity.COLUMN_NAME_PSIZE,
			  PrescriptionDatabaseActivity.COLUMN_NAME_PCOLOR,
			  PrescriptionDatabaseActivity.COLUMN_NAME_PFREQUENCY,
			  PrescriptionDatabaseActivity.COLUMN_NAME_PAMOUNT,
			  PrescriptionDatabaseActivity.COLUMN_NAME_PSTARTDATE,
			  PrescriptionDatabaseActivity.COLUMN_NAME_PREMAINING
			  };

	  
	  public PrescriptionDataSource(Context context) {
	    dbHelper = new PrescriptionDatabaseActivity(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public void createPrescription(Prescription prescription) {
		System.err.print(prescription);
		database = dbHelper.getWritableDatabase();
		  
	    ContentValues values = new ContentValues();
		values.put(PrescriptionDatabaseActivity.COLUMN_NAME_PNAME, prescription.prescriptionName);
		values.put(PrescriptionDatabaseActivity.COLUMN_NAME_PSIZE, prescription.prescriptionSize);
		values.put(PrescriptionDatabaseActivity.COLUMN_NAME_PCOLOR, prescription.prescriptionColor);
		values.put(PrescriptionDatabaseActivity.COLUMN_NAME_PFREQUENCY, prescription.prescriptionFrequency);
		values.put(PrescriptionDatabaseActivity.COLUMN_NAME_PAMOUNT, prescription.prescriptionAmount);
		values.put(PrescriptionDatabaseActivity.COLUMN_NAME_PSTARTDATE, prescription.prescriptionStartdate);
		values.put(PrescriptionDatabaseActivity.COLUMN_NAME_PREMAINING, prescription.prescriptionRemaining);
	
		// Inserting Row
	    database.insert(PrescriptionDatabaseActivity.TABLE_NAME, null, values);
	    database.close(); // Closing database connection
		
		/*
	    long insertId = database.insert(PrescriptionDatabaseActivity.TABLE_NAME, null,
	        values);
	    Cursor cursor = database.query(PrescriptionDatabaseActivity.TABLE_NAME,
	        allColumns, PrescriptionDatabaseActivity.COLUMN_NAME_PNAME + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Prescription newPrescription = cursorToPrescription(cursor);
	    cursor.close();
	    return newPrescription;*/
	  }

	  public void deletePrescription(Prescription prescription) {
	    String id = prescription.getPrescriptionName();
	    //System.out.println("prescription deleted with name: " + id);
	    //problem here
	    database.delete(PrescriptionDatabaseActivity.TABLE_NAME, PrescriptionDatabaseActivity.COLUMN_NAME_PNAME
	        + " = '" + id + "'", null);
	  }

	  //problem here, I believe
	  public List<Prescription> getAllPrescriptions() {
	    List<Prescription> prescriptions = new ArrayList<Prescription>();
	    Cursor cursor = database.query(PrescriptionDatabaseActivity.TABLE_NAME,
	        allColumns, null, null, null, null, null);
	    //cursor.moveToFirst();
	    //while (!cursor.isAfterLast()) {
	    	//Prescription prescription = cursorToPrescription(cursor);
	      //prescriptions.add(prescription);
	      //cursor.moveToNext();
	    //}
	    if (cursor.moveToFirst()) {
	           do {
	               Prescription prescription = new Prescription();
	               prescription.setPrescriptionName(cursor.getString(0));
	               prescription.setPrescriptionSize(cursor.getString(1));
	               prescription.setPrescriptionColor(cursor.getString(2));
	               prescription.setPrescriptionFrequency(cursor.getString(3));
	               prescription.setPrescriptionAmount(cursor.getString(4));
	               prescription.setPrescriptionStartdate(cursor.getString(5));
	               prescription.setPrescriptionRemaining(cursor.getString(6));
	               
	               // Add book to books
	               prescriptions.add(prescription);
	           } while (cursor.moveToNext());
	       }
	    
	    // make sure to close the cursor
	    cursor.close();
	    return prescriptions;
	  }

	  private Prescription cursorToPrescription(Cursor cursor) {
		Prescription prescription = new Prescription();
		//prescription.setId(cursor.getLong(0));
		prescription.setPrescriptionName(cursor.getString(1));
	    return prescription;
	  }
	
}
