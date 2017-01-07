package com.allergyiap.beans;

public class Station implements java.io.Serializable{

	private static final long serialVersionUID = 2L;
	
	private long idstation;
	private String name_station;
	private double latitude;
	private double longitude;
	
	public Station(){}
	
	public Station(long idstation, String name_station, double latitude, double longitude){
		this.idstation = idstation;
		this.name_station = name_station;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void setIdstation(long idstation){
		this.idstation = idstation;
	}
	public long getIdstation(){
		return this.idstation;
	}
	
	public void setName_station(String name_station){
		this.name_station = name_station;
	}
	public String getName_station(){
		return this.name_station;
	}
	
	public void setLatitude(double latitude){
		this.latitude = latitude;
	}
	public double getLatitude(){
		return this.latitude;
	}
	
	public void setLongitude(double longitude){
		this.longitude = longitude;
	}
	public double getLongitude(){
		return this.longitude;
	}
}
