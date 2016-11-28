package com.plumsdealscalendar.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.plumsdealscalendar.R;
import com.plumsdealscalendar.adapters.settings.SettingsAdapter;
import com.plumsdealscalendar.models.login.Payload;

import io.realm.Realm;

/**
 * Created by NickNb on 23.11.2016.
 */
public class Settings extends Fragment {
    private Payload payload;
    private ListView listView;

    public Settings newInstance(Payload payload){
        Settings fragment = new Settings();
        Bundle args = new Bundle();
        args.putString("name", payload.getName());
        fragment.setArguments(args);
        return fragment;
    }

    public Settings(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fr_settings, container, false);

        if(savedInstanceState != null){
            //Restoring fragment state
        }

        // Initialize Realm
        Realm.init(getActivity());
        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();
        Payload payload = realm.where(Payload.class).findFirst();

        listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(new SettingsAdapter(getActivity(), payload));

        return view;
    }
}
