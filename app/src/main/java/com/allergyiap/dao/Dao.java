package com.allergyiap.dao;

import java.util.List;

import com.allergyiap.db.DB;

public abstract class Dao<T> {

	protected DB db = DB.getInstance();

	public abstract void insert(T bean);

	public abstract void update(T bean);

	public abstract void delete(int id);

	public abstract List<T> getAll();

}
