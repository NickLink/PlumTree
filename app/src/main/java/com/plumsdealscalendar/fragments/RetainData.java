package com.plumsdealscalendar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.plumsdealscalendar.models.login.Payload;

/**
 * Created by NickNb on 24.11.2016.
 */
public class RetainData extends Fragment {

    // data object we want to retain
    private Payload data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setData(Payload data) {
        this.data = data;
    }

    public Payload getData() {
        return data;
    }
}
