package com.example.asus.catalogmov.Preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppPreference {
    SharedPreferences prefs;
    Context context;

    public AppPreference(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setIdMov(int input){

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("ID_MOV", input);
        editor.commit();
    }

    public int getIdMov(){
        return prefs.getInt("ID_MOV", 0);
    }
}
