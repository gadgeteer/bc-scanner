package com.wherephone.helloandroid;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class UserIdEntry extends Activity implements OnClickListener {

	private static final String TAG = "UserIdEntry";
	static String userValue ="";
	static String clientValue ="";
	public static final String DEFAULT_TITLE = "Install Barcode Scanner?";
	public static final String DEFAULT_MESSAGE =
	      "This application requires Barcode Scanner. Would you like to install it?";
	public static final String DEFAULT_YES = "Yes";
	public static final String DEFAULT_NO = "No";
    private static final String PACKAGE = "com.google.zxing.client.android";
	Button scanButton;
	TextView userIdEntry, clientIdEntry;
	
    public void onCreate(Bundle savedInstanceState) {
    	Log.d(TAG, "UserIdEntry.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.useridentry);
        scanButton  = (Button) findViewById(R.id.button1);
        userIdEntry = (TextView) findViewById(R.id.UserEntry);
        clientIdEntry = (TextView) findViewById(R.id.ClientEntry);

        userIdEntry.setText(userValue);
        clientIdEntry.setText(clientValue);
        
        if (!isMyServiceRunning()){
        	Intent myIntent = new Intent(getApplicationContext(), BackgroundDataSender.class);
    		startService(myIntent);
        }
    	Log.d(TAG, " after startService");
        
    	DataSaver.deleteOld();
    	
    	Log.d(TAG, " after deleteOld");
    	TourDataPoint aPoint = new TourDataPoint("abc","123");
    	ReferDataPoint rPoint = new ReferDataPoint("refer2", "789");
    	rPoint.setSetPoint("32");
    	Log.d(TAG, " after TourPointCreated");
    	Log.d(TAG, "aPoint> " + aPoint.getClientID() + " " + aPoint.getUserId());
    	new ObjectSaver(rPoint, getApplicationContext());
    	Log.d(TAG, " after ObjectSaver created");
    	ObjectSaver.getObject(getApplicationContext());
    	Log.d(TAG, " after getObject");
    	
    	
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	Log.d(TAG,"onCreateOptionsMenu");
    	return false;
    }
    
    public void onEdit(View view) {
    	Log.d(TAG, "UserIdEntry TextFieldtapped");
    	switch(view.getId()){
    	case R.id.UserEntry:
    		Log.d(TAG, "-UserIdEntry");
    		userIdEntry.setText("");
    		scanButton.setText("Scan");
    		break;
    		
    	case R.id.ClientEntry:
    		Log.d(TAG, "-ClientCode");
    		clientIdEntry.setText("");
    		scanButton.setText("Scan");
    		break;
    	
    	}
    	
    }
	
	public void onClick(View view) {
		Log.d(TAG, "UserIdEntry button Clicked");
        if ((userIdEntry.getText().length() > 0 ) && (clientIdEntry.getText().length() > 0 ) ){


        	Intent myIntent = new Intent(getApplicationContext(), ScanSelector.class );
        	myIntent.putExtra("userId", userValue);
        	myIntent.putExtra("clientId", clientValue);
        	startActivity(myIntent);
        	clientValue ="";
        	userValue ="";
        	finish();

        	
        	
        } else {
        	try {
        		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
  //      	intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
          		startActivityForResult(intent, 0);
        	} catch (ActivityNotFoundException e) {
        	      showDownloadDialog( ((Activity)view.getContext()), DEFAULT_TITLE, DEFAULT_MESSAGE, DEFAULT_YES, DEFAULT_NO);
            }
        }
		
	}

	  private static void showDownloadDialog(final Activity activity,
              CharSequence stringTitle,
              CharSequence stringMessage,
              CharSequence stringButtonYes,
              CharSequence stringButtonNo) {
               AlertDialog.Builder downloadDialog = new AlertDialog.Builder(activity);
               downloadDialog.setTitle(stringTitle);
               downloadDialog.setMessage(stringMessage);
               downloadDialog.setPositiveButton(stringButtonYes, new DialogInterface.OnClickListener() {
            	   public void onClick(DialogInterface dialogInterface, int i) {
            		   Uri uri = Uri.parse("market://search?q=pname:" + PACKAGE);
            		   Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            		   activity.startActivity(intent);
            	   }
               });
               downloadDialog.setNegativeButton(stringButtonNo, new DialogInterface.OnClickListener() {
            	   public void onClick(DialogInterface dialogInterface, int i) {}
               });
               downloadDialog.show();
	  }
	  
	  private boolean isMyServiceRunning() {
		   Log.d(TAG,"isMyServiceRunning()");
		    ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
		    	Log.d(TAG," "+service.service.getClassName());
		        if ("com.wherephone.helloandroid.BackgroundDataSender".equals(service.service.getClassName())) {
		            return true;
		        }
		    }
		    return false;
		}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		String contents = null;

		if (requestCode == 0) {
	        if (resultCode == RESULT_OK) {
	            contents = intent.getStringExtra("SCAN_RESULT");

	            // Handle successful scan
	            Log.d(TAG, "got scan " + contents);
	            if (userIdEntry.getText().length() == 0 ){
	            	Log.d(TAG, " in null");	
	              userIdEntry.setText(contents);
	              userValue = contents;
	              
	            } else {
	            	Log.d(TAG, " is not null");
	            	clientIdEntry.setText(contents);
	            	clientValue = contents;
	            	
	            }
	            Log.d(TAG, "scan appended");
	            if (userIdEntry.getText().length() > 0 &&
	            	clientIdEntry.length()>0	){
	            	scanButton.setText("Save");
	            }
	            
	        } else if (resultCode == RESULT_CANCELED) {
	            // Handle cancel
	        	Log.d(TAG, "cancelled scan");
	        }
	    }

	}

}
