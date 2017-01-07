package com.allergyiap.dao;

import android.database.SQLException;
import com.allergyiap.db.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.allergyiap.service.PharmacyService;
import com.allergyiap.service.RelationPharmaciesCustomersService;
import com.allergyiap.beans.Pharmacy;

public class PharmacyDao extends Dao<Pharmacy> {

	private static final String TABLE_NAME = "pharmacy";

	private String id_pharmacy = "id_pharmacy";
	private String name_pharmacy = "name_pharmacy";
	private String latitude = "latitude";
	private String longitude = "longitude";

	@Override
	public void insert(Pharmacy bean) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(name_pharmacy + ", ");
		query.append(latitude + ", ");
		query.append(longitude + " ");
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append("'" + bean.getName_pharmacy() + "', ");
		query.append("'" + bean.getLatitude() + "', ");
		query.append("'" + bean.getLongitude() + "' ");
		query.append(") ");

		db.executeUpdate(query.toString());
	}

	@Override
	public void update(Pharmacy bean) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" set ");
		query.append(name_pharmacy + " = '" + bean.getName_pharmacy() + "', ");
		query.append(latitude + " = '" + bean.getLatitude() + "', ");
		query.append(longitude + " = '" + bean.getLongitude() + "' ");
		query.append(" WHERE ");
		query.append(id_pharmacy + " = " + bean.getId_pharmacy());

		db.executeUpdate(query.toString());
	}

	@Override
	public void delete(int id) {
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(id_pharmacy + " = " + id);

		db.executeUpdate(query.toString());

	}

	@Override
	public List<Pharmacy> getAll() {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";
		return select(selectQuery);
	}

	private List<Pharmacy> select(String query) {
		List<Pharmacy> list = new ArrayList<>();
		try {

			ResultSet rs = db.execute(query);
			while (rs.next()) {
				long idp = rs.getLong(id_pharmacy);
				String name = rs.getString(name_pharmacy);
				double lat = rs.getDouble(latitude);
				double lon = rs.getDouble(longitude);
				list.add(new Pharmacy(idp, name, lat, lon));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	public List<Pharmacy> getNewPharmacies(long idcustomer){
		List<Pharmacy> p = new ArrayList<>();
		try {
			Set<Pharmacy> foo = new HashSet<Pharmacy>(RelationPharmaciesCustomersService.getPharmaciesByCustomer(idcustomer)) ;
			Set<Pharmacy> all = new HashSet<Pharmacy>(PharmacyService.getAll());
			for(Pharmacy m : all){
				if(!foo.contains(m)){
					p.add(m);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return p;
	}
	
	public Pharmacy get(long id_pharmacy){
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE id_pharmacy = " + id_pharmacy + ";";
		List<Pharmacy> pharmacies = select(selectQuery);
		return pharmacies.isEmpty() ? null : pharmacies.get(0);
	}
}
