package com.allergyiap.service;

import java.util.List;

import com.allergyiap.beans.User;
import com.allergyiap.dao.UserDao;
import com.allergyiap.utils.C;
import com.allergyiap.utils.Util;

public class UserService {

private static UserDao dao = new UserDao();
	
	public static void insert(User bean){
		dao.insert(bean);
	}
	
	public static void update(User bean){
		dao.update(bean);
		User u=bean;
		if (u.getIduser() > 0) {
			try {
				Util.getUrlAsync(C.Network.WS_URL + "setnotification/" + u.getUser_name() + "/" + u.getUser_password() + "/W" + u.getAlarm_weekdays() + "/" + u.getAlarm_time() + "/" + u.getDevice_key() + "/" + u.getUser_station_default());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void delete(int id){
		dao.delete(id);
	}
	
	public static User get(long id) {
		return dao.get(id);
	}
	
	public static List<User> getAll() {
		return dao.getAll();
	}
	
	public static User isValidLogin(String mail, String password){
		return dao.isValidLogin(mail, password);
	}

	public static User getCurrentUser(){
		return getAll().get(0);
	}

	public static void setUserId(long id){
		dao.setUserId(id);
	}

}
