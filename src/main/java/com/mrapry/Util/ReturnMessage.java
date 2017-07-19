package com.mrapry.Util;

/**
 * Created by mrapry on 7/19/17.
 */
public class ReturnMessage {

    private String response;
    private String message;
    private String data;

    public ReturnMessage(String response, String message, String data) {
        this.response = response;
        this.message = message;
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
