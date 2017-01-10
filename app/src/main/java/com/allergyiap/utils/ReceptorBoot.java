package com.allergyiap.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;

/**
 * This receiver start the alarm configuration because when turn off the mobile this service is closed
 */
public class ReceptorBoot extends BroadcastReceiver {

    static final String TAG = "ReceptorBoot";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"onReceive");
        initAlarm(context);
    }

    public static void initAlarm(Context context) {
        Log.d(TAG,"initAlarm");

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar cal = Calendar.getInstance();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String hour = String.valueOf(preferences.getLong("time_alarm", 0));

        cal.set(Calendar.HOUR_OF_DAY, TimePreference.getHour(hour));
        cal.set(Calendar.MINUTE, TimePreference.getMinute(hour));
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        if (cal.getTimeInMillis() < System.currentTimeMillis()) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }

        manager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, getPendingIntent(context));
    }

    public static void cancelAlarm(Context context) {
        Log.d(TAG,"cancelAlarm");
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(getPendingIntent(context));
    }

    private static PendingIntent getPendingIntent(Context context) {
        Log.d(TAG,"PendingIntent");
        Intent i = new Intent(context, ReceiveAlarm.class);
        return PendingIntent.getBroadcast(context, 0, i, 0);
    }
}