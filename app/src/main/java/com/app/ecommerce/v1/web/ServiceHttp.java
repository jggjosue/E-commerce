package com.app.ecommerce.v1.web;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.ecommerce.v1.props.Messages;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class ServiceHttp {
    private static final String TAG = ServiceHttp.class.getSimpleName();

    private Context mContext = null;

    public ServiceHttp(Context context) {
        this.mContext = context;
    }

    public void get(String uri, Response.Listener<JSONObject> jsonListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(uri, null, jsonListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return setHeaders();
            }
        };
        ClientHttp.getInstance(mContext).addToRequestQueue(request);
    }

    public void post(String uri, String datos, Response.Listener<JSONObject> jsonListener, Response.ErrorListener errorListener, final HashMap<String, String> cabeceras) {
        JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.POST, uri, datos, jsonListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return cabeceras;
            }
        };
        ClientHttp.getInstance(mContext).addToRequestQueue(peticion);
    }

    public Map<String, String> setHeaders() {
        Map<String, String>  params = new HashMap<String, String>();
        params.put(Messages.KEY, Messages.VALUE);
        return params;
    }
}
