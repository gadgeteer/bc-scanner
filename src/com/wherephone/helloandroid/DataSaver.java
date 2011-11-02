package com.wherephone.helloandroid;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import android.os.Environment;
import android.util.Log;


import android.content.Context;

public class DataSaver {
	private static final String TAG = "DataSaver";
	private static String PATH = "/Android/data/com.wherephone.helloandroid/files/";
	private static Long MONTH = new Long(1000*60*60*24*30);
	
	public static void deleteOld(){
		
		try {
			if ( getExternalReadWriteable()){
				String[] files =  (new File(Environment.getExternalStorageDirectory() + PATH)).list();
				Log.d(TAG, "current time" + System.currentTimeMillis() + "");
				for (int i = 0; i < files.length; i++) {
					Log.d(TAG, files[i] + "");
					Long fileNumber = new Long(files[i]);
					if (fileNumber + MONTH < System.currentTimeMillis()){
						Log.d(TAG, files[i] + "deleted");
						new File(Environment.getExternalStorageDirectory() + PATH, files[i]).delete();
					}
					
				}
				
				
			}
		} catch (Exception e) {
			Log.d(TAG,"Problem deleting " + e);
			e.printStackTrace();
		}
		
	}
	
	public static boolean getExternalReadWriteable(){
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			Log.d(TAG,"We can read and write the media");
		    mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			Log.d(TAG,"We can only read the media");
		    mExternalStorageAvailable = true;
		    mExternalStorageWriteable = false;
		} else {
			Log.d(TAG,"Something else is wrong. It may be one of many other states");
		    mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
		
		return mExternalStorageAvailable && mExternalStorageWriteable;
	}
	
	public static void save(TourDataPoint thePoint,  Context context){
		Log.d(TAG,"theTourPoint " + thePoint);
		
	}
	
	public static void save(ReferDataPoint thePoint,  Context context){
		Log.d(TAG,"theReferPoint " + thePoint);
		
	}
	
	public static void save(String data, Context context){
		String FILENAME = System.currentTimeMillis() + "";
		Log.d(TAG,"PATH=" + PATH);
		
		try {
			saveToApplicationMemory(data, context, FILENAME);
			saveToExternalMemory(data, FILENAME);			
		} catch (Exception e) {

			Log.d(TAG,"Problem writing " + e);
			e.printStackTrace();
		}
	}

	private static void saveToApplicationMemory(String data, Context context,
			String FILENAME) throws FileNotFoundException, IOException {
		FileOutputStream fos;
		fos = context.openFileOutput(FILENAME, Context.MODE_WORLD_READABLE);
		fos.write(data.getBytes()); //write to application top level directory for immediate sending 
		fos.close();
	}

	private static void saveToExternalMemory(String data, String FILENAME)
			throws IOException, FileNotFoundException {
		if ( getExternalReadWriteable() ){//check if external memory is available for USB download
			Boolean fileOk = true;
			File tempPath = new File(Environment.getExternalStorageDirectory(), PATH);
			
			if (!tempPath.exists()) {
			  fileOk = tempPath.mkdirs();
			  Log.d(TAG,"made path "+ fileOk);
			}

			File tempFile = new File(Environment.getExternalStorageDirectory() + PATH, FILENAME);
			
			fileOk = fileOk && tempFile.createNewFile();
			
			if(fileOk){
			  BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tempFile));

			  out.write(data.getBytes());
			  out.close();
			} else {
				Log.d(TAG,"could not create file");
			}
		}
	}
	

}
