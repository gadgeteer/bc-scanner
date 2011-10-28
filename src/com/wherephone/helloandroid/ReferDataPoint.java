package com.wherephone.helloandroid;

//needs different toString

//data saver and sender need no methods for this type that call old string methods

public class ReferDataPoint extends TourDataPoint {
	
	private String setPoint;
	private String actualTemp;
	private String trailerNumber;

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
	
}
