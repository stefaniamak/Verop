package com.example.simpleeshop;

import android.content.Context;
import android.content.SharedPreferences;

public class sharedPreferenceConfig {
    SharedPreferences sharedPreferences;
    Context context;

    public sharedPreferenceConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("com.example.simpleeshop_loginPreference",Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("com.example.simpleeshop_loginStatus", status);
    }

    public boolean readLoginStatus(){
        boolean status = false;
        status = sharedPreferences.getBoolean("com.example.simpleeshop_loginStatus", false);
        return status;
    }
}
