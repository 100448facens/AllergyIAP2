package com.allergyiap.dao;

import android.database.SQLException;

import com.allergyiap.beans.Allergy;
import com.allergyiap.beans.User;
import com.allergyiap.beans.UserAllergy;
import com.allergyiap.db.ResultSet;
import com.allergyiap.service.AllergyService;
import com.allergyiap.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserAllergyDao extends Dao<UserAllergy> {
    public static final String TABLE_NAME = "user_allergies";

    public static String id_user = "id_user";
    public static String id_allergy = "id_allergy";

    public UserAllergyDao() {
        super(TABLE_NAME);
    }

    @Override
    public void insert(UserAllergy bean) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(TABLE_NAME);
        query.append(" (");
        query.append(id_user + ", ");
        query.append(id_allergy);
        query.append(") ");
        query.append("VALUES");
        query.append(" (");
        query.append("'" + bean.getId_user() + "', ");
        query.append("'" + bean.getId_allergy() + "'");
        query.append(") ");

        db.executeUpdate(query.toString());

    }

    @Override
    public void update(UserAllergy bean) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(int id) {
    }

    public void delete(long iduser, long idallergy) {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(TABLE_NAME);
        query.append(" WHERE ");
        query.append(id_user + " = " + iduser + " AND " + id_allergy + " = " + idallergy);

        db.executeUpdate(query.toString());

    }

    @Override
    public List<UserAllergy> getAll() {

        String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";
        return select(selectQuery);
    }

    private List<UserAllergy> select(String query) {
        //this.updateFromWS(1);
        List<UserAllergy> list = new ArrayList<>();
        try {

            ResultSet rs = db.execute(query);
            while (rs.next()) {
                int iduser = rs.getInt(id_user);
                int idallergy = rs.getInt(id_allergy);
                list.add(new UserAllergy(iduser, idallergy));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<User> getUsersByAllergy(long idallergy) {
        String selectQuery = "SELECT id_user FROM " + TABLE_NAME + " WHERE " + id_allergy + " = " + idallergy + ";";
        List<User> list = new ArrayList<>();
        try {

            ResultSet rs = db.execute(selectQuery);
            while (rs.next()) {
                int iduser = rs.getInt(id_user);
                User u = UserService.get(new Long(iduser));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<Allergy> getAllergyesByUser(long iduser) {
        String selectQuery = "SELECT id_allergy FROM " + TABLE_NAME + " WHERE " + id_user + " = " + iduser + ";";
        List<Allergy> list = new ArrayList<>();
        try {
            ResultSet rs = db.execute(selectQuery);
            while (rs.next()) {
                int idallergy = rs.getInt("id_allergy");
                Allergy a = AllergyService.get(new Long(idallergy));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public void unsetAllergyToUser(long iduser, long idallergy) {
        String selectQuery = "DELETE FROM " + TABLE_NAME + " WHERE " + id_user + " = " + iduser + " AND " + id_allergy + " = " + idallergy;
        try {
            db.executeUpdate(selectQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAllergyToUser(long iduser, long idallergy) {
        unsetAllergyToUser(iduser, idallergy);
        String selectQuery = "INSERT INTO " + TABLE_NAME + "(" + id_user + "," + id_allergy + ") VALUES(" + iduser + ", " + idallergy + ")";
        try {
            db.executeUpdate(selectQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getTheUserHasThisAllergy(long iduser, long idallergy) {
        String selectQuery = "SELECT id_allergy FROM " + TABLE_NAME + " WHERE " + id_user + " = " + iduser + " AND " + id_allergy + " = " + idallergy;
        try {
            ResultSet rs = db.execute(selectQuery);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
