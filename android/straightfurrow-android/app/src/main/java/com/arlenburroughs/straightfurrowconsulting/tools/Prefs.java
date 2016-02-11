package com.arlenburroughs.straightfurrowconsulting.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Arlen on 2/10/15.
 */
public class Prefs {

    public static final String LAST_USER = "last user to log in";
    public static final String LAST_PASS = "last pass to log in";
    public static final String AUTO_LOGIN = "auto log in the user";;
    public static final String CURR_USR_ID = "current user id";

    private Prefs(){}

    public static void put(Context context, String key, boolean value){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean(key,value).apply();
    }

    public static void put(Context context, String key, String value){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(key, value).apply();
    }


    public static boolean get(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(key,false);
    }

    public static String getLastUser(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(LAST_USER, "");
    }

    public static String getLastPass(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(LAST_PASS, "");
    }

    public static String getUserID(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(CURR_USR_ID, "");
    }
}
