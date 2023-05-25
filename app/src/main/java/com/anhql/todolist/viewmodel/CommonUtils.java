package com.anhql.todolist.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;

import com.anhql.todolist.App;

public class CommonUtils {
    private static final String PREF_FILE = "PREF_FILE";
    private static CommonUtils instance;

    private CommonUtils() {
    }

    public static CommonUtils getInstance() {
        if (instance == null) {
            instance = new CommonUtils();
        }
        return instance;
    }

    public void savePref(String key, String value) {
        if (key == null || key.isEmpty()) return;
        if (value == null || value.isEmpty()) return;
        SharedPreferences pref = App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        pref.edit().putString(key, value).apply();
    }

    public String getPref(String key) {
        if (key == null || key.isEmpty()) return null;
        SharedPreferences pref = App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        return pref.getString(key, null);
    }

    public void removePref(String key) {
        if (key == null || key.isEmpty()) return;
        SharedPreferences pref = App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        pref.edit().remove(key).apply();
    }

}
