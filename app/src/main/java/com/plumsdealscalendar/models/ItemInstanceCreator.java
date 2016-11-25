package com.plumsdealscalendar.models;

import com.google.gson.InstanceCreator;
import com.plumsdealscalendar.models.login.UserData;

import java.lang.reflect.Type;

/**
 * Created by NickNb on 25.11.2016.
 */
public class ItemInstanceCreator implements InstanceCreator {
    public UserData createInstance(Type type) {
        return new UserData();
    }
}