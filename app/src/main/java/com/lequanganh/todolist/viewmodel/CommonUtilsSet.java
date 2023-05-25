package com.lequanganh.todolist.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;

import com.lequanganh.todolist.App;

import java.util.Set;

public class CommonUtilsSet {
    private static final String PREF_FILE = "PREF_FILE";
    private static CommonUtilsSet instance;

    private CommonUtilsSet() {
    }

    public static CommonUtilsSet getInstance() {
        if (instance == null) {
            instance = new CommonUtilsSet();
        }
        return instance;
    }


    public void savePref(String key, Set<String> listVal) {
        if (key == null || key.isEmpty()) return;
        if (listVal == null || listVal.isEmpty()) return;
        SharedPreferences pref = App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        pref.edit().putStringSet(key, listVal).apply();
    }

    public Set<String> getPref(String key) {
        if (key == null || key.isEmpty()) return null;
        SharedPreferences pref = App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        return pref.getStringSet(key, null);
    }

    public void removePref(String key) {
        if (key == null || key.isEmpty()) return;
        SharedPreferences pref = App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        pref.edit().remove(key).apply();
    }

}
