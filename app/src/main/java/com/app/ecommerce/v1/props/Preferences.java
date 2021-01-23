package com.app.ecommerce.v1.props;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    private static final String PREFERENCIA_CLAVE_API = "preferencia.claveApi";

    private static SharedPreferences getDefaultSharedPreferences(Context contexto) {
        return PreferenceManager.getDefaultSharedPreferences(contexto);
    }

    public static void guardarClaveApi(Context contexto, String claveApi) {
        SharedPreferences sp = getDefaultSharedPreferences(contexto);
        sp.edit().putString(PREFERENCIA_CLAVE_API, claveApi).apply();
    }

    public static String obtenerClaveApi(Context contexto) {
        return getDefaultSharedPreferences(contexto).getString(PREFERENCIA_CLAVE_API, null);
    }
}
