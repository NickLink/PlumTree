package com.plumsdealscalendar.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.plumsdealscalendar.R;
import com.plumsdealscalendar.interfaces.UI_Interfaces;

/**
 * Created by Nick on 05.12.2016.
 */

public class PlumsList extends Fragment {

    private String TAG = getClass().getSimpleName();
    UI_Interfaces UIInterfaces;


    public PlumsList(){
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            UIInterfaces = (UI_Interfaces) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fr_settings, container, false);

        if (savedInstanceState != null) {
            //Restoring fragment state
        }

        return view;
    }
}
