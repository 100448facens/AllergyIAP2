package com.allergyiap.db;

import android.database.Cursor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultSet {
    private Cursor cursor;

    public ResultSet(Cursor cursor) {
        this.cursor = cursor;
    }

    public String getString(String field) {
        return cursor.getString(cursor.getColumnIndex(field));
    }

    public long getLong(String field) {
        return cursor.getLong(cursor.getColumnIndex(field));
    }

    public boolean next() {
        return cursor.moveToNext();
    }

    public float getFloat(String field) {
        return cursor.getFloat(cursor.getColumnIndex(field));
    }

    public Date getDate(String field) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(cursor.getString(cursor.getColumnIndex(field)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public double getDouble(String field) {
        return cursor.getDouble(cursor.getColumnIndex(field));
    }

    public int getInt(String field) {
        return cursor.getInt(cursor.getColumnIndex(field));
    }

    public String getTime(String field) {
        return cursor.getString(cursor.getColumnIndex(field));
    }
}
