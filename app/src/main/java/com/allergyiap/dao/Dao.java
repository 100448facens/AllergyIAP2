package com.allergyiap.dao;

import com.allergyiap.db.DB;
import com.allergyiap.utils.C;
import com.allergyiap.utils.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public abstract class Dao<T> {

    protected DB db = DB.getInstance();
    protected String entityName;

    public Dao(String entityName) {
        this.entityName = entityName;
    }

    public abstract void insert(T bean);

    public abstract void update(T bean);

    public abstract void delete(int id);

    public abstract List<T> getAll();

    public void updateFromWS(int days) {
        if (!db.getLastUpdate(this.entityName, days)) {
            try {
                String jsonStr = Util.getUrl(C.Network.WS_URL + this.entityName);
                JSONArray jsonObj = new JSONArray(jsonStr);
                for (int i = 0; i < jsonObj.length(); i++) {
                    JSONObject keyValue = jsonObj.getJSONObject(i);
                    db.insertJson(keyValue, this.entityName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
