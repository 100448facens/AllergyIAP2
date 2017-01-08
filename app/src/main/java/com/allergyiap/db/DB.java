package com.allergyiap.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.allergyiap.utils.DBHelper;
import com.allergyiap.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DB extends SQLiteOpenHelper {
    private DB(Context context) {
        super(context, "allergyiap.db", null, 4);
    }

    private static DB db;
    private static Context context;

    static public Context getCurrentContext() {
        return DB.context;
    }
    static public void setCurrentContext(Context context) {
        DB.context=context;
    }

    public static DB getInstance() {
        if (DB.db == null) {
            DB.db = new DB(DB.context);
        }
        return DB.db;
    }

    /*For select statements*/
    public ResultSet execute(String query) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        ResultSet rs = new ResultSet(cursor);
        return rs;
    }

    /*For insert, update and delete statements*/
    public void executeUpdate(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queries = Util.getFile("allergyiap.db.sql");
        for (String query : queries.split(";")) {
            if (query.length() > 7) {
                db.execSQL(query);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean getLastUpdate(String name, int days) {
        String strDays = String.valueOf(days);
        JSONArray t = getQuery("SELECT * FROM data_version WHERE name = '" + name + "' AND DATE('NOW') < DATE(last_update,'" + strDays + " DAYS')");
        if (t.length() > 0) {
            return true;
        }
        return false;
    }

    public void setLastUpdateToNow(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM data_version WHERE name = '" + name + "'");
        db.execSQL("INSERT INTO data_version(name,last_update) SELECT '" + name + "',DATE('NOW')");
    }

    public void insertJson(JSONObject jsonObj, String table) throws JSONException {
        String sqlKeys = "";
        String sqlValues = "";
        for (Iterator iterator = jsonObj.keys(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            if (!sqlKeys.isEmpty()) {
                sqlKeys = sqlKeys + ",";
                sqlValues = sqlValues + ",";
            }
            sqlKeys = sqlKeys + key;
            sqlValues = sqlValues + "'" + jsonObj.get(key).toString() + "'";
        }
        String query = "REPLACE INTO " + table + "(" + sqlKeys + ") VALUES(" + sqlValues + ")";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    public JSONArray getQuery(String selectQuery) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> m = new HashMap<String, String>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    m.put(cursor.getColumnName(i), cursor.getString(i));
                }
                list.add(m);
            } while (cursor.moveToNext());
        }
        return Util.mapToJsonArray(list);
    }

}
