package com.allergyiap.service;

import com.allergyiap.beans.Allergy;
import com.allergyiap.beans.User;
import com.allergyiap.beans.UserAllergy;
import com.allergyiap.dao.UserAllergyDao;
import com.allergyiap.utils.C;
import com.allergyiap.utils.Util;

import java.util.List;

public class UserAllergyService {
    private static UserAllergyDao dao = new UserAllergyDao();

    public static void insert(UserAllergy bean) {
        dao.insert(bean);
    }

    public static void update(UserAllergy bean) {
        dao.update(bean);
    }

    public static void delete(long iduser, long idallergy) {
        dao.delete(iduser, idallergy);
    }

    public static List<User> getUsersByAllergy(long idallergy) {
        return dao.getUsersByAllergy(idallergy);
    }

    public static List<Allergy> getAllergyesByUser(long iduser) {
        return dao.getAllergyesByUser(iduser);
    }

    public static boolean getTheCurrentUserHasThisAllergy(long idallergy) {
        return dao.getTheUserHasThisAllergy(UserService.getCurrentUser().getIduser(), idallergy);
    }

    public static void setAllergyToTheCurrentUser(long idallergy, boolean checked) {
        if (checked) {
            dao.setAllergyToUser(UserService.getCurrentUser().getIduser(), idallergy);
        } else {
            dao.unsetAllergyToUser(UserService.getCurrentUser().getIduser(), idallergy);
        }
        User u = UserService.getCurrentUser();
        if (u.getIduser() > 0) {
            try {
                long c = (checked ? 1 : 0);
                Util.getUrlAsync(C.Network.WS_URL + "setallergy/" + u.getUser_name() + "/" + u.getUser_password() + "/" + idallergy + "/" + c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static List<UserAllergy> getAll() {
        return dao.getAll();
    }
}
