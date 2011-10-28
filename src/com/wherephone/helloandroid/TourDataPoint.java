package com.wherephone.helloandroid;

import java.io.Serializable;
import java.util.Date;


public class TourDataPoint implements Serializable{


	Date dateCreated = new java.util.Date();
	String date = (String) android.text.format.DateFormat.format("\"MM-dd-yyyy\",\"hh:mm:ss\"", new java.util.Date());


	protected String userId;
	protected String clientID;
	private String scanDataType;
	private String scanData;
	private String selectedData;
	private String enteredData;

	
	
	TourDataPoint(String user, String client){

		this.setUserId(user);
		this.setClientID(client);
	}
	
	TourDataPoint(String user, String client, String scan ){
		this(user, client);
		this.setScanData(scan);
	}
	
	TourDataPoint(String user, String client, String scan, String selected ){
		this(user, client, scan);
		this.setSelectedData(selected);
	}
	
	public String getSendableData(){
		StringBuffer returnString = new StringBuffer();
		
		return returnString.toString();
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getClientID() {
		return clientID;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setScanDataType(String scanDataType) {
		this.scanDataType = scanDataType;
	}

	public String getScanDataType() {
		return scanDataType;
	}

	public void setScanData(String scanData) {
		this.scanData = scanData;
	}

	public String getScanData() {
		return scanData;
	}

	public void setSelectedData(String enteredData) {
		this.selectedData = enteredData;
	}

	public String getSelectedData() {
		return selectedData;
	}

	public void setEnteredData(String entereData) {
		this.enteredData = entereData;
	}

	public String getEnteredData() {
		return enteredData;
	}
	
	
	
	
	
}
