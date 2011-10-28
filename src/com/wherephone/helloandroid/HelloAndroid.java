package com.wherephone.helloandroid;



import android.app.Activity;
import android.content.Intent;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HelloAndroid extends Activity implements OnClickListener{

	private static final String TAG = "HelloAndroid";
	
	UserIdEntry idEntry;
	Button bt,bt2;
	TextView tv;
	String lastScan;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idEntry = new UserIdEntry();
        setContentView(R.layout.main);
        Log.d(TAG, "HelloAndroid.onCreate");
        
        bt = (Button) findViewById(R.id.button1);

        bt.setOnClickListener(this);

        Log.d(TAG, "found button");

        tv = (TextView) findViewById(R.id.textView2);
    
    }
    
    @Override
    public void onPause() {
    	Log.d(TAG, "HelloAndroid.onPause");
    	super.onPause();
    }

    @Override
    public void onResume() {
    	Log.d(TAG, "HelloAndroid.onResume");
    	super.onResume();
    	
    }
    
    @Override
    public void onStop(){
        Log.d(TAG, "HelloAndroid.onStop");
        super.onStop();
    }
    
	public void onClick(View view) {
		Log.d(TAG, "button Clicked");

        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
  //      intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);
		
	}
	
	public void advanceOnClick(View view) {
		Log.d(TAG, "advance button Clicked");
//		setContentView(R.layout.useridentry);
		Intent intent = new Intent();
		intent.setClass(this, UserIdEntry.class);
		startActivityForResult(intent,0);
	}
//	
//	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//		String contents = null;
//		String format = null;
//		if (requestCode == 0) {
//	        if (resultCode == RESULT_OK) {
//	            contents = intent.getStringExtra("SCAN_RESULT");
//	            format = intent.getStringExtra("SCAN_RESULT_FORMAT");
//	            // Handle successful scan
//	            Log.d(TAG, "got scan " + contents);
//	            lastScan = contents;
//	            tv.append(lastScan);
//	            Log.d(TAG, "scan appended");
//	            
//	        } else if (resultCode == RESULT_CANCELED) {
//	            // Handle cancel
//	        	Log.d(TAG, "cancelled scan");
//	        }
//	    }
//		httpGet(lastScan);
//	}
	
//	private void httpGet(String data) {
//		try {
//			Log.d(TAG,"start lastScan data=" + data);
//			HttpClient httpclient = new DefaultHttpClient();
//			Log.d(TAG,"created client");		
//			HttpGet httpget = new HttpGet("http://wherephone.com/debug/savePhoneData.php?data=" + data);
//			Log.d(TAG,"created get");
//			HttpResponse response = httpclient.execute(httpget);
//			Log.d(TAG,"executed GET");
//			HttpEntity entity = response.getEntity();
//			
//			byte buffer[] = new byte[8024] ;
//			InputStream is = entity.getContent() ;
//			int numBytes = is.read(buffer) ;
//			is.close();
//			Log.d(TAG,"numBytes=" + numBytes);
//		//	String entityContents = new String(buffer,0,numBytes) ;
//		//	Log.d(TAG,entityContents);
//			
//		} catch (Exception e) {
//			Log.d(TAG,"httpGet Exception");
//			e.printStackTrace();
//		} 
//	}	
}