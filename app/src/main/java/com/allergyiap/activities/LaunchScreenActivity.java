package com.allergyiap.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.allergyiap.R;
import com.allergyiap.utils.CommonServices;
import com.allergyiap.utils.LocationService;

/**
 * Created by David on 06/01/2017.
 * <p>
 * Working like a preloader
 **/
public class LaunchScreenActivity extends BaseActivity {

    static final String TAG = "LaunchScreen";
    private AsyncTask<Void, Void, Boolean> task;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG + ".onCreate", ".");
        super.onCreate(savedInstanceState);

        /*this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        //getSupportActionBar().hide();

        setContentView(R.layout.activity_launch_screen);

        if(checkPermissionLocation()){
            task = new BackgroundTask().execute();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the listener you previously added
        //locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CommonServices.PERMISSIONS_REQUEST_ACCESS_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                task = new BackgroundTask().execute();
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void showResult() {
        Intent intent = new Intent(LaunchScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean checkPermissionLocation() {

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "request Permission");
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION},
                    CommonServices.PERMISSIONS_REQUEST_ACCESS_LOCATION);
            return false;
        }
        return true;
    }

    private class BackgroundTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            LocationService.getInstance(context);
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                //Thread.sleep(3000); //solo para pruebas
                //check Internet
                //if (!CommonServices.getInstance(context).checkInternet())
                //    return null;

                //check if google services its ok
                if (!CommonServices.getInstance(context).checkServicesGoogle(LaunchScreenActivity.this))
                    return Boolean.FALSE;

                return Boolean.TRUE;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
//          Pass your loaded data here using Intent

            showResult();
        }
    }
}
