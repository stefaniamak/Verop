package com.example.simpleeshop;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static MyApplication _instance = null;

    public static MyApplication Instance() {
        return _instance;
    }

    public static Context Context() {
        return _instance == null ? null : _instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        _instance = this;
        super.onCreate();


    }
}
