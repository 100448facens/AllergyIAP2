package com.allergyiap.beans;

public class UserAllergy implements java.io.Serializable {
	
	private static final long serialVersionUID = 2L;
	
	private long id_user;
	private long id_allergy;

	public UserAllergy(long id_user, long id_allergy){
		this.id_user = id_user;
		this.id_allergy = id_allergy;
	}
	
	public UserAllergy(){}
	
	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}
	
	public long getId_allergy() {
		return id_allergy;
	}

	public void setId_allergy(long id_allergy) {
		this.id_allergy = id_allergy;
	}
}
