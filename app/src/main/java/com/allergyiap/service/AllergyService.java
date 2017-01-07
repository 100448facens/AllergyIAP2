package com.allergyiap.service;

import java.util.List;

import com.allergyiap.beans.Allergy;
import com.allergyiap.dao.AllergyDao;

public class AllergyService {

private static AllergyDao dao = new AllergyDao();
	
	public static void insert(Allergy bean){
		dao.insert(bean);
	}
	
	public static void update(Allergy bean){
		dao.update(bean);
	}
	
	public static void delete(int id){
		dao.delete(id);
	}
	
	public static Allergy get(long id) {
		return dao.get(id);
	}
	
	public static List<Allergy> getAll() {
		return dao.getAll();
	}

}
