package com.app.ecommerce.v1.web;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ClientHttp {
    private static ClientHttp singleton;
    private RequestQueue requestQueue;
    private static Context context;

    private ClientHttp(Context context) {
        ClientHttp.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized ClientHttp getInstance(Context context) {
        if (singleton == null)
            singleton = new ClientHttp(context.getApplicationContext());
        return singleton;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
