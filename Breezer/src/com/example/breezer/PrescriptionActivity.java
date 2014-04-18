package com.example.breezer;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class PrescriptionActivity extends Activity {

	ListView listViewFromDB;
	
	public static  String EXTRA_MESSAGE = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prescription);
		listViewFromDB = (ListView) findViewById(R.id.listViewFromDB);

		populateListViewFromDB();
		
		/*if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prescription, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_prescription,
					container, false);
			return rootView;
		}
	}
	/** Called when the user clicks the AddNewPrescription link */
	public void moveToAddNewPrescription(View view) {
	    Intent intent = new Intent(this, NewPrescriptionActivity.class);
	    //EditText editText = (EditText) findViewById(R.id.edit_message);
	    //String message = editText.getText().toString();
	    //intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
	
	public void populateListViewFromDB(){
	
				PrescriptionDataSource db = new PrescriptionDataSource(this.getApplicationContext());
				db.open();
				
				List<Prescription> prescriptionList = new ArrayList<Prescription>();
				prescriptionList = db.getAllPrescriptions();
				int size = prescriptionList.size();
				final String[] prescriptionNames = new String[size];
				//final String[] membership = new String[size];
				Prescription[] prescription = new Prescription[size];
				
				//transfer from array list to array
				for(int i=0; i < prescriptionList.size(); i++){
					prescription[i] = prescriptionList.get(i);
				}
				
				for(int i = 0; i < size; i++){
					prescriptionNames[i] = prescription[i].getPrescriptionName();
					//membership[i] = person[i].getMembership();
				}
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, prescriptionNames);
				listViewFromDB.setAdapter(adapter);
				
				listViewFromDB.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
						  String membershipID = prescriptionNames[position];
						  Intent selectedIntent = new Intent(getApplicationContext(), EditPrescriptionActivity.class);
						  selectedIntent.putExtra("member", membershipID);
						  startActivity(selectedIntent);
					   }
				});
	}
}
