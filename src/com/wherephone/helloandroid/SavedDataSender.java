package com.wherephone.helloandroid;

import java.io.DataInputStream;
import java.io.FileInputStream;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

public class SavedDataSender extends Thread {
	private static final String TAG = "SavedDataSender";
	public static boolean keepRunning = true;
	private Context theApp;
	
	public SavedDataSender(Context context){
		
		theApp = context;
	}
	
	public void run(){
		Log.d(TAG,"SavedDataSender run()");
		while (keepRunning){
			SystemClock.sleep(5000);
			String files[] = theApp.getApplicationContext().fileList();
			sendFileList(files);
		}
	}

	private void sendFileList(String[] files) {
		FileInputStream fis;
		int numFiles = 0;
		if (files != null){

			numFiles = files.length;	
		}
		Log.d(TAG,"numFiles " + numFiles);
		for (int i=0; i<numFiles; i++ ){
			Log.d(TAG,"File " + i + " " + files[i]);
			final StringBuffer storedString = new StringBuffer();
			try {
			    fis = theApp.openFileInput(files[i]);
			    DataInputStream dataIO = new DataInputStream(fis);
			    String strLine = null;

			    if ((strLine = dataIO.readLine()) != null) {
			        storedString.append(strLine);
			    }
			    dataIO.close();
			    fis.close();		    
			    Log.d(TAG,"  " + storedString );
			    
			    String resp = DataSender.send(storedString.toString());
			    Log.d(TAG,"  response>" + resp );
			    if (resp.equals("200")){
			    	theApp.deleteFile( files[i]);
			    }
								    
			}
			catch  (Exception e) {  
				Log.d(TAG,"File exception  " + e );
			}
			
		}
	}
}


