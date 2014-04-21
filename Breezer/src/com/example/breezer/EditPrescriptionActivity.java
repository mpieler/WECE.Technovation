package com.example.breezer;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
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
	
	//@Natasha, first, initialize all static variables here. There's five: size, color, frequency, amount, remaining
	static int amount;
	static String color;
	static String size;
	static String frequency;
	static String remaining;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_prescription);
		//test		
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			value = extras.getString("member");
		}

		//get prescription
		Prescription selectedPrescription = setEdit();
		
		//@Natasha, second, attach a value to all initialized variables
		amount = selectedPrescription.getPrescriptionAmount();
		color = selectedPrescription.getPrescriptionColor();
		size = selectedPrescription.getPrescriptionSize();
		frequency = selectedPrescription.getPrescriptionFrequency();
		remaining = selectedPrescription.getPrescriptionRemaining();
		
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
			
			//set prescription name
			EditText view = (EditText) rootView.findViewById(R.id.prescriptionNameEntered);
			view.setText(value, TextView.BufferType.EDITABLE);
			
		
			//@Michelle make template that receives from database
			//Edit Text field:
			EditText pColor = (EditText) rootView.findViewById(R.id.prescriptionColorEntered);
			pColor.setText(color, TextView.BufferType.EDITABLE);
			
			//Drop Down:
			Spinner pAmount = (Spinner) rootView.findViewById(R.id.amountSpinner);
			pAmount.setSelection(amount-1);//(-1) is a special case
		
			/*
			//@Natasha, third assign the value to the view for all remaining variables, keep in mind their type, see templates above
			//Spinner: amount, size, frequency EditText: remaining, color
			 * use variables such a pAmount, pSize, etc. and amountSpinner, sizeSpinner for spinners and prescriptionRemainingEntered for editTexts
			*/
			//Spinner pSize = (Spinner) rootView.findViewById(R.id.sizeSpinner);
			//pSize.setSelection(1);
			
			EditText pRemaining = (EditText) rootView.findViewById(R.id.prescriptionRemaining);
			pColor.setText(remaining, TextView.BufferType.EDITABLE);
			

			//Spinner pFrequency = (Spinner) rootView.findViewById(R.id.frequencySpinner);
			//pFrequency.setSelection(1);
			
			return rootView;
		}
		
	}


	
	public Prescription setEdit(){
		//find prescription
		//PrescriptionDataSource datasource = new PrescriptionDataSource();
		PrescriptionDataSource data = new PrescriptionDataSource(this.getApplicationContext());
		data.open();
		
		String prescriptionName = value;
		List<Prescription> list = new ArrayList<Prescription>();
		list = data.getAllPrescriptions();
		Prescription selectedPrescription = null;
		
		for(int i = 0; i < list.size(); i++){
			if(prescriptionName.equals(list.get(i).getPrescriptionName())){
				selectedPrescription = list.get(i);
				break;
			}
		}
		
		//selected prescription holds the prescription we are looking for
		return selectedPrescription;
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
		public void saveChanges(View view) {
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
