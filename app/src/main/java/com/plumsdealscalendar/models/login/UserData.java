package com.plumsdealscalendar.models.login;


/**
 * Created by NickNb on 24.11.2016.
 */
public class UserData {

    public int status;
    public Payload payload;
    public String error;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}