package com.allergyiap.dao;

import android.database.SQLException;
import com.allergyiap.db.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.AllergyLevel;

public class AllergyLevelDao extends Dao<AllergyLevel> {

	private static final String TABLE_NAME = "allergy_level";
	private static String idallergy_level = "idallergy_level";
	private static String allergy_idallergy = "allergy_idallergy";
	private static String current_level = "current_level";
	private static String station = "station";
	private static String date_start = "date_start";
	private static String date_end = "date_end";
	private static String forecast_level = "forecast_level";

	/**
	 * 
	 * @param bean
	 */
	@Override
	public void insert(AllergyLevel bean) {

		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(allergy_idallergy + ", ");
		query.append(current_level + ", ");
		query.append(station + ", ");
		query.append(date_start + ", ");
		query.append(date_end + ", ");
		query.append(forecast_level);
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append(bean.getAllergy_idallergy() + ", ");
		query.append(bean.getCurrent_level() + ", ");
		query.append(bean.getStation() + ", ");
		query.append(bean.getDate_start() + ", ");
		query.append(bean.getDate_end() + ", ");
		query.append(bean.getForecast_level());
		query.append(") ");

		db.executeUpdate(query.toString());
	}

	/**
	 * 
	 */
	@Override
	public void delete(int id) {

		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(idallergy_level + " = " + id);

		db.executeUpdate(query.toString());
	}

	/**
	 * 
	 * @param bean
	 */
	@Override
	public void update(AllergyLevel bean) {

		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" set ");
		query.append(allergy_idallergy + " = " + bean.getAllergy_idallergy() + ", ");
		query.append(current_level + " = " + bean.getCurrent_level() + ", ");
		query.append(station + " = " + bean.getStation() + ", ");
		query.append(date_start + " = " + bean.getDate_start() + ", ");
		query.append(date_end + " = " + bean.getDate_end() + ", ");
		query.append(forecast_level + " = " + bean.getForecast_level());
		query.append(" WHERE ");
		query.append(idallergy_level + " = " + bean.getIdallergy_level());

		db.executeUpdate(query.toString());
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public List<AllergyLevel> getAll() {

		String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";

		return select(selectQuery);
	}

	public List<AllergyLevel> getByStation(long stationid) {
		String selectQuery = "SELECT allergy_level.* FROM allergy_level INNER JOIN station ON station.name_station = allergy_level.station INNER JOIN user_allergies ON user_allergies.id_allergy = allergy_level.allergy_idallergy INNER JOIN user_information ON user_information.iduser = user_allergies.id_user WHERE station.idstation = "+stationid+" AND DATE('NOW') BETWEEN allergy_level.date_start AND allergy_level.date_end GROUP BY allergy_level.allergy_idallergy";
		return select(selectQuery);
	}

	public AllergyLevelDao() {
		super(TABLE_NAME);
	}
	private List<AllergyLevel> select(String query) {
		this.updateFromWS(1);

		List<AllergyLevel> list = new ArrayList<>();

		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			ResultSet rs = db.execute(query);
			while (rs.next()) {

				long idlevel = rs.getLong(idallergy_level);
				long idallergy = rs.getLong(allergy_idallergy);
				float curlevel = rs.getFloat(current_level);
				String stationLevel = rs.getString(station);
				String dateStart = df.format(rs.getDate(date_start));
				String dateEnd = df.format(rs.getDate(date_end));
				String forecastLevel = rs.getString(forecast_level);

				list.add(new AllergyLevel(idlevel, idallergy, curlevel, stationLevel, dateStart, dateEnd, forecastLevel));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public AllergyLevel get(long id) {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + idallergy_level + " = " + id + ";";
		List<AllergyLevel> allergieslevel = select(selectQuery);
		return allergieslevel.isEmpty() ? null : allergieslevel.get(0);
	}

}
