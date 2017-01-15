package com.allergyiap.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Application Preferences Manager.
 */
public class Prefs {
    static final String TAG = "Prefs";

    private Context context;

    static Prefs prefs;

    public static Prefs getInstance(Context context) {
        if(prefs == null) {
            Log.e(TAG,"new instance");
            prefs = new Prefs(context);
        }
        return prefs;
    }

    private Prefs(Context context) {
        this.context = context;
    }

    /**
     * @return Default {@code SharedPreferences}.
     */
    private SharedPreferences defaultSharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * @return Application's {@code SharedPreferences}.
     */
    private SharedPreferences sharedPrefs() {
        return context.getApplicationContext().getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    public int getAppVersionCode() {
        Log.v(TAG, ".getAppVersionCode");
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    public int getRegisteredVersionCode(){
        Log.v(TAG, ".getRegisteredVersionCode");
        return sharedPrefs().getInt(C.GCM.PROPERTY_APP_VERSION, Integer.MIN_VALUE);
    }

    /**
     * Remove register GCM
     */
    public void clearRegistrationId() {
        Log.v(TAG, ".clearRegistrationId");
        sharedPrefs().edit()
                .remove(C.GCM.PROPERTY_REG_ID)
                .remove(C.GCM.PROPERTY_APP_VERSION)
                .apply();
    }

    /**
     * @return id register GCM
     */
    public String getRegistrationId() {
        String registrationId = sharedPrefs().getString(C.GCM.PROPERTY_REG_ID, "");
        Log.d(TAG, ".getRegistrationId:" + registrationId);
        return registrationId;
    }


    public void setRegistrationId(String registrationId) {
        Log.d(TAG, ".setRegistrationId:" + registrationId);
        sharedPrefs().edit()
                .putString(C.GCM.PROPERTY_REG_ID, registrationId)
                .putInt(C.GCM.PROPERTY_APP_VERSION, getAppVersionCode())
                .apply();
    }

    /**
     * Language (DefaultSharedPrefs from PreferenceActivity)
     */
    public String getLanguage() {
        String language = defaultSharedPrefs().getString(C.Prefs.LANGUAGE, C.Lang.LANG_EN);
        Log.d(TAG, ".getLanguage" + language);
        return language;
    }

    public void setLanguage(String language) {
        Log.v(TAG, ".setLanguage:" + language);
        try {
            // save
            defaultSharedPrefs().edit().putString(C.Prefs.LANGUAGE, language).apply();
        } catch (Exception e) {
            Log.e(TAG, ".setLanguage Error: " + e.getMessage());
        }
    }

    public void setAllergies(Set<String> allergies) {
        Log.v(TAG, ".setAllergies:" + allergies.size());
        try {
            // save
            defaultSharedPrefs().edit().putStringSet(C.Prefs.USER_ALLERGIES, allergies).apply();
        } catch (Exception e) {
            Log.e(TAG, ".setAllergies Error: " + e.getMessage());
        }
    }

    /**
     * Language (DefaultSharedPrefs from PreferenceActivity)
     */
    public Set<String> getAllergies() {
        Set<String> allergies = defaultSharedPrefs().getStringSet(C.Prefs.LANGUAGE, new HashSet<String>());
        Log.d(TAG, ".getLanguage" + allergies.size());
        return allergies;
    }



    /**
     * Notifications Received
     *
     * Some options in DefaultSharedPrefs from PreferenceActivity
     */
    public boolean isNotificationsEnabled() {
        SharedPreferences defaultPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return defaultPrefs.getBoolean(C.Prefs.NOTIFICATIONS_ENABLED, false);
    }
    public boolean isNotificationsSound() {
        SharedPreferences defaultPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return defaultPrefs.getBoolean(C.Prefs.NOTIFICATIONS_SOUND, false);
    }
    public boolean isNotificationsVibration() {
        SharedPreferences defaultPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return defaultPrefs.getBoolean(C.Prefs.NOTIFICATIONS_VIBRATION, false);
    }
    public Set<String> getNotificationDaysOfWeek() {
        Set<String> weeks = new HashSet<>();
        SharedPreferences defaultPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return defaultPrefs.getStringSet(C.Prefs.NOTIFICATIONS_WEEK, weeks);
    }
    public Long getNotificationHour() {
        SharedPreferences defaultPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return defaultPrefs.getLong(C.Prefs.NOTIFICATIONS_HOUR, 0);
    }



    /**
     * Login: Remember me
     */
    public String getLoginName() {
        return sharedPrefs().getString(C.Prefs.LOGIN_NAME, "");
    }
    public String getLoginPass() {
        return sharedPrefs().getString(C.Prefs.LOGIN_PASS, "");
    }
    public void setLoginName(String name) {
        sharedPrefs().edit()
                .putString(C.Prefs.LOGIN_NAME, name)
                .commit();
    }
    public void setLoginFields(String name, String pass) {
        sharedPrefs().edit()
                .putString(C.Prefs.LOGIN_NAME, name)
                .putString(C.Prefs.LOGIN_PASS, pass)
                .commit();
    }
    public void clearLoginFields() {
        sharedPrefs().edit()
                .remove(C.Prefs.LOGIN_NAME)
                .remove(C.Prefs.LOGIN_PASS)
                .commit();
    }
    public String getLoginToken() {
        return sharedPrefs().getString(C.Prefs.LOGIN_TOKEN, "");
    }
    public void setLoginToken(String token) {
        sharedPrefs().edit().putString(C.Prefs.LOGIN_TOKEN, token).commit();
    }
    public void clearLoginToken() {
        sharedPrefs().edit()
                .remove(C.Prefs.LOGIN_TOKEN)
                .commit();
    }
}
