package com.plumsdealscalendar.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.plumsdealscalendar.R;

/**
 * Created by NickNb on 23.11.2016.
 */
public class Settings extends Fragment {

    public Settings(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_main, container);

        if(savedInstanceState != null){
            //Restoring fragment state
        }
        return view;
    }
}
