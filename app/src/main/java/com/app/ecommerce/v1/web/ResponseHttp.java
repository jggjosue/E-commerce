package com.app.ecommerce.v1.web;

public class ResponseHttp {
    private int status;
    private String message;

    public ResponseHttp(int code, String body) {
        this.status = code;
        this.message = body;
    }

    public ResponseHttp() {

    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "(" + getStatus() + "): " + getMessage();
    }

}
