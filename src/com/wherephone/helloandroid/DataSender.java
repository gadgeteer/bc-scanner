package com.wherephone.helloandroid;

import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;


public class DataSender {
	private static final String TAG = "DataSender";
	private static final String DEFAULT_URL = 
		"http://www.rmprogram4.com/tp2010/savePhoneData.php?data=";
	
	public static String send(String data){
		//new Thread(new DataSender(data)).start();	
		return httpGet(data, DEFAULT_URL);
	}
	
	
	private static String httpGet(String data, String url) {
		
		HttpResponse response = null;
		InputStream is = null;
		try {
			
			Log.d(TAG,"start lastScan data=" + data);
			
			String encodedData = URLEncoder.encode(data, "utf-8");
			
			HttpParams httpParameters = new BasicHttpParams();
			// Set the timeout in milliseconds until a connection is established.
			int timeoutConnection = 7000;
			HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
			// Set the default socket timeout (SO_TIMEOUT) 
			// in milliseconds which is the timeout for waiting for data.
			int timeoutSocket = 95000;
			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
			
			HttpClient httpclient = new DefaultHttpClient(httpParameters);
			Log.d(TAG,"created client");		
			HttpGet httpget = new HttpGet(url + encodedData);
			Log.d(TAG,"created get");
			
			response = httpclient.execute(httpget);
			Log.d(TAG,"executed GET");
			HttpEntity entity = response.getEntity();
			
			is = entity.getContent() ;
			int ch;
			int numBytes = 0;
	        StringBuffer sb = new StringBuffer(); 
	        while( ( ch = is.read() ) != -1 ) { 
	            sb.append( (char)ch ); 
	            numBytes++;
	        } 
			
			Log.d(TAG,"numBytes=" + numBytes);
			
			Log.d(TAG,"resp>" + sb);
			
		} catch (Exception e) {
			Log.d(TAG,"httpGet Exception");
			Log.d(TAG,e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			
	        try {
	            if (is != null)
	                is.close();
	            } catch (Exception e) {}
		}
		
		if (response != null){
		  return response.getStatusLine().getStatusCode()+"";
		} else {
		  return "problem";
		}

	}	
	

}
