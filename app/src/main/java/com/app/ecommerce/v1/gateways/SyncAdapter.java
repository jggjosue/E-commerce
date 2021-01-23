package com.app.ecommerce.v1.gateways;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.app.ecommerce.v1.props.Messages;
import com.app.ecommerce.v1.web.ResponseHttp;
import com.app.ecommerce.v1.web.ServiceHttp;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class SyncAdapter extends AbstractThreadedSyncAdapter {
    private static final String TAG = SyncAdapter.class.getSimpleName();

    public static final String EXTRA_RESULTADO = "extra.resultado";
    public static final String URL_SYNC_BATCH = "https://00672285.us-south.apigw.appdomain.cloud/demo-gapsi/search?&query=title";

    private static final String EXTRA_MENSAJE = "extra.mensaje";
    private static final int ESTADO_PETICION_FALLIDA = 107;
    private static final int ESTADO_TIEMPO_ESPERA = 108;
    private static final int ESTADO_ERROR_PARSING = 109;
    private static final int ESTADO_ERROR_SERVIDOR = 110;
    private Gson gson = new Gson();

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        syncLocal();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, final SyncResult syncResult) {
        Log.i(TAG, "Comenzando a sincronizar:" + account);
    }

    public void syncLocal() {
        new ServiceHttp(getContext()).get(URL_SYNC_BATCH, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getError(error);
            }
        });
    }

    private void getResponse(JSONObject response) {
        try {
            JSONArray jsonObject = response.getJSONArray("items");
            for(int i = 0; i< jsonObject.length(); i++) {
                Log.i(TAG, "JSONObject Response:" + jsonObject.getString(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getError(VolleyError error) {
        ResponseHttp respuesta = new ResponseHttp(ESTADO_PETICION_FALLIDA, "Petición incorrecta");
        if (error.networkResponse != null) {
            String s = new String(error.networkResponse.data);
            try {
                respuesta = gson.fromJson(s, ResponseHttp.class);
            } catch (JsonSyntaxException e) {
                Log.d(TAG, "Error de parsing: " + s);
            }
        }

        if (error instanceof NetworkError)
            respuesta = new ResponseHttp(ESTADO_TIEMPO_ESPERA, "Error en la conexión. Intentalo de nuevo");
        if (error instanceof TimeoutError)
            respuesta = new ResponseHttp(ESTADO_TIEMPO_ESPERA, "Error de espera del servidor");
        if (error instanceof ParseError)
            respuesta = new ResponseHttp(ESTADO_ERROR_PARSING, "La respuesta no es formato JSON");
        if (error instanceof ServerError)
            respuesta = new ResponseHttp(ESTADO_ERROR_SERVIDOR, "Error en el servidor");
        if (error instanceof NoConnectionError)
            respuesta = new ResponseHttp(ESTADO_ERROR_SERVIDOR, "Servidor no disponible, prueba mas tarde");

        Log.d(TAG, "Error Respuesta:" + (respuesta != null ? respuesta.toString() : "()") + "\nDetalles:" + error.getMessage());
        sendBroadcast(false, respuesta.getMessage());
    }

    private void sendBroadcast(boolean estado, String mensaje) {
        Log.d(TAG, "Estado: " + estado + ", Mensaje: " + mensaje);

        Intent intentLocal = new Intent(Intent.ACTION_SYNC);
        intentLocal.putExtra(EXTRA_RESULTADO, estado);
        intentLocal.putExtra(EXTRA_MENSAJE, mensaje);
        //LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intentLocal);
    }

}
