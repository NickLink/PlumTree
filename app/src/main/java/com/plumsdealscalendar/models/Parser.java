package com.plumsdealscalendar.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.plumsdealscalendar.models.login.Error;
import com.plumsdealscalendar.models.login.Payload;

import org.json.JSONObject;

/**
 * Created by NickNb on 28.11.2016.
 */
public class Parser {

    public static Payload getPayload(JSONObject jsonObject){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Payload payload = gson.fromJson(jsonObject.toString(), Payload.class);
        return payload;
    }

    public static Error getError(JSONObject jsonObject){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Error error = gson.fromJson(jsonObject.toString(), Error.class);
        return error;
    }
}
