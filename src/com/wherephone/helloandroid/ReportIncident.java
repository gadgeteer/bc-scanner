package com.wherephone.helloandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class ReportIncident extends ExpandableListActivity {
	private static final String TAG = "ReportIncident";
	/** Called when the activity is first created. */
	private String userId, clientId;
	private String pointType = "Incident";

    final String[] CATAGORIES = {"Assistance Provided","Automobile","Bomb Threat","Crime/Violent","Crime/Other","Equipment",
    		                     "Fire","Injury","Maintenance Required","Property Damage","Weather Related"};
    
    final String subCatagories[][] = {{"Escort to car","Repair Flat Tire", "Jump-Start Vehicle"},
    		{"Accident-No Injury","Accident-Injury", "Accident - Hit&Run", "Break in with property theft","Driving Erratically", "Parking violation", "Speeding", "Tampering", "Theft of Vehicle"},
            {"Note or Letter", "Suspicious Package", "Telephone Threat"},
            {"Assault", "Assault with deadly weapon", "Murder","Rape", "Robbery","Stabbing","Shooting"},
            {"Abusive Language","Burglary","Disorderly Conduct","Fighting","Gambling","Indecent Exposure/Conduct","Loitering","Malicious Mischief","Purse Snatching","Trespassing","Unauthorized Entry","Vandalism"},
            {"Overheating","Alarm Sounded"},
            {"False Alarm","Controlled/Extinguished"},
            {"Client Employee - minor","Client Employee - serious","Visitor - minor","Visitor - serious"},
            {"Broken Window - Door","Elevator Not Operating","Gate Not Operating","Fence Broken/Overgrown","Lighting Out - Broken","Oil Spill","Water Leak"},
            {"Defacing Property/Graffiti","Destroying Property"},
            {"Power Outage","Tree Damage","Roof Leak"}
            
    };
	
	
	public void onCreate(Bundle icicle) {
		Log.d(TAG,"ScanSelector onCreate()");
		super.onCreate(icicle);
		userId = getIntent().getExtras().getString("userId"); 
		clientId = getIntent().getExtras().getString("clientId");
		
	    // Construct Expandable List
	    final String NAME = "name";
	    final String IMAGE = "image";
	    final LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    final ArrayList<HashMap<String, String>> headerData = new ArrayList<HashMap<String, String>>();

	    
	    for (int k=0; k<CATAGORIES.length; k++){
		    final HashMap<String, String> group1 = new HashMap<String, String>();
		    group1.put(NAME, CATAGORIES[k]);
		    headerData.add( group1 );
	    }
//	    final HashMap<String, String> group1 = new HashMap<String, String>();
//	    group1.put(NAME, "Assistance Provided");
//	    headerData.add( group1 );
//
//	    final HashMap<String, String> group2 = new HashMap<String, String>();
//	    group2.put(NAME, "Automotive");
//	    headerData.add( group2);
//	    
//	    final HashMap<String, String> group3 = new HashMap<String, String>();
//	    group3.put(NAME, "Bomb Threat");
//	    headerData.add( group3);


	    final ArrayList<ArrayList<HashMap<String, Object>>> childData = new ArrayList<ArrayList<HashMap<String, Object>>>();


	    for (int j=0; j<CATAGORIES.length; j++){
		    final ArrayList<HashMap<String, Object>> group1data = new ArrayList<HashMap<String, Object>>();

		    for (int i = 0; i<subCatagories[j].length; i++){
			    final HashMap<String, Object> autoMap = new HashMap<String,Object>();
			    autoMap.put("subitem", subCatagories[j][i]);
			    group1data.add(autoMap);
		    }
		    childData.add(group1data);
	    }
	    

//	    final ArrayList<HashMap<String, Object>> group2data = new ArrayList<HashMap<String, Object>>();
//	    final HashMap<String, Object> assistMap = new HashMap<String,Object>();
//	    assistMap.put("subitem", "Accident-No Injury");
//	    group2data.add(assistMap);
//
//	    childData.add(group2data);


	    // Set up some sample data in both groups
//	    for( int i=0; i<10; ++i) {
//	        final HashMap<String, Object> map = new HashMap<String,Object>();
//	        map.put("subitem", "Child " + i );
//	        map.put(IMAGE, getResources().getDrawable(R.drawable.icon));
//	        ( i%2==0 ? group1data : group2data ).add(map);
//	    }
	    


	    setListAdapter( new SimpleExpandableListAdapter(
	            this,
	            headerData,
	            android.R.layout.simple_expandable_list_item_1,
	            new String[] { NAME },            // the name of the field data
	            new int[] { android.R.id.text1 }, // the text field to populate with the field data
	            childData,
	            0,
	            null,
	            new int[] {}
	        ) {
	            @Override
	            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
	                final View v = super.getChildView(groupPosition, childPosition, isLastChild, convertView, parent);
	                
	                // Populate your custom view here
	                ((TextView)v.findViewById(R.id.name)).setText( (String) ((Map<String,Object>)getChild(groupPosition, childPosition)).get("subitem") );
	           //     ((ImageView)v.findViewById(R.id.image)).setImageDrawable( (Drawable) ((Map<String,Object>)getChild(groupPosition, childPosition)).get(IMAGE) );

	                return v;
	            }

	            @Override
	            public View newChildView(boolean isLastChild, ViewGroup parent) {
	                 return layoutInflater.inflate(R.layout.reportincident, null, false);
	            }
	        }
	    );
		
	}
	
    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
        int groupPosition, int childPosition, long id) {
    	Log.d(TAG,"" + groupPosition+ " " + childPosition +" " + CATAGORIES[groupPosition] + " " + 
    			subCatagories[groupPosition][childPosition]);
		String date = (String) android.text.format.DateFormat.format("\"MM-dd-yyyy\",\"hh:mm:ss\"", new java.util.Date());
        
		String oliNum = (groupPosition+1) + ""; 
        oliNum = ( oliNum.length() > 1) ? "OLI0" + oliNum : "OLI00" + oliNum; 
        
        int screenIndex = childPosition+1;
        
        String oliTotal = oliNum + ((screenIndex > 9) ? "0" + screenIndex : "00" + screenIndex);
		DataSaver.save(date + ",\"" + userId + "\",\""+pointType+"\",\"" + clientId + 
				"\",\"" + oliTotal + "\",\"" + 
				groupPosition+ " "+ CATAGORIES[groupPosition] + "-" +
				childPosition + " " +
    			subCatagories[groupPosition][childPosition] + 
				"\",\"\",\"maintenance\",\"END\",\"\",\"\",\"\"" , 
				getApplicationContext());
    	
		Intent myIntent = new Intent(this, ScanSelector.class );
		myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(myIntent);
    	
        return true;
    }
	

}
