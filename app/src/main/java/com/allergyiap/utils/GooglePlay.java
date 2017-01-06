package com.allergyiap.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.util.Log;

import com.allergyiap.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class GooglePlay {

    static final String TAG = "GooglePlay";

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    public static boolean checkServices(final Activity activity) {
        Log.d(TAG, ".checkPlayServices");
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity.getApplicationContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(
                        resultCode,
                        activity,
                        C.GooglePlay.CONNECTION_FAILURE_RESOLUTION_REQUEST,
                        new OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                activity.finish();
                            }
                        }
                ).show();
            } else {
                Log.i(TAG ,".checkPlayServices: This device is not supported.");
                //activity.finish();
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(activity.getString(R.string.main_error_googleplay_title));
                builder.setMessage(R.string.main_error_googleplay_message);
                builder.setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        activity.finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
            }
            return false;
        }
        Log.i(TAG, ".checkPlayServices: Google Play Services OK");
        return true;
    }

}
