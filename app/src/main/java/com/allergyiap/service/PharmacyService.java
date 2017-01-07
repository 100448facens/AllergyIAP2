package com.allergyiap.service;

import java.util.List;

import com.allergyiap.beans.Pharmacy;
import com.allergyiap.dao.PharmacyDao;

public class PharmacyService {

	private static PharmacyDao dao = new PharmacyDao();

	public static void insert(Pharmacy bean) {
		dao.insert(bean);
	}

	public static void update(Pharmacy bean) {
		dao.update(bean);
	}

	public static void delete(int id_pharmacy) {
		dao.delete(id_pharmacy);
	}

	public static Pharmacy get(long id_pharmacy) {
		return dao.get(id_pharmacy);
	}
	
	public static List<Pharmacy> getNewPharmacies(int id_customer){
		return dao.getNewPharmacies(id_customer);
	}

	public static List<Pharmacy> getAll() {
		return dao.getAll();
	}
}
