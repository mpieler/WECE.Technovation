package com.example.breezer;

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
import android.os.Build;

public class SymptomsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_symptoms);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.symptoms, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_symptoms,
					container, false);
			return rootView;
		}
	}
	/** Called when the user clicks Add_Symptom link */
	public void moveToAdd_Symptom(View view) {
	    Intent intent = new Intent(this, AddSymptomActivity.class);
	    //EditText editText = (EditText) findViewById(R.id.edit_message);
	    //String message = editText.getText().toString();
	    //intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}

	/** Called when the user clicks Symptoms_Log link */
	public void moveToSymptoms_Log(View view) {
	    Intent intent = new Intent(this, SymtomsLogActivity.class);
	    //EditText editText = (EditText) findViewById(R.id.edit_message);
	    //String message = editText.getText().toString();
	    //intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
	
	/** Called when the user clicks Edit_Symptoms link */
	public void moveToEdit_Symptoms(View view) {
	    Intent intent = new Intent(this, EditSymptomsActivity.class);
	    //EditText editText = (EditText) findViewById(R.id.edit_message);
	    //String message = editText.getText().toString();
	    //intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
	

}
