package com.allergyiap.services;

import android.content.Context;

import com.allergyiap.entities.AllergyLevelEntity;
import com.allergyiap.entities.StationEntity;
import com.allergyiap.utils.DBHelper;
import com.allergyiap.utils.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lfernando on 08/12/2016.
 */

public class AllergyLevelProxyClass {
    public static List<StationEntity> getStations() throws Exception {
        Context context = DBHelper.getCurrentContext();
        DBHelper db = DBHelper.getDBHelper(context);
        JSONArray t = db.getQuery("SELECT * FROM stations");
        ArrayList<StationEntity> r = new ArrayList<StationEntity>();
        for (int i = 0; i < t.length(); i++) {
            JSONObject keyValue = t.getJSONObject(i);
            r.add(StationEntity.fromJson(keyValue));
        }
        return r;
    }

    public static List<AllergyLevelEntity> getLevels(int stationId) throws Exception {
        String stationString = String.valueOf(stationId);
        Context context = DBHelper.getCurrentContext();
        DBHelper db = DBHelper.getDBHelper(context);
        JSONArray t = db.getQuery("SELECT allergy_level.*,allergy.allergy_name AS allergy_name FROM allergy_level INNER JOIN allergy ON allergy.idallergy = allergy_level.allergy_idallergy INNER JOIN stations ON stations.name = allergy_level.station WHERE stations.id=" + stationString + " AND date_start <= DATE('NOW') AND DATE('NOW') <= date_end ");
        if (t.length() == 0) {
            //String jsonLevels = Util.getJson("levels.json");
            String jsonLevels = Util.getUrl("http://10.0.2.2:8080/AllergyIAPWS/XarxaImportServlet");
            JSONArray jsonObj = new JSONArray(jsonLevels);
            for (int i = 0; i < jsonObj.length(); i++) {
                JSONObject keyValue = jsonObj.getJSONObject(i);
                db.insertJson(keyValue, "allergy_level");
            }
            t = db.getQuery("SELECT allergy_level.*,allergy.allergy_name AS allergy_name FROM allergy_level INNER JOIN allergy ON allergy.idallergy = allergy_level.allergy_idallergy INNER JOIN stations ON stations.name = allergy_level.station WHERE stations.id=" + stationString + " AND date_start <= DATE('NOW') AND DATE('NOW') <= date_end ");
        }
        ArrayList<AllergyLevelEntity> r = new ArrayList<AllergyLevelEntity>();
        for (int i = 0; i < t.length(); i++) {
            JSONObject keyValue = t.getJSONObject(i);
            r.add(AllergyLevelEntity.fromJson(keyValue));
        }
        return r;
    }
}