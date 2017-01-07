package com.allergyiap.service;

import java.util.List;

import com.allergyiap.beans.User;
import com.allergyiap.dao.UserDao;

public class UserService {

private static UserDao dao = new UserDao();
	
	public static void insert(User bean){
		dao.insert(bean);
	}
	
	public static void update(User bean){
		dao.update(bean);
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

}
