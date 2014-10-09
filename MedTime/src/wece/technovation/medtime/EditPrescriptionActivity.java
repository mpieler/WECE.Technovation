package wece.technovation.medtime;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import wece.technovation.medtime.R;

public class EditPrescriptionActivity extends ActionBarActivity {

	static String value = null;
	private PrescriptionDataSource datasource;
	
	//@Natasha, first, initialize all static variables here. There's five: size, color, frequency, amount, remaining
	static String amount;
	static String color;
	static String size;
	static String frequency;
	static String remaining;
	static String startdateValue;
	
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
		startdateValue = selectedPrescription.getPrescriptionStartdate();
		
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
			
			EditText startdate = (EditText) rootView.findViewById(R.id.prescriptionStartdateEntered);
			startdate.setText(startdateValue, TextView.BufferType.EDITABLE);
		
			int setColor = 0;
			String[] selectedColor = new String[8];
			selectedColor[0] = "White";
			selectedColor[1] = "Blue";
			selectedColor[2] = "Orange";
			selectedColor[3] = "Red";
			selectedColor[4] = "Pink";
			selectedColor[5] = "Brown";
			selectedColor[6] = "Green";
			selectedColor[7] = "Purple";
			for(int i = 0; i < 8; i++){//find out how many items are in pcolor
				//create array with all colors
				
				if(color.equals(selectedColor[i])){
					setColor = i;
					break;
				}
			}
			selectedColor = null;
			
			Spinner pColor = (Spinner) rootView.findViewById(R.id.colorSpinner);
			pColor.setSelection(setColor+1);//need to set up correct thing, color holds correct thing
			
			setColor = 0;
			String[] selectedAmount = new String[5];
			selectedAmount[4] = "Five at a time";
			selectedAmount[3] = "Four at a time";
			selectedAmount[2] = "Three at a time";
			selectedAmount[1] = "Two at a time";
			selectedAmount[0] = "One at a time";
			for(int i = 0; i < 5; i++){//find out how many items are in pcolor
				//create array with all amounts
				
				if(amount.equals(selectedAmount[i])){
					setColor = i;
					break;
				}
			}
			selectedAmount = null;
			
			Spinner pAmount = (Spinner) rootView.findViewById(R.id.amountSpinner);
			pAmount.setSelection(setColor+1);//need to set up correct thing

			setColor = 0;
			String[] selectedSize = new String[3];
			selectedSize[0] = "Small";
			selectedSize[1] = "Medium";
			selectedSize[2] = "Large";
			for(int i = 0; i < 3; i++){//find out how many items are in pcolor
				//create array with all sizes
				
				if(size.equals(selectedSize[i])){
					setColor = i;
					break;
				}
			}
			selectedSize = null;
			
			Spinner pSize = (Spinner) rootView.findViewById(R.id.sizeSpinner);
			pSize.setSelection(setColor+1);
			
			setColor = 0;
			for(int i = 0; i < 45; i++){//find out how many items are in pRemaining
				if(remaining.equals(Integer.toString(i))){
					setColor = i;
					break;
				}
			}
			
			Spinner pRemaining = (Spinner) rootView.findViewById(R.id.remainingSpinner);
			pRemaining.setSelection(setColor+1);//need to set up correct thing
			
			setColor = 0;
			String[] selectedFrequency = new String[8];
			selectedFrequency[0] = "Every 4 hours";
			selectedFrequency[1] = "Every 6 hours";
			selectedFrequency[2] = "Every 8 hours";
			selectedFrequency[3] = "Every 12 hours";
			selectedFrequency[4] = "Every 24 hours";
			selectedFrequency[5] = "Every 48 hours";
			selectedFrequency[6] = "Every 72 hours";
			selectedFrequency[7] = "Once a week";
			for(int i = 0; i < 8; i++){//find out how many items are in pFrequency
				//create array with all frequencies
				if(frequency.equals(selectedFrequency[i])){
					setColor = i;
					break;
				}
			}
			selectedFrequency = null;

			Spinner pFrequency = (Spinner) rootView.findViewById(R.id.frequencySpinner);
			pFrequency.setSelection(setColor+1);//need to set up correct thing
			
			return rootView;
		}
	}

	public Prescription setEdit(){
		//find prescription
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
		
		//move back to screen
	    Intent intent = new Intent(this, PrescriptionActivity.class);
	    startActivity(intent);
		
	}
	
	//this needs to save the information to the database
		public void saveChanges(View view) {
			//remove prescription before adding edited one
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
		    
		    Spinner spinnerSize = (Spinner) findViewById(R.id.sizeSpinner);
		    String size = spinnerSize.getSelectedItem().toString();
		    prescription.setPrescriptionSize(size);
		    
		    Spinner spinnerColor = (Spinner) findViewById(R.id.colorSpinner);
		    String color = spinnerColor.getSelectedItem().toString();
		    prescription.setPrescriptionColor(color);
		    
		    Spinner spinnerFrequency = (Spinner) findViewById(R.id.frequencySpinner);
		    String frequency = spinnerFrequency.getSelectedItem().toString();
		    prescription.setPrescriptionFrequency(frequency);
		    
		    editText = (EditText) findViewById(R.id.prescriptionStartdateEntered);
		    message = editText.getText().toString();
		    prescription.setPrescriptionStartdate(message);
		    
		    Spinner spinnerAmount = (Spinner) findViewById(R.id.amountSpinner);
		    String amount = spinnerAmount.getSelectedItem().toString();
		    prescription.setPrescriptionAmount(amount);
		    
		    Spinner spinnerRemaining = (Spinner) findViewById(R.id.remainingSpinner);
		    String remaining = spinnerRemaining.getSelectedItem().toString();
		    prescription.setPrescriptionRemaining(remaining);
		    
		    datasource = new PrescriptionDataSource(this.getApplicationContext());
		    datasource.createPrescription(prescription);
		    
		    //move back to screen
		    Intent intent = new Intent(this, PrescriptionActivity.class);
		    startActivity(intent);
		}

}
