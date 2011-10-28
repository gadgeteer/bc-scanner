package com.wherephone.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NumericEntry extends Activity{
	
    private static final String TAG = "NumericEntry";

    private String scan, clientId, userId, pointType;
    private TextView numericEntryLabel;
    private EditText textField;
    
	public void onCreate(Bundle savedInstanceState) {
    	Log.d(TAG, "NumericEntry.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numericentry);
        numericEntryLabel = (TextView) findViewById(R.id.TextView01);
        textField = (EditText) findViewById(R.id.txtAmount);
		scan = getIntent().getExtras().getString("scan");
		
		if (scan.contains("INP01")){
			numericEntryLabel.setText("Temperature");
			pointType = "Temperature";
		}
		
		if (scan.contains("INP02")){
			numericEntryLabel.setText("Pressure");
			pointType = "Pressure";
		}
		
		if (scan.contains("INP03")){
			numericEntryLabel.setText("Flow Rate");
			pointType = "Flow Rate";
		}
        
    }
	
	public void onClick(View view) {
		Log.d(TAG, "NumericEntry button Clicked");
		String date = (String) android.text.format.DateFormat.format("\"MM-dd-yyyy\",\"hh:mm:ss\"", new java.util.Date());

    	if (textField.getText().toString().length()==0){
    		LayoutInflater inflater = getLayoutInflater();
    		View toastRoot = inflater.inflate(R.layout.my_toast, null);
    		Toast toast = new Toast(this);
    		toast.setView(toastRoot);
    		toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,0, 0);
    		toast.show();

    		return;
    	}
		
		
		userId = getIntent().getExtras().getString("userId"); 
		clientId = getIntent().getExtras().getString("clientId");
		
		
//		DataSender.send(date + ",\"" + userId + "\",\""+pointType+"\",\"" + clientId + 
//				"\",\"" + scan + "\",\"\",\"" + 
//				textField.getText().toString() + "\",\"\",\"END\",\"\",\"\",\"\"" );
		
		DataSaver.save(date + ",\"" + userId + "\",\""+pointType+"\",\"" + clientId + 
				"\",\"" + scan + "\",\"\",\"" + 
				textField.getText().toString() + "\",\"\",\"END\",\"\",\"\",\"\"", getApplicationContext());
		
		
		Intent myIntent = new Intent(this, ScanSelector.class );
		myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(myIntent);
	}

}
