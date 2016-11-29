package com.plumsdealscalendar.database;

import com.plumsdealscalendar.interfaces.UserDataInterfaces;
import com.plumsdealscalendar.models.login.Payload;

import io.realm.Realm;

/**
 * Created by NickNb on 29.11.2016.
 */
public class UserDataImp implements UserDataInterfaces {
    @Override
    public Payload getPayload() {

        Realm realm = Realm.getDefaultInstance();
        Payload payload = realm.where(Payload.class).findFirst();

        return payload;
    }
}
