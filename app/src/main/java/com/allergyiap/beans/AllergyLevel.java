package com.allergyiap.beans;

public class AllergyLevel implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;

	private long idallergy_level;
	private long allergy_idallergy;
	private float current_level;
	private String station;
	private String date_start;
	private String date_end;
	private String forecast_level;
	
	
	public AllergyLevel(long id, long allergyIdAllergy, float currentLevel, String station, String dateStart, String dateEnd, String forecastLevel ){
		this.idallergy_level = id;
		this.allergy_idallergy = allergyIdAllergy;
		this.current_level = currentLevel;
		this.station = station;
		this.date_start = dateStart;
		this.date_end = dateEnd;
		this.forecast_level = forecastLevel;
	}
	
	public long getIdallergy_level(){
		return this.idallergy_level;
	}
	public long getAllergy_idallergy(){
		return this.allergy_idallergy;
	}
	
	public float getCurrent_level(){
		return this.current_level;
	}
	
	public String getStation(){
		return this.station;
	}
	
	public String getDate_start(){
		return this.date_start;
	}
	
	public String getDate_end(){
		return this.date_end;
	}
	
	public String getForecast_level(){
		return this.forecast_level;
	}
	
	public static long getSerialversionUId() {
		return serialVersionUID;
	}
	
	
	public void setIdallergy_level(long id){
		this.idallergy_level = id;
	}
	
	public void setAllergy_idallergy(long allergyIdAllergy){
		this.allergy_idallergy = allergyIdAllergy;
	}
	
	public void setCurrent_level(float currentLevel){
		this.current_level = currentLevel;
	}
	
	public void setStation(String station){
		this.station = station;
	}
	
	public void setdate_start(String dateStart){
		this.date_start = dateStart;
	}
	
	public void setDate_end(String dateEnd){
		this.date_end = dateEnd;
	}
	
	public void setForecast_level(String forecastLevel){
		this.forecast_level = forecastLevel;
	}	
	
}
