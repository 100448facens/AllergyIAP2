package com.allergyiap.dao;

import android.database.SQLException;
import com.allergyiap.db.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.ProductCatalog;

public class ProductCatalogDao extends Dao<ProductCatalog> {

	private static final String TABLE_NAME = "product_catalog";

	private static String idproduct_catalog = "idproduct_catalog";
	private static String allergy_idallergy = "allergy_idallergy";
	private static String customer_idcustomer = "customer_idcustomer";
	private static String product_name = "product_name";
	private static String product_description = "product_description";
	private static String product_url_image = "product_url_image";

	/**
	 * 
	 */
	@Override
	public void insert(ProductCatalog bean) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(allergy_idallergy + ", ");
		query.append(customer_idcustomer + ", ");
		query.append(product_name + ", ");
		query.append(product_description + ", ");
		query.append(product_url_image + " ");
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append("'" + bean.getAllergy_idallergy() + "', ");
		query.append("'" + bean.getCustomer_idcustomer() + "', ");
		query.append("'" + bean.getProduct_name() + "', ");
		query.append("'" + bean.getProduct_description() + "', ");
		query.append("'" + bean.getProduct_url_image() + "'");
		query.append(") ");

		db.executeUpdate(query.toString());
	}

	@Override
	public void update(ProductCatalog bean) {

		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" set ");
		query.append(allergy_idallergy + " = '" + bean.getAllergy_idallergy() + "', ");
		query.append(customer_idcustomer + " = '" + bean.getCustomer_idcustomer() + "', ");
		query.append(product_name + " = '" + bean.getProduct_name() + "', ");
		query.append(product_description + " = '" + bean.getProduct_description() + "', ");
		query.append(product_url_image + " = '" + bean.getProduct_url_image() + "'");
		query.append(" WHERE ");
		query.append(idproduct_catalog + " = " + bean.getIdproduct_catalog());

		db.executeUpdate(query.toString());
	}

	@Override
	public void delete(int id) {

		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(idproduct_catalog + " = " + id);

		db.executeUpdate(query.toString());
	}

	@Override
	public List<ProductCatalog> getAll() {

		String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";
		return select(selectQuery);
	}

	private List<ProductCatalog> select(String query) {
		List<ProductCatalog> list = new ArrayList<>();

		try {

			ResultSet rs = db.execute(query);
			while (rs.next()) {

				long idlevel = rs.getLong(idproduct_catalog);
				long idallergy = rs.getLong(allergy_idallergy);
				long idcustomer = rs.getLong(customer_idcustomer);
				String name = rs.getString(product_name);
				String description = rs.getString(product_description);
				String url = rs.getString(product_url_image);

				list.add(new ProductCatalog(idlevel, idallergy, idcustomer, name, description, url));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	/* From a customerID, select all his products in the catalog */
	public List<ProductCatalog> getByCustomer(long id) {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + customer_idcustomer + " = " + id + ";";
		return select(selectQuery);
	}

	public ProductCatalog get(long id) {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + idproduct_catalog + " = " + id + ";";
		List<ProductCatalog> list = select(selectQuery);
		return list.isEmpty() ? null : list.get(0);
	}
}
