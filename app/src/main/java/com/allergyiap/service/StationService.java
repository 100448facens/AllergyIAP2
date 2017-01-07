package com.allergyiap.service;

import java.util.List;

import com.allergyiap.beans.Station;
import com.allergyiap.dao.StationDao;

public class StationService {
	private static StationDao dao = new StationDao();
	
	public static void insert(Station bean){
		dao.insert(bean);
	}
	
	public static void update(Station bean){
		dao.update(bean);
	}
	
	public static void delete(int id){
		dao.delete(id);
	}
	
	public static Station get(String name) {
		return dao.get(name);
	}
	
	public static Station get(int id) {
		return dao.get(id);
	}
	
	public static List<Station> getAll() {
		return dao.getAll();
	}
}
