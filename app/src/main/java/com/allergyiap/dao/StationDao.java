package com.allergyiap.dao;

import android.database.SQLException;
import com.allergyiap.db.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.Station;

public class StationDao extends Dao<Station> {

	private static final String TABLE_NAME = "station";

	private static String idstation = "idstation";
	private static String name_station = "name_station";
	private static String latitude = "latitude";
	private static String longitude = "longitude";

	public StationDao() {
		super(TABLE_NAME);
	}
	@Override
	public void insert(Station bean) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(name_station + ", ");
		query.append(latitude + ", ");
		query.append(longitude + " ");
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append("'" + bean.getName_station() + "', ");
		query.append("'" + bean.getLatitude() + "', ");
		query.append("'" + bean.getLongitude() + "' ");
		query.append(") ");

		db.executeUpdate(query.toString());
	}

	@Override
	public void update(Station bean) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" set ");
		query.append(name_station + " = '" + bean.getName_station() + "', ");
		query.append(latitude + " = '" + bean.getLatitude() + "', ");
		query.append(longitude + " = '" + bean.getLongitude() + "' ");
		query.append(" WHERE ");
		query.append(idstation + " = " + bean.getIdstation());

		db.executeUpdate(query.toString());

	}

	@Override
	public List<Station> getAll() {
		this.updateFromWS(1);

		String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";
		return select(selectQuery);
	}

	private List<Station> select(String query) {
		List<Station> list = new ArrayList<>();
		try {

			ResultSet rs = db.execute(query);
			while (rs.next()) {
				long id = rs.getLong(idstation);
				String name = rs.getString(name_station);
				double lat = rs.getDouble(latitude);
				double lon = rs.getDouble(longitude);
				list.add(new Station(id, name, lat, lon));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public Station get(String name) {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE name_station = '" + name + "';";
		List<Station> stations = select(selectQuery);
		return stations.isEmpty() ? null : stations.get(0);
	}
	
	public Station get(int id) {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE idstation = '" + id + "';";
		List<Station> stations = select(selectQuery);
		return stations.isEmpty() ? null : stations.get(0);
	}

	@Override
	public void delete(int id) {
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append( idstation+ " = " + id);

		db.executeUpdate(query.toString());
	}

}
