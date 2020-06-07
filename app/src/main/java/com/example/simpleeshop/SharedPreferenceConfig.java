package com.example.simpleeshop;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceConfig {
    SharedPreferences sharedPreferences;
    Context context;

    public SharedPreferenceConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_preference),Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status), status);
        editor.commit();
    }

    public boolean readLoginStatus(){
        boolean status = false;
        status = sharedPreferences.getBoolean( context.getResources().getString(R.string.login_status), false);
        return status;
    }

    public void writeUserId(int id){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(context.getResources().getString(R.string.user_id), id);
        editor.commit();
    }

    public int readUserId(){
        int status = 0;
        status = sharedPreferences.getInt( context.getResources().getString(R.string.user_id), 0);
        return status;
    }
}
