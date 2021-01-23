package com.app.ecommerce.v1.props;

import android.content.Context;
import android.net.ConnectivityManager;

public class Utilities {
    public static boolean getConexion(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
