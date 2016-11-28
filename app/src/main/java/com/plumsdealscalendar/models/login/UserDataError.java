package com.plumsdealscalendar.models.login;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NickNb on 25.11.2016.
 */
public class UserDataError {

    public int status;
    public String payload;
    public List<Error> error = new ArrayList<Error>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public List<Error> getError() {
        return error;
    }

    public void setError(List<Error> error) {
        this.error = error;
    }

}


