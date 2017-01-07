package com.allergyiap.beans;

public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 2L;

	private long idcustomer;
	private String user_mail;
	private String user_password;
	private String company_name;
	private String url_logo;
	private String company_description;
	
	
	public Customer(long idcustomer, String userMail, String userPassword, String companyName, 
			String url_logo, String company_description) {
		this.idcustomer = idcustomer;
		this.user_mail = userMail;
		this.user_password = userPassword;
		this.company_name = companyName;
		this.url_logo = url_logo;
		this.company_description = company_description;
	}

	public Customer(String userMail, String userPassword, String companyName,
			String url_logo, String company_description) {
		this.user_mail = userMail;
		this.user_password = userPassword;
		this.company_name = companyName;
		this.url_logo = url_logo;
		this.company_description = company_description;
	}

	public Customer() {}

	public long getIdcustomer() {
		return idcustomer;
	}

	public void setIdcustomer(long idcustomer) {
		this.idcustomer = idcustomer;
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
	
	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String userMail) {
		this.user_mail = userMail;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String password) {
		this.user_password = password;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String companyName) {
		this.company_name = companyName;
	}

	public boolean isAdmin(){
		return this.user_mail.equals("admin");
	}
}