package com.allergyiap.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ReceiveAlarm extends BroadcastReceiver {

    static final String TAG = "ReceiveAlarm";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        Toast.makeText(context, "Alarm!!!!", Toast.LENGTH_SHORT).show();
        /*Intent i = new Intent(context, Main.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);*/
    }
}
