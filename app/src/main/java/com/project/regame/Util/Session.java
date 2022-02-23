package com.project.regame.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private Context _context;

    public Session(Context context){
        this._context = context;
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public void setSession(String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(_context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String getSession(String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(_context.getApplicationContext());
        String data = sharedPreferences.getString(key,value);
        return  data;
    }

    public void clearSession(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(_context.getApplicationContext());
        sharedPreferences.edit().clear().commit();
    }

    public void setSessionBoolean(String key, Boolean value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(_context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public Boolean getSessionBoolean(String key, Boolean value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(_context.getApplicationContext());
        Boolean data = sharedPreferences.getBoolean(key,value);
        return  data;
    }

}
