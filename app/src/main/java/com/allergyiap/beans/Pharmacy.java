package com.allergyiap.beans;

public class Pharmacy implements java.io.Serializable{
	
	private static final long serialVersionUID = 2L;

	private long id_pharmacy;
	private String name_pharmacy;
	private double latitude;
	private double longitude;
	
	public Pharmacy(){}
	
	public Pharmacy(long id_pharmacy, String name_pharmacy, double latitude, double longitude){
		this.id_pharmacy = id_pharmacy;
		this.name_pharmacy = name_pharmacy;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Pharmacy(String name_pharmacy, double latitude, double longitude){
		this.name_pharmacy = name_pharmacy;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void setId_pharmacy(long id_pharmacy){
		this.id_pharmacy = id_pharmacy;
	}
	
	public long getId_pharmacy(){
		return this.id_pharmacy;
	}
	
	public void setName_pharmacy(String name_pharmacy){
		this.name_pharmacy = name_pharmacy;
	}
	
	public String getName_pharmacy(){
		return this.name_pharmacy;
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
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + (int)id_pharmacy;
	    result = prime * result + ((name_pharmacy == null) ? 0 : name_pharmacy.hashCode());
	    result = prime * result + (int)latitude;
	    result = prime * result + (int)longitude;;
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Pharmacy other = (Pharmacy) obj;
	    if (id_pharmacy != other.getId_pharmacy()){
	    	return false;
	    }
	    if (name_pharmacy == null) {
	        if (other.name_pharmacy != null)
	            return false;
	    } else if (!name_pharmacy.equals(other.name_pharmacy))
	        return false;
	    if (latitude != other.getLatitude()){
	    	return false;
	    }
	    if (longitude != other.getLongitude()){
	    	return false;
	    }
	    return true;
	}
}
