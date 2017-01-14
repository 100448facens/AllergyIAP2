package com.allergyiap.service;

import com.allergyiap.beans.Station;
import com.allergyiap.dao.StationDao;
import com.allergyiap.utils.Util;

import java.util.List;

public class StationService {
    private static StationDao dao = new StationDao();

    public static void insert(Station bean) {
        dao.insert(bean);
    }

    public static void update(Station bean) {
        dao.update(bean);
    }

    public static void delete(int id) {
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

    public static Station getNearestStation(double lat, double lon) {
        List<Station> s = getAll();
        double d = 0;
        Station sel = null;
        for (Station st : s) {
            double dt = Util.distance(lat, lon, st.getLatitude(), st.getLongitude());
            if (dt < d || sel == null) {
                d = dt;
                sel = st;
            }
        }
        return sel;
    }
}
