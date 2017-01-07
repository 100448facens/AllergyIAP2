package com.allergyiap.service;

import java.util.List;

import com.allergyiap.beans.AllergyLevel;
import com.allergyiap.dao.AllergyLevelDao;

public class AllergyLevelService {

private static AllergyLevelDao dao = new AllergyLevelDao();
	
	public static void insert(AllergyLevel bean){
		dao.insert(bean);
	}
	
	public static void update(AllergyLevel bean){
		dao.update(bean);
	}
	
	public static void delete(int id){
		dao.delete(id);
	}
	
	public static AllergyLevel get(long id) {
		return dao.get(id);
	}
	
	public static List<AllergyLevel> getAll() {
		return dao.getAll();
	}

}
