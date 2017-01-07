package com.allergyiap.beans;

public class Allergy implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long idallergy;
	private String allergy_name;
	private String allergy_description;
	private String allergy_code;

	public Allergy() {
	}

	public Allergy(long id, String allergyName, String allergyDescription, String allergyCode) {
		this.idallergy = id;
		this.allergy_name = allergyName;
		this.allergy_description = allergyDescription;
		this.allergy_code = allergyCode;
	}

	public long getIdallergy() {
		return idallergy;
	}

	public void setIdallergy(long id) {
		this.idallergy = id;
	}

	public String getAllergy_name() {
		return allergy_name;
	}

	public void setAllergy_name(String allergyName) {
		this.allergy_name = allergyName;
	}

	public String getAllergy_description() {
		return allergy_description;
	}

	public void setAllergy_description(String allergyDescription) {
		this.allergy_description = allergyDescription;
	}

	public String getAllergy_code() {
		return allergy_code;
	}

	public void setAllergy_code(String allergyCode) {
		this.allergy_code = allergyCode;
	}

}
