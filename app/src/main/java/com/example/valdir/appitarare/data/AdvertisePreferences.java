package com.example.valdir.appitarare.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.valdir.appitarare.R;

/**
 * Created by VALDIR on 29/06/2018.
 */

public class AdvertisePreferences {

    private static SharedPreferences buildSettings(Context context) {
        return context.getSharedPreferences(context.getString(R.string.key_prefs_menu), 0);
    }

    public static Boolean getPreferredOrderRated(Context context){
        return buildSettings(context).
                getBoolean(context.getString(R.string.key_prefs_order), false);
    }

    public static void setPreferredOrderRated(Context context, int id){
        Boolean popularChecked = (id == R.id.action_check_avalua);

        SharedPreferences.Editor editor = buildSettings(context).edit();

        editor.putBoolean(context.getString(R.string.key_prefs_order), popularChecked);
        editor.apply();
    }
}
