package com.allergyiap.dao;

import android.database.SQLException;
import com.allergyiap.db.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.Customer;

public class CustomerDao extends Dao<Customer> {

	public CustomerDao() {
		super(TABLE_NAME);
	}
	private static final String TABLE_NAME = "customer";

	private static String idcustomer = "idcustomer";
	private static String user_mail = "user_mail";
	private static String user_password = "user_password";
	private static String company_name = "company_name";
	private static String url_logo = "url_logo";
	private static String company_description = "company_description";

	/**
	 * 
	 */
	@Override
	public void insert(Customer bean) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(user_mail + ", ");
		query.append(user_password + ", ");
		query.append(company_name + ", ");
		query.append(url_logo + ", ");
		query.append(company_description);
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append("'" + bean.getUser_mail() + "', ");
		query.append("'" + bean.getUser_password() + "', ");
		query.append("'" + bean.getCompany_name() + "',");
		query.append("'" + bean.getUrl_logo() + "',");
		query.append("'" + bean.getCompany_description() + "'");
		query.append(") ");

		db.executeUpdate(query.toString());
	}

	@Override
	public void update(Customer bean) {

		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" set ");
		query.append(company_name + " = '" + bean.getCompany_name() + "', ");
		query.append(user_mail + " = '" + bean.getUser_mail() + "', ");
		query.append(user_password + " = '" + bean.getUser_password() + "',");
		query.append(url_logo + " = '" + bean.getUrl_logo() + "',");
		query.append(company_description + " = '" + bean.getCompany_description() + "'");
		query.append(" WHERE ");
		query.append(idcustomer + " = " + bean.getIdcustomer());

		db.executeUpdate(query.toString());
	}

	@Override
	public void delete(int id) {

		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(idcustomer + " = " + id);

		db.executeUpdate(query.toString());
	}

	@Override
	public List<Customer> getAll() {
		this.updateFromWS(1);

		String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";
		return select(selectQuery);
	}

	private List<Customer> select(String query) {
		List<Customer> list = new ArrayList<>();
		try {

			ResultSet rs = db.execute(query);
			while (rs.next()) {
				long id = rs.getLong(idcustomer);
				//String mail = rs.getString(user_mail);
				//String pass = rs.getString(user_password);
				String name = rs.getString(company_name);
				String url = rs.getString(url_logo);
				String description = rs.getString(company_description);
				list.add(new Customer(id, "", "", name, url, description));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public Customer get(long id) {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + idcustomer + " = " + id + ";";
		List<Customer> customers = select(selectQuery);
		Customer c = customers.isEmpty() ? null : customers.get(0);
		return c;
	}

	public Customer get(String name, String password) {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + user_mail + " = '" + name 
				+ "' AND " + user_password + " = '" + password + "';";
		List<Customer> customers = select(selectQuery);
		return customers.isEmpty() ? null : customers.get(0);
	}
}