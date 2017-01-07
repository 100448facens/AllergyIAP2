package com.allergyiap;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

public class AllergyApp extends MultiDexApplication {

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
    /*@Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this); //initialize other plugins

    }*/
}
