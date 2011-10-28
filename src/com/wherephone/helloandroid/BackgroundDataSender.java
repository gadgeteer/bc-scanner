package com.wherephone.helloandroid;



import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BackgroundDataSender extends Service {
	
	
		private static final String TAG = "BackgroundDataSender";
	    int mStartMode = 3;     // indicates how to behave if the service is killed
	    IBinder mBinder;      // interface for clients that bind
	    boolean mAllowRebind; // indicates whether onRebind should be used
	    boolean keepRunning = true;
	    
	    


	    @Override
	    public void onCreate() {
	        // The service is being created
	    	Log.d(TAG, "BackgroundDataService created");

	    }
	    public int onStartCommand(Intent intent, int flags, int startId) {
	        // The service is starting, due to a call to startService()
	    	Log.d(TAG, "BackgroundDataService started");
	    	new Thread(new SavedDataSender(getApplicationContext())).start();	
	        return mStartMode;
	    }
	    @Override
	    public IBinder onBind(Intent intent) {
	        // A client is binding to the service with bindService()
	        return mBinder;
	    }
	    @Override
	    public boolean onUnbind(Intent intent) {
	        // All clients have unbound with unbindService()
	        return mAllowRebind;
	    }
	    @Override
	    public void onRebind(Intent intent) {
	        // A client is binding to the service with bindService(),
	        // after onUnbind() has already been called
	    }
	    @Override
	    public void onDestroy() {
	        // The service is no longer used and is being destroyed
	    	SavedDataSender.keepRunning = false;
	    	
	    }
	    
	    
	    
    	
	

}
