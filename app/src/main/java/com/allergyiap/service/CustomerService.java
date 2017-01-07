package com.allergyiap.service;

import java.util.List;

import com.allergyiap.beans.Customer;
import com.allergyiap.dao.CustomerDao;

public class CustomerService {

	private static CustomerDao dao = new CustomerDao();
	
	public static void insert(Customer bean){
		dao.insert(bean);
	}
	
	public static void update(Customer bean){
		dao.update(bean);
	}
	
	public static void delete(int idcustomer){
		dao.delete(idcustomer);
	}
	
	public static Customer get(long idcustomer) {
		return dao.get(idcustomer);
	}
	
	public static Customer get(String name, String password) {
		return dao.get(name, password);
	}
	
	public static List<Customer> getAll() {
		return dao.getAll();
	}
	
}
