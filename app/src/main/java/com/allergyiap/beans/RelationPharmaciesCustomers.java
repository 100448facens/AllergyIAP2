package com.allergyiap.beans;

public class RelationPharmaciesCustomers implements java.io.Serializable {
	
	private static final long serialVersionUID = 2L;
	
	private long id_pharmacy;
	private long id_customer;

	public RelationPharmaciesCustomers(long id_pharmacy, long id_customer){
		this.id_pharmacy = id_pharmacy;
		this.id_customer = id_customer;
	}
	
	public long getId_customer() {
		return id_customer;
	}

	public void setId_customer(long id_customer) {
		this.id_customer = id_customer;
	}
	
	public long getId_pharmacy() {
		return id_pharmacy;
	}

	public void setId_pharmacy(long id_pharmacy) {
		this.id_pharmacy = id_pharmacy;
	}
}
