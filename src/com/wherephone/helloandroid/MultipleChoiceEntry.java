package com.wherephone.helloandroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MultipleChoiceEntry extends ListActivity {

	private static final String TAG = "SecuredSelector";
	
	private String clientId;
	private String userId;
	private String scan;
	private String pointType = "Check Location";
	
	/** Called when the activity is first created. */
	public void onCreate(Bundle icicle) {
		Log.d(TAG,"SecuredSelector onCreate()");
		super.onCreate(icicle);
		// Create an array of Strings, that will be put to our ListActivity
		String[] names = new String[] { "Secured", "Unsecured"};
		
		scan = getIntent().getExtras().getString("scan");
		
		if (scan.contains("INP04")){
			
			names = new String[] { "Clear", "Dusting"};
			pointType = "Emission";
		}
		
		if (scan.contains("INP05")){
			
			names = new String[] { "Operating", "Not Operating"};
			pointType = "Operational";
		}
		
		// Create an ArrayAdapter, tcshat will actually make the Strings above
		// appear in the ListView
		this.setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Get the item that was clicked
		Object o = this.getListAdapter().getItem(position);
		String keyword = o.toString();

		String date = (String) android.text.format.DateFormat.format("\"MM-dd-yyyy\",\"hh:mm:ss\"", new java.util.Date());

//		Toast.makeText(this, "You selected: " + keyword + " " +
//				android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new java.util.Date())
//				, Toast.LENGTH_LONG)
//				.show();
		
		userId = getIntent().getExtras().getString("userId"); 
		clientId = getIntent().getExtras().getString("clientId");
		
		
//		DataSender.send(date + ",\"" + userId + "\",\""+pointType+"\",\"" + clientId + 
//				"\",\"" + scan + "\",\"" + keyword + 
//				"\",\"\",\"\",\"END\",\"\",\"\",\"\"" );
		
		DataSaver.save(date + ",\"" + userId + "\",\""+pointType+"\",\"" + clientId + 
				"\",\"" + scan + "\",\"" + keyword + 
				"\",\"\",\"\",\"END\",\"\",\"\",\"\"" , 
				getApplicationContext());
		
		Intent myIntent = new Intent(this, ScanSelector.class );
		myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(myIntent);
	
	}
	

	
}