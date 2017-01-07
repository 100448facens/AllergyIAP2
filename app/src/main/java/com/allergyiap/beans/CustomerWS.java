package com.allergyiap.beans;

public class CustomerWS implements java.io.Serializable{
	private static final long serialVersionUID = 2L;

	private long idcustomer;
	private String company_name;
	private String url_logo;
	private String company_description;
	
	public CustomerWS(Customer customer){
		this.idcustomer = customer.getIdcustomer();
		this.company_name = customer.getCompany_name();
		this.url_logo = customer.getUrl_logo();
		this.company_description = customer.getCompany_description();
	}

	public CustomerWS() {

	}

	public long getIdcustomer() {
		return idcustomer;
	}

	public void setIdcustomer(long idcustomer) {
		this.idcustomer = idcustomer;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String companyName) {
		this.company_name = companyName;
	}
	
	public String getUrl_logo() {
		return url_logo;
	}

	public void setUrl_logo(String url_logo) {
		this.url_logo = url_logo;
	}
	
	public String getCompany_description() {
		return company_description;
	}

	public void setCompany_description(String company_description) {
		this.company_description = company_description;
	}
}
