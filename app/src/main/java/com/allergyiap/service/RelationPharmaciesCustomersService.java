package com.allergyiap.service;

import java.util.List;

import com.allergyiap.beans.Pharmacy;
import com.allergyiap.beans.RelationPharmaciesCustomers;
import com.allergyiap.dao.RelationPharmaciesCustomersDao;

public class RelationPharmaciesCustomersService {
	private static RelationPharmaciesCustomersDao dao = new RelationPharmaciesCustomersDao();
	
	public static void insert(RelationPharmaciesCustomers bean){
		dao.insert(bean);
	}
	
	public static void update(RelationPharmaciesCustomers bean){
		dao.update(bean);
	}
	
	public static void delete(int idcustomer, int id_pharmacy){
		dao.delete(idcustomer, id_pharmacy);
	}
	
	public static List<Pharmacy> getPharmaciesByCustomer(long id_customer) {
		return dao.getPharmaciesByCustomer(id_customer);
	}
	
	public static List<Integer> getCustomersByPharmacy(long id_pharmacy) {
		return dao.getCustomersByPharmacy(id_pharmacy);
	}
	
	public static List<RelationPharmaciesCustomers> getAll() {
		return dao.getAll();
	}
}
