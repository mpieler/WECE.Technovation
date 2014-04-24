package com.example.breezer;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;


@SuppressLint("SimpleDateFormat") public class NewPrescriptionActivity extends ActionBarActivity {
	
	private PrescriptionDataSource datasource;

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
	    EditText editText = (EditText) findViewById(R.id.prescriptionNameEntered);
	    String message = editText.getText().toString();
	    prescription.setPrescriptionName(message);
	    
	    Spinner spinnerSize = (Spinner) findViewById(R.id.sizeSpinner);
	    String size = spinnerSize.toString();
	    prescription.setPrescriptionSize(size);
	    
	    Spinner spinnerColor = (Spinner) findViewById(R.id.colorSpinner);
	    String color = spinnerColor.toString();
	    prescription.setPrescriptionColor(color);
	    
	    Spinner spinnerFrequency = (Spinner) findViewById(R.id.frequencySpinner);
	    String frequency = spinnerFrequency.toString();
	    prescription.setPrescriptionFrequency(frequency);
	    
	    editText = (EditText) findViewById(R.id.prescriptionStartdateEntered);
	    message = editText.getText().toString();
	    prescription.setPrescriptionStartdate(message);
	    
	    Spinner spinnerAmount = (Spinner) findViewById(R.id.amountSpinner);
	    String amount = spinnerAmount.toString();
	    prescription.setPrescriptionAmount(amount);
	    
	    Spinner spinnerRemaining = (Spinner) findViewById(R.id.remainingSpinner);
	    String remaining = spinnerRemaining.toString();
	    prescription.setPrescriptionRemaining(remaining);
	    
	    datasource = new PrescriptionDataSource(this.getApplicationContext());
	    datasource.createPrescription(prescription);
	    
	    //move back to screen
	    Intent intent = new Intent(this, PrescriptionActivity.class);
	    startActivity(intent);
	}
}
