package com.example.simpleeshop;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static MyApplication _instance = null;

    private SharedPreferenceConfig sharedPreferenceConfig;


    public static MyApplication Instance() {
        return _instance;
    }

    public static Context Context() {
        return _instance == null ? null : _instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        _instance = this;
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        super.onCreate();
    }

    public SharedPreferenceConfig getSharedPreferenceConfig() {
        return sharedPreferenceConfig;
    }

    public static int getImageId(String imagePath) {
        return Context().getResources().getIdentifier(imagePath, null, Context().getPackageName());
    }


}
