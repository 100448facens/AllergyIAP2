package com.allergyiap.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.allergyiap.R;

/**
 * Class Actions
 */
public class A {

    /**
     * Settings : User Location
     */
    public static void startSettingsGeolocation(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * Share Text
     */
    public static void startShareText(Activity activity, String subject, String message) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        // common text
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        // extra text (subject)
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendIntent.setType("text/plain");
        activity.startActivity(Intent.createChooser(sendIntent, activity.getString(R.string.share_dialog_title)));
    }

    /**
     * Share Text
     */
    public static void startShareText(Context activity, String subject, String message) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        // common text
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        // extra text (subject)
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendIntent.setType("text/plain");
        activity.startActivity(Intent.createChooser(sendIntent, activity.getString(R.string.share_dialog_title)));
    }
}
