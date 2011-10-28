package com.wherephone.helloandroid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.util.Log;

public class ObjectSaver {
	private static final String TAG = "ObjectSaver";
	private static final String PATH ="savedObjects";
	
	public ObjectSaver(Object theObject, Context context){
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		boolean madeDirs = false;
		
		try{
			File topLevelDir = context.getDir(PATH, Context.MODE_WORLD_READABLE);
			if (!topLevelDir.exists()) {
				madeDirs = topLevelDir.mkdirs();
				Log.d(TAG,"made path "+ madeDirs);
			}
			
			Log.d(TAG, "writing topLevelDir " + topLevelDir);
			fos = new FileOutputStream(new File(topLevelDir,"anObject") );
			out = new ObjectOutputStream(fos);
		       
			out.writeObject(theObject);
			out.close();
		     }
		     catch(Exception ex)
		     {
		       ex.printStackTrace();
		       Log.d(TAG, "writing " + ex);
		     }
	}
	
	public static void getObject(Context context){
		   Log.d(TAG, "at start");
		   FileInputStream fis = null;
		   ObjectInputStream in = null;
		   TourDataPoint thePoint = null;
		   ReferDataPoint referPoint = null;
		   Object somePoint = null;
		   Log.d(TAG, "before try");
		   try
		   {

			   File topLevelDir = context.getDir(PATH, Context.MODE_WORLD_READABLE);
			   Log.d(TAG, "reading topLevelDir " + topLevelDir);
			   String[] files = topLevelDir.list();
			   Log.d(TAG, "files.length " + files.length);
			   File inFile = new File(topLevelDir, files[0]);
			   
			   fis = new FileInputStream(inFile);
		       in = new ObjectInputStream(fis);
		       somePoint = in.readObject();
		       if (somePoint instanceof ReferDataPoint){ // must check refer first all refers are tours
		    	   referPoint = (ReferDataPoint)somePoint;
		       } else if (somePoint instanceof TourDataPoint){
		    	   thePoint = (TourDataPoint)somePoint;
		       }
		       in.close();
		   }
		   catch(IOException ex)
		   {
		      ex.printStackTrace();
		      Log.d(TAG, "IO " +ex);
		   }
		   catch(ClassNotFoundException ex)
		   {
		      ex.printStackTrace();
		      Log.d(TAG, "ClassNotFound " +ex);
		   }
		   catch(Exception ex){
			      ex.printStackTrace();
			      Log.d(TAG, "exception " +ex);
		   }
		   if (thePoint != null){
	    	   Log.d(TAG, "thePoint> " + thePoint.getClientID() + " " + thePoint.getUserId());
		   }
		   
		   if (referPoint != null){
	    	   Log.d(TAG, "referPoint> " + referPoint.getClientID() + " " + referPoint.getSetPoint());
		   }
	}
		
}
	


