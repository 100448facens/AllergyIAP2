package com.allergyiap.dao;

import android.database.SQLException;
import com.allergyiap.db.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.User;

public class UserDao extends Dao<User>{
	
	private static final String TABLE_NAME = "user_information";

	private static String iduser = "iduser";
	private static String user_name = "user_name";
	private static String user_second_name = "user_second_name";
	private static String user_mail = "user_mail";
	private static String user_password = "user_password";
	private static String user_station_default = "user_station_default";
	private static String alarm_weekdays = "alarm_weekdays";
	private static String alarm_time = "alarm_time";

	public UserDao() {
		super(TABLE_NAME);
	}

	@Override
	public void insert(User bean) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(user_name + ", ");
		query.append(user_second_name + ", ");
		query.append(user_mail + ", ");
		query.append(user_password + ", ");
		query.append(user_station_default + ", ");
		query.append(alarm_weekdays + ", ");
		query.append(alarm_time + " ");
		query.append(") ");
		query.append("VALUES");
		query.append(" ('");
		query.append(bean.getUser_name() + "', '");
		query.append(bean.getUser_second_name() + "', '");
		query.append(bean.getUser_mail() + "', '");
		query.append(bean.getUser_password() + "', ");
		query.append(bean.getUser_station_default() + ", '");
		query.append(bean.getAlarm_weekdays() + "', '");
		query.append(bean.getAlarm_time() + "'");
		query.append(") ");
		db.executeUpdate(query.toString());
		
	}

	@Override
	public void update(User bean) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" SET ");
		query.append(user_name + " = '" + bean.getUser_name() + "', ");
		query.append(user_second_name + " = '" + bean.getUser_second_name() + "', ");
		query.append(user_mail + " = '" + bean.getUser_mail() + "', ");
		query.append(user_password + " = '" + bean.getUser_password() + "', ");
		query.append(user_station_default +"= "+bean.getUser_station_default() + ", ");
		query.append(alarm_weekdays + " = '" + bean.getAlarm_weekdays() + "', ");
		query.append(alarm_time + " = '" + bean.getAlarm_time() + "' ");
		query.append(" WHERE ");
		query.append(iduser + " = " + bean.getIduser());

		db.executeUpdate(query.toString());
		
	}

	@Override
	public void delete(int id) {
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(iduser + " = " + id);

		db.executeUpdate(query.toString());
		
	}

	@Override
	public List<User> getAll() {
		this.updateFromWS(1);

		String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";
		return select(selectQuery);
	}
	
	private List<User> select(String query) {
		List<User> list = new ArrayList<>();

		try {

			ResultSet rs = db.execute(query);
			while (rs.next()) {

				long id = rs.getLong(iduser);
				String name = rs.getString(user_name);
				String secondName = rs.getString(user_second_name);
				String mail = rs.getString(user_mail);
				String password = rs.getString(user_password);
				int location = rs.getInt(user_station_default);
				String weekdays = rs.getString(alarm_weekdays);
				String time = rs.getTime(alarm_time).toString();

				list.add(new User(id, name, secondName, mail, password, location, weekdays, time));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public User get(long id) {

		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + iduser + " = " + id + ";";
		List<User> users = select(selectQuery);
		return users.isEmpty() ? null : users.get(0);
	}
	
	public User isValidLogin(String mail, String password){
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + user_mail + " = '" + mail + "' AND "+ user_password + " = '" + password+"' ;";
		List<User> users = select(selectQuery);
		return users.isEmpty() ? null : users.get(0);
	}

}
