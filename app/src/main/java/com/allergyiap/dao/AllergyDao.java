package com.allergyiap.dao;

import android.database.SQLException;
import com.allergyiap.db.ResultSet;

import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.Allergy;

public class AllergyDao extends Dao<Allergy> {

	private static final String TABLE_NAME = "allergy";

	private static String idallergy = "idallergy";
	private static String allergy_name = "allergy_name";
	private static String allergy_description = "allergy_description";
	private static String allergy_code = "allergy_code";

	/**
	 * 
	 */
	@Override
	public void insert(Allergy bean) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(allergy_name + ", ");
		query.append(allergy_description + ", ");
		query.append(allergy_code + " ");
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append(bean.getAllergy_name() + ", ");
		query.append(bean.getAllergy_description() + ", ");
		query.append(bean.getAllergy_code() + " ");
		query.append(") ");

		db.executeUpdate(query.toString());
	}

	@Override
	public void update(Allergy bean) {

		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" set ");
		query.append(allergy_code + " = " + bean.getAllergy_code() + ", ");
		query.append(allergy_name + " = " + bean.getAllergy_name() + ", ");
		query.append(allergy_description + " = " + bean.getAllergy_description() + " ");
		query.append(" WHERE ");
		query.append(idallergy + " = " + bean.getIdallergy());

		db.executeUpdate(query.toString());
	}

	@Override
	public void delete(int id) {

		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(idallergy + " = " + id);

		db.executeUpdate(query.toString());
	}

	@Override
	public List<Allergy> getAll() {

		String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";
		return select(selectQuery);
	}

	private List<Allergy> select(String query) {
		List<Allergy> list = new ArrayList<>();

		try {

			ResultSet rs = db.execute(query);
			while (rs.next()) {

				long id = rs.getLong(idallergy);
				String name = rs.getString(allergy_name);
				String description = rs.getString(allergy_description);
				String code = rs.getString(allergy_code);

				list.add(new Allergy(id, name, description, code));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Allergy get(long id) {

		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + idallergy + " = " + id + ";";
		List<Allergy> customers = select(selectQuery);
		return customers.isEmpty() ? null : customers.get(0);
	}

}
