package com.allergyiap.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.allergyiap.beans.Station;
import com.allergyiap.db.DB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lfernando on 08/12/2016.
 */

public class Util {
    public static String getUrl(String urlStr) throws Exception {
        java.net.URL url = new URL(urlStr);
        DownloadTask d = new DownloadTask();
        return d.getUrl(url);
    }

    public static String getUrlAsync(String urlStr) throws Exception {
        java.net.URL url = new URL(urlStr);
        DownloadTask d = new DownloadTask();
        return d.execute(url).get();
    }

    public static String getFile(String file) {
        try {
            Context context = DB.getCurrentContext();
            InputStream s = context.getAssets().open(file);
            return Util.convertStreamToString(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static Station station;

    static public JSONArray mapToJsonArray(List<HashMap<String, String>> list) {
        JSONArray json_arr = new JSONArray();
        for (Map<String, String> map : list) {
            JSONObject json_obj = new JSONObject();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                try {
                    json_obj.put(key, value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            json_arr.put(json_obj);
        }
        return json_arr;
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::	This function converts decimal degrees to radians						 :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    public static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    public static class DownloadTask extends AsyncTask<URL, Void, String> {
        public String getUrl(URL url) {
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                return Util.convertStreamToString(in);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return "";
        }

        protected String doInBackground(URL... urls) {
            int count = urls.length;
            if (count > 0) {
                URL url = urls[0];
                return getUrl(url);
            }
            return "";
        }
    }
}
