package com.app.ecommerce.v1.gateways;

import android.content.Intent;
import android.os.IBinder;

import android.app.Service;

public class ServiceAutentication extends Service {
    private Autenticador autenticador;

    @Override
    public void onCreate() {
        autenticador = new Autenticador(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return autenticador.getIBinder();
    }

}
