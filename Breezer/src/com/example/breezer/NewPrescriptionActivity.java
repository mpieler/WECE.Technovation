package com.example.breezer;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class NewPrescriptionActivity extends ActionBarActivity {
	
	private PrescriptionDataSource datasource;
	//public final static String EXTRA_MESSAGE = "com.example.breezer.MESSAGE";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_prescription);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_prescription, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_new_prescription, container, false);
			return rootView;
		}
	}
	
	//this needs to save the information to the database
	public void sendMessage(View view) {
		
		//initialize prescription
		Prescription prescription = new Prescription();
	    EditText editText = (EditText) findViewById(R.id.prescriptionName);
	    String message = editText.getText().toString();
	    prescription.setPrescriptionName(message);
	    
	    editText = (EditText) findViewById(R.id.prescriptionSize);
	    message = editText.getText().toString();
	    prescription.setPrescriptionSize(message);
	    
	    editText = (EditText) findViewById(R.id.prescriptionColor);
	    message = editText.getText().toString();
	    prescription.setPrescriptionColor(message);

	    editText = (EditText) findViewById(R.id.prescriptionFrequency);
	    message = editText.getText().toString();
	    prescription.setPrescriptionFrequency(message);
	    
	    editText = (EditText) findViewById(R.id.prescriptionStartdate);
	    message = editText.getText().toString();
	    prescription.setPrescriptionStartdate(message);
	    
	    editText = (EditText) findViewById(R.id.prescriptionAmount);
	    message = editText.getText().toString();
	    prescription.setPrescriptionAmount(message);
	    
	    editText = (EditText) findViewById(R.id.prescriptionRemaining);
	    message = editText.getText().toString();
	    prescription.setPrescriptionRemaining(message);
	    
	    
	    //save the new prescription
	    datasource.createPrescription(prescription);
	    
	    //move back to screen
	    Intent intent = new Intent(this, PrescriptionActivity.class);
	    //EditText editText = (EditText) findViewById(R.id.edit_message);
	    //String message = editText.getText().toString();
	    //intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
}
