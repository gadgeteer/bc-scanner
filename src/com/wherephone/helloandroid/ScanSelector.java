package com.wherephone.helloandroid;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ScanSelector extends ListActivity {

	private static final String TAG = "ScanSelector";
	
	private String clientId;
	private String userId;
	
	/** Called when the activity is first created. */
	public void onCreate(Bundle icicle) {
		Log.d(TAG,"ScanSelector onCreate()");
		super.onCreate(icicle);
		// Create an array of Strings, that will be put to our ListActivity
		String[] names = new String[] { "Scan Location", "Report Incident", "Inspections Complete","Refrigerated Trailer Inspection", "Load Equipment List",};
		// Create an ArrayAdapter, that will actually make the Strings above
		// appear in the ListView
		
		userId = getIntent().getExtras().getString("userId"); 
		clientId = getIntent().getExtras().getString("clientId");
		Log.d(TAG,"userId " + userId + "  clientId " + clientId);
		this.setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Get the item that was clicked
		Object o = this.getListAdapter().getItem(position);
		String keyword = o.toString();
		if (position == 0){
			startActivityForResult( new Intent("com.google.zxing.client.android.SCAN"),0);
		} else if (position ==1 ){// report incident
			Intent reportIntent = new Intent(getApplicationContext(), ReportIncident.class );
        	reportIntent.putExtra("userId", userId);
        	reportIntent.putExtra("clientId", clientId);
			
			startActivity(reportIntent);
			
			
		} else if (position == 2){
			finish();
		} else if (position == 3){// refer incident
			Intent reportIntent = new Intent(getApplicationContext(), ReferTrailer.class );
        	reportIntent.putExtra("userId", userId);
        	reportIntent.putExtra("clientId", clientId);
			
			startActivity(reportIntent);
			
			
		} else {
		
		
	    	Toast.makeText(this, "You selected: " + keyword, Toast.LENGTH_LONG)
				.show();
		}
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		String contents = null;

		if (requestCode == 0) {
	        if (resultCode == RESULT_OK) {
	            contents = intent.getStringExtra("SCAN_RESULT");

	            // Handle successful scan

	            Log.d(TAG, "scaned " + contents);
	            Intent myIntent;
	            if ( contents.contains("INP01") || contents.contains("INP02") || contents.contains("INP03") ){
	            	myIntent = new Intent(this, NumericEntry.class );
	            } else {
	            	myIntent = new Intent(this, MultipleChoiceEntry.class );
	            	
	            }
	        	myIntent.putExtra("userId", userId);
	        	myIntent.putExtra("clientId", clientId);
	        	myIntent.putExtra("scan", contents);
	        	startActivity(myIntent);
	            
	            
	        } else if (resultCode == RESULT_CANCELED) {
	            // Handle cancel
	        	Log.d(TAG, "cancelled scan");
	        }
	    }

	}
	
}