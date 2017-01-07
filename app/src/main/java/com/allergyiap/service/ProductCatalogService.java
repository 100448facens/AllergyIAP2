package com.allergyiap.service;

import java.util.List;

import com.allergyiap.beans.ProductCatalog;
import com.allergyiap.dao.AllergyDao;
import com.allergyiap.dao.CustomerDao;
import com.allergyiap.dao.ProductCatalogDao;

public class ProductCatalogService {

	private static ProductCatalogDao productDao = new ProductCatalogDao();
	private static AllergyDao allergyDao = new AllergyDao();
	private static CustomerDao customerDao = new CustomerDao();

	public static void insert(ProductCatalog bean) {
		productDao.insert(bean);
	}

	public static void update(ProductCatalog bean) {
		productDao.update(bean);
	}

	public static void delete(int id) {
		productDao.delete(id);
	}

	public static ProductCatalog get(long id) {
		return productDao.get(id);
	}

	public static List<ProductCatalog> getAllByCustomer(long id) {
		List<ProductCatalog> list = productDao.getByCustomer(id);
		for (ProductCatalog productCatalog : list) {
			productCatalog.setAllergy(allergyDao.get(productCatalog.getAllergy_idallergy()));
			productCatalog.setCustomer(customerDao.get(productCatalog.getCustomer_idcustomer()));
		}
		return list;
	}

	public static List<ProductCatalog> getAllByUserAllergy(long id) {
		List<ProductCatalog> list = productDao.getByUserAllergy(id);
		for (ProductCatalog productCatalog : list) {
			productCatalog.setAllergy(allergyDao.get(productCatalog.getAllergy_idallergy()));
			productCatalog.setCustomer(customerDao.get(productCatalog.getCustomer_idcustomer()));
		}
		return list;
	}

	public static List<ProductCatalog> getAllByAllergy(long id) {
		List<ProductCatalog> list = productDao.getByAllergy(id);
		for (ProductCatalog productCatalog : list) {
			productCatalog.setAllergy(allergyDao.get(productCatalog.getAllergy_idallergy()));
			productCatalog.setCustomer(customerDao.get(productCatalog.getCustomer_idcustomer()));
		}
		return list;
	}

	public static List<ProductCatalog> getAll() {
		List<ProductCatalog> list = productDao.getAll();
		for (ProductCatalog productCatalog : list) {
			productCatalog.setAllergy(allergyDao.get(productCatalog.getAllergy_idallergy()));
			productCatalog.setCustomer(customerDao.get(productCatalog.getCustomer_idcustomer()));
		}
		return list;
	}

}
