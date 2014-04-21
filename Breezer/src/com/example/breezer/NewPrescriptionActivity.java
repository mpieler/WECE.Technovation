package com.example.breezer;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	    
	    EditText psize = (EditText) findViewById(R.id.prescriptionSize);
	    String size = psize.getText().toString();
	    prescription.setPrescriptionSize(size);
	    
	    EditText pcolor = (EditText) findViewById(R.id.prescriptionColor);
	    String color = pcolor.getText().toString();
	    prescription.setPrescriptionColor(color);

	    EditText pfreq = (EditText) findViewById(R.id.prescriptionFrequency);
	    String frequency = pfreq.getText().toString();
	    prescription.setPrescriptionFrequency(frequency);
	    
	    //editText = (EditText) findViewById(R.id.prescriptionStartdate);//this will be automated
	    //message = editText.getText().toString();
	    SimpleDateFormat s = new SimpleDateFormat("hh:mm");
	    String format = s.format(new Date());
	    prescription.setPrescriptionStartdate(format);//entered hour and minute
	    
	    
	    EditText amt = (EditText) findViewById(R.id.prescriptionAmount);
	    String amount = amt.getText().toString();
	    prescription.setPrescriptionAmount(amount);
	    
	    EditText rem = (EditText) findViewById(R.id.prescriptionRemaining);
	    String remaining = rem.getText().toString();
	    prescription.setPrescriptionRemaining(remaining);
	    
	    datasource = new PrescriptionDataSource(this.getApplicationContext());
	    //save the new prescription
	    //datasource.open();
	    datasource.createPrescription(prescription);
	    
	    //move back to screen
	    Intent intent = new Intent(this, PrescriptionActivity.class);
	    //EditText editText = (EditText) findViewById(R.id.edit_message);
	    //String message = editText.getText().toString();
	    //intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
}
