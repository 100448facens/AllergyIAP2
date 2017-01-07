package com.allergyiap.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.allergyiap.utils.DBHelper;

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
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
