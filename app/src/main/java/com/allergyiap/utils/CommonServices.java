package com.allergyiap.utils;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by David on 06/01/2017.
 */
public class CommonServices {

    static final String TAG = "CommonServices";

    public static final int REQCODE_SETTINGS = 100;
    public static final int REQCODE_ADD_NOTICE = 200;
    public static final int REQCODE_USER_PROFILE = 300;
    public static final int REQCODE_DETAIL_NOTICE = 400;
    public static final int REQCODE_RELOAD_APP = 500;
    public static final int REQCODE_CHAT_DETAIL = 600;
    public static final int REQCODE_CATEG_RESTART = 700;
    public static final int REQCODE_IMAGE = 800;

    public static final int RESULT_RELOAD_CHATS = 1;
    public static final int RESULT_LOGIN = 2;
    public static final int RESULT_RESTART = 3;
    public static final int RESULT_LOGOUT = 4;
    public static final int RESULT_ENCUESTA_ANSWERED = 5;
    public static final int RESULT_LOGOUT_GROUP = 6;
    public static final int RESULT_IMAGE_CROP = 7;

    public static final int MODE_LOGIN = 1;
    public static final int MODE_REGISTER = 2;
    public static final int MODE_RECOVERY = 3;
    public static final int MODE_PROFILE = 4;

    //public static final int REQUEST_CAMERA = 1;
    //public static final int REQUEST_GALLERY = 2;
    public static final String TEMP_IMAGE_FILE = "temp.jpg";

    public static int NUM_MESSAGE = 0;

    static CommonServices services;
    private Context context;

    public CommonServices(Context context) {
        this.context = context;
    }

    public static CommonServices getInstance(Context context) {
        services = new CommonServices(context);
        return services;
    }

    /**
     * Comprobaci�n de GooglePlay Services, Registro GCM.
     */
    public Boolean checkServicesGoogle(Activity activity) {
        Log.v(TAG, ".checkPreRequisites");

        // Check device for Play Services APK.
        // If check succeeds, proceed with GCM registration.
        if (GooglePlay.checkServices(activity)) {
            // register device on server to receive push notifications
            //GCM.registerDevice(context, (BaseActivity) activity);
            return true;
        }

        return false;
    }

    /**
     * Verification of internet
     *
     * @return
     */
    public Boolean checkInternet() {
        Log.v(TAG, ".checkInternet");
        // check internet

        if (this.checkInternet(context)) {
            return true;
        }
        return false;
    }

    private static boolean checkInternet(Context context) {
        try {
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo == null)
                return false;
            if (!networkInfo.isConnected())
                return false;
            if (!networkInfo.isAvailable())
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Activity getActivity() {

        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                ArrayMap<Object, Object> activities = (ArrayMap<Object, Object>) activitiesField.get(activityThread);
                for (Object activityRecord : activities.values()) {
                    Class activityRecordClass = activityRecord.getClass();
                    Field pausedField = activityRecordClass.getDeclaredField("paused");
                    pausedField.setAccessible(true);
                    if (!pausedField.getBoolean(activityRecord)) {
                        Field activityField = activityRecordClass.getDeclaredField("activity");
                        activityField.setAccessible(true);
                        Activity activity = (Activity) activityField.get(activityRecord);
                        return activity;
                    }
                }
            } else {
                HashMap activities = (HashMap) activitiesField.get(activityThread);
                for (Object activityRecord : activities.values()) {
                    Class activityRecordClass = activityRecord.getClass();
                    Field pausedField = activityRecordClass.getDeclaredField("paused");
                    pausedField.setAccessible(true);
                    if (!pausedField.getBoolean(activityRecord)) {
                        Field activityField = activityRecordClass.getDeclaredField("activity");
                        activityField.setAccessible(true);
                        Activity activity = (Activity) activityField.get(activityRecord);
                        return activity;
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            Log.e("", "getActivity", e);
        } catch (NoSuchMethodException e) {
            Log.e("", "getActivity", e);
        } catch (IllegalAccessException e) {
            Log.e("", "getActivity", e);
        } catch (InvocationTargetException e) {
            Log.e("", "getActivity", e);
        } catch (NoSuchFieldException e) {
            Log.e("", "getActivity", e);
        }
        return null;
    }

    public void clearNotifications() {
        // limpia las notificaciones si entramos en noticias o venimos de noticias
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }
}
