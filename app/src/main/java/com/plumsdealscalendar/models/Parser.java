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
        //GsonBuilder builder = new GsonBuilder();
        //Gson gson = builder.create();
        //Payload payload = gson.fromJson(jsonObject.toString(), Payload.class);
        Payload payload = new Payload();
        payload.setUserId(jsonObject.optInt("user_id"));
        payload.setName(jsonObject.optString("name"));
        payload.setEmail(jsonObject.optString("email"));
        payload.setGender(jsonObject.optString("gender"));
        payload.setThemeId(jsonObject.optInt("theme_id"));
        payload.setAge(jsonObject.optInt("age"));
        payload.setImage(jsonObject.optString("image"));
        payload.setApiHash(jsonObject.optString("api_hash"));
        payload.setCity(jsonObject.optInt("city"));
        payload.setZip(jsonObject.optInt("zip"));
        payload.setPhone(jsonObject.optString("phone"));
        payload.setBirthday(jsonObject.optString("birthday"));
        payload.setPlumPoints(jsonObject.optInt("plum_points"));
        payload.setFollowedBusiness(jsonObject.optInt("followed_business"));
        payload.setContactsCount(jsonObject.optInt("contacts_count"));
        return payload;
    }

    public static Error getError(JSONObject jsonObject){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Error error = gson.fromJson(jsonObject.toString(), Error.class);
        return error;
    }
}
