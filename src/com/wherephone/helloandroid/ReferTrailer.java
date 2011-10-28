package com.wherephone.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ReferTrailer extends Activity{
	
    private static final String TAG = "ReferTrailer";

    private String clientId, userId, pointType, value;
    private TextView numericEntryLabel;
    private EditText textField;
    private Spinner statusSpinner, fuelSpinner, trailerStatusSpinner;
    
	public void onCreate(Bundle savedInstanceState) {
    	Log.d(TAG, "ReferTrailer.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refertrailer);
        
        numericEntryLabel = (TextView) findViewById(R.id.TextView01);
        textField = (EditText) findViewById(R.id.txtAmount);
        
        statusSpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.refer_status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);
        
        fuelSpinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.fuel_level_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fuelSpinner.setAdapter(adapter2);
        
        trailerStatusSpinner = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
                this, R.array.trailer_status_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        trailerStatusSpinner.setAdapter(adapter3);
		

        
    }
	
	public void onClick(View view) {
		Log.d(TAG, "button Clicked");
		String date = (String) android.text.format.DateFormat.format("\"MM-dd-yyyy\",\"hh:mm:ss\"", new java.util.Date());


		
		userId = getIntent().getExtras().getString("userId"); 
		clientId = getIntent().getExtras().getString("clientId");
		Log.d(TAG, "selected " + statusSpinner.getSelectedItem() + " " + fuelSpinner.getSelectedItem()+ " " 
				+ trailerStatusSpinner.getSelectedItem());
			
//		DataSaver.save(date + ",\"" + userId + "\",\""+pointType+"\",\"" + clientId + 
//				"\",\"" + scan + "\",\"\",\"" + 
//				textField.getText().toString() + "\",\"\",\"END\",\"\",\"\",\"\"", getApplicationContext());
		
		
		Intent myIntent = new Intent(this, ScanSelector.class );
		myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(myIntent);
	}

}
