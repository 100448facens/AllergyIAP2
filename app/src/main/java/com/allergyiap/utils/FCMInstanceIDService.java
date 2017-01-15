package com.allergyiap.utils;

import android.util.Log;

import com.allergyiap.beans.User;
import com.allergyiap.service.UserService;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by David on 06/01/2017.
 */

public class FCMInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "FCMInstanceIDService";

    @Override
    public void onTokenRefresh() {
        //Get hold of the registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Log the token
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        //Implement this method if you want to store the token on your server
        User u = UserService.getCurrentUser();
        u.setDevice_key(token);
        UserService.update(u);
    }

}
