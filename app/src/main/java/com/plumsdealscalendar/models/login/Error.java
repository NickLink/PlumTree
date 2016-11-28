package com.plumsdealscalendar.models.login;

/**
 * Created by NickNb on 28.11.2016.
 */
public class Error {
    public String field;
    public String message;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}