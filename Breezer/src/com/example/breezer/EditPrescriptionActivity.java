package com.example.breezer;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Build;

public class EditPrescriptionActivity extends ActionBarActivity {

	static String value = null;
	private PrescriptionDataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_prescription);
		//test		
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			value = extras.getString("member");
		}
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_prescription, menu);
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
					R.layout.fragment_edit_prescription, container, false);
			
			EditText view = (EditText) rootView.findViewById(R.id.prescriptionNameEntered);//view is null
			view.setHint(value);
			
			//@Michelle make template that receives from database
			
			//@Natasha add more here using template
			
			return rootView;
		}
	}


	public void remove(View view) {
		//find prescription
		//prescription name is in value
		datasource = new PrescriptionDataSource(this.getApplicationContext());
		datasource.open();
		
		String prescriptionName = value;
		List<Prescription> list = new ArrayList<Prescription>();
		list = datasource.getAllPrescriptions();
		Prescription selectedPrescription = null;
		
		for(int i = 0; i < list.size(); i++){
			if(prescriptionName.equals(list.get(i).getPrescriptionName())){
				selectedPrescription = list.get(i);
				datasource.deletePrescription(selectedPrescription);//doesn't delete ._.
				break;
			}
		}
		
		//ListView.listViewFromDB.RemoveAt(0);
		
		//move back to screen
	    Intent intent = new Intent(this, PrescriptionActivity.class);
	    //EditText editText = (EditText) findViewById(R.id.edit_message);
	    //String message = editText.getText().toString();
	    //intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
		
	}
	
	//this needs to save the information to the database
		public void sendMessage(View view) {
			//@Michelle remove prescription before adding edited one
			datasource = new PrescriptionDataSource(this.getApplicationContext());
			datasource.open();
			
			String prescriptionName = value;
			List<Prescription> list = new ArrayList<Prescription>();
			list = datasource.getAllPrescriptions();
			Prescription selectedPrescription = null;
			
			for(int i = 0; i < list.size(); i++){
				if(prescriptionName.equals(list.get(i).getPrescriptionName())){
					selectedPrescription = list.get(i);
					datasource.deletePrescription(selectedPrescription);
					break;
				}
			}
			
			
			//initialize prescription
			Prescription prescription = new Prescription();
		    EditText editText = (EditText) findViewById(R.id.prescriptionNameEntered);
		    String message = editText.getText().toString();
		    prescription.setPrescriptionName(message);
		    
		    editText = (EditText) findViewById(R.id.prescriptionSizeEntered);
		    message = editText.getText().toString();
		    prescription.setPrescriptionSize(message);
		    
		    editText = (EditText) findViewById(R.id.prescriptionColorEntered);
		    message = editText.getText().toString();
		    prescription.setPrescriptionColor(message);

		    editText = (EditText) findViewById(R.id.prescriptionFrequencyEntered);
		    message = editText.getText().toString();
		    prescription.setPrescriptionFrequency(message);
		    
		    editText = (EditText) findViewById(R.id.prescriptionStartdateEntered);
		    message = editText.getText().toString();
		    prescription.setPrescriptionStartdate(message);
		    
		    /*editText = (EditText) findViewById(R.id.prescriptionAmountEntered);
		    message = editText.getText().toString();
		    prescription.setPrescriptionAmount(message);*/
		    
		    Spinner spinnerAmount = (Spinner) findViewById(R.id.amountSpinner);
		    int amount = spinnerAmount.getId();
		    prescription.setPrescriptionAmount(amount);
		    
		    
		    editText = (EditText) findViewById(R.id.prescriptionRemainingEntered);
		    message = editText.getText().toString();
		    prescription.setPrescriptionRemaining(message);
		    
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
