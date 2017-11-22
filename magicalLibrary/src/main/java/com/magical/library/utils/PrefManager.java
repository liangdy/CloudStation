package com.magical.library.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * Project: TShow
 * FileName: PrefManager.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:49 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:49 PM
 * Remark:
 */
public class PrefManager {
    private static PrefManager instance = null;


    public static PrefManager getInstance() {
        if (instance == null) {
            synchronized (PrefManager.class) {
                if (instance == null) {
                    instance = new PrefManager();
                }
            }
        }
        return instance;
    }


    private PrefManager() {
    }


    public SharedPreferences getPrefs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    public int getIntFromPrefs(Context context, String key, int def) {
        if (key == null) {
            return def;
        } else {
            SharedPreferences pref = PreferenceManager
                    .getDefaultSharedPreferences(context);
            return pref.getInt(key, def);
        }
    }


    public long getLongFromPrefs(Context context, String key, long def) {
        if (key == null) {
            return def;
        } else {
            SharedPreferences pref = PreferenceManager
                    .getDefaultSharedPreferences(context);
            return pref.getLong(key, def);
        }
    }


    public boolean getBooleanFromPrefs(Context context, String key, boolean def) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        return pref.getBoolean(key, def);
    }


    public String getStringFromPrefs(Context context, String key, String def) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        return pref.getString(key, def);
    }

    public Set<String> get(Context context, String key, Set<String> def) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        return pref.getStringSet(key, def);
    }

    public void putIntToPrefs(Context context, String key, int value) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.apply();
    }


    public void putLongToPrefs(Context context, String key, long value) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.apply();
    }


    public void putStringToPrefs(Context context, String key, String value) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public void putBooleanToPrefs(Context context, String key, boolean value) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void putStringSetToPrefs(Context context, String key, Set<String> value) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(key, value);
        editor.apply();
    }
}
