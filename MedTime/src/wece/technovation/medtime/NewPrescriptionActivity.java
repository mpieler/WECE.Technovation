package wece.technovation.medtime;

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
import android.widget.Spinner;
import wece.technovation.medtime.R;


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
	    EditText editText = (EditText) findViewById(R.id.prescriptionName);
	    String message = editText.getText().toString();
	    prescription.setPrescriptionName(message);
	    
	    Spinner spinnerSize = (Spinner) findViewById(R.id.sizeSpinner);
	    String size = spinnerSize.getSelectedItem().toString();
	    prescription.setPrescriptionSize(size);
	    
	    SimpleDateFormat s = new SimpleDateFormat("hh:mm");
	    String format = s.format(new Date());
	    prescription.setPrescriptionStartdate(format);//entered hour and minute
	    
	    Spinner spinnerColor = (Spinner) findViewById(R.id.colorSpinner);
	    String color = spinnerColor.getSelectedItem().toString();
	    prescription.setPrescriptionColor(color);
	    
	    Spinner spinnerFrequency = (Spinner) findViewById(R.id.frequencySpinner);
	    String frequency = spinnerFrequency.getSelectedItem().toString();
	    prescription.setPrescriptionFrequency(frequency);
	    
	    Spinner spinnerAmount = (Spinner) findViewById(R.id.amountSpinner);
	    String amount = spinnerAmount.getSelectedItem().toString();
	    prescription.setPrescriptionAmount(amount);
	    
	    Spinner spinnerRemaining = (Spinner) findViewById(R.id.remainingSpinner);
	    String remaining = spinnerRemaining.getSelectedItem().toString();
	    prescription.setPrescriptionRemaining(remaining);
	    
	    datasource = new PrescriptionDataSource(this.getApplicationContext());
	    datasource.createPrescription(prescription);
	    
	    //reminder(prescription);
	    
	    //move back to screen
	    Intent intent = new Intent(this, PrescriptionActivity.class);
	    startActivity(intent);
	}
	
	public void reminder(Prescription prescription){
		//create reminder algorithm
				
		
		//this doesn't work completely yet
		int remaining = Integer.parseInt(prescription.getPrescriptionRemaining());
				
		String str = prescription.getPrescriptionAmount(); 
		String result = str.substring(0, str.indexOf(" "));
		int amount = 1;
		if(result.equals("One")){
			amount = 1;
		}
		else if(result.equals("Two")){
			amount = 2;
		}
		else if(result.equals("Three")){
			amount = 3;
		}
		else if(result.equals("Four")){
			amount = 4;
		}
		else if(result.equals("Five")){
			amount = 5;
		}
		
		str = prescription.getPrescriptionFrequency(); 
		result = str.substring(str.indexOf(" ") + 1, str.indexOf(" "));
		int frequency = Integer.parseInt(result);
		
		
		//how many times the reminder needs to go off
		int numTimes = remaining/amount;
		
		//when the reminder needs to go off
		//startdate + frequency = when alarm goes off; subtract one of number of times		
		
	}
	
	/** Called when the user clicks Home link */
	public void moveToPrescription(View view) {
	    Intent intent = new Intent(this, PrescriptionActivity.class);
	    //EditText editText = (EditText) findViewById(R.id.edit_message);
	    //String message = editText.getText().toString();
	    //intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
}
