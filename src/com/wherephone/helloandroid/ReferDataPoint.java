package com.wherephone.helloandroid;

//needs different toString

//data saver and sender need no methods for this type that call old string methods

public class ReferDataPoint extends TourDataPoint {
	
	private String setPoint;
	private String actualTemp;
	private String trailerNumber;
	private String referStatus;
	private String trailerStatus;
	private String fuelLevel;


	ReferDataPoint(String user, String client){
		super(user,client);
	}
	// change on refer branch
	public String getSendableData(){
		StringBuffer returnString = new StringBuffer();
		
		
		
		return returnString.toString();	
	}

	public void setSetPoint(String setPoint) {
		this.setPoint = setPoint;
	}

	public String getSetPoint() {
		return setPoint;
	}

	public void setActualTemp(String actualTemp) {
		this.actualTemp = actualTemp;
	}

	public String getActualTemp() {
		return actualTemp;
	}

	public void setTrailerNumber(String trailerNumber) {
		this.trailerNumber = trailerNumber;
	}

	public String getTrailerNumber() {
		return trailerNumber;
	}
	public void setTrailerStatus(String trailerStatus) {
		this.trailerStatus = trailerStatus;
	}
	public String getTrailerStatus() {
		return trailerStatus;
	}
	public void setReferStatus(String referStatus) {
		this.referStatus = referStatus;
	}
	public String getReferStatus() {
		return referStatus;
	}
	public String getFuelLevel() {
		return fuelLevel;
	}
	public void setFuelLevel(String fuelLevel) {
		this.fuelLevel = fuelLevel;
	}
	
	public String toString(){
		return "trailer:" + this.trailerNumber + " setPt:" + this.setPoint + " act:"  + this.actualTemp +
				" referStatus:" + this.referStatus + " trailerStatus:" + this.trailerStatus +" fuel:" + this.fuelLevel;
	}
	
}
