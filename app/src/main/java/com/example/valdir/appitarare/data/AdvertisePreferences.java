package com.example.valdir.appitarare.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.valdir.appitarare.R;

/**
 * Created by VALDIR on 29/06/2018.
 */

public class AdvertisePreferences {
    private static final String KEY_PREF_MENU = "id_menu_pref";
    private static final String KEY_ORDER = "order_avaliado";

    public static Boolean getPreferedOrderAvaliad(Context context){
        SharedPreferences settings = context.getSharedPreferences(KEY_PREF_MENU, 0);
        return settings.getBoolean(KEY_ORDER, false);
    }

    public static void setPreferedOrderAvaliad(Context context, int id){
        SharedPreferences settings = context.getSharedPreferences(KEY_PREF_MENU, 0);
        SharedPreferences.Editor editor = settings.edit();
        Boolean popularChecked = (id == R.id.action_check_avalua);
        editor.putBoolean(KEY_ORDER, popularChecked);
        editor.apply();
    }
}
