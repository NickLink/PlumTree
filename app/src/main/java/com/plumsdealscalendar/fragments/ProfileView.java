package com.plumsdealscalendar.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.plumsdealscalendar.R;
import com.plumsdealscalendar.database.UserDataImp;
import com.plumsdealscalendar.interfaces.UserDataInterfaces;
import com.plumsdealscalendar.models.login.Payload;
import com.plumsdealscalendar.utils.Images;
import com.plumsdealscalendar.interfaces.UI_Interfaces;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;

/**
 * Created by NickNb on 29.11.2016.
 */
public class ProfileView extends Fragment implements View.OnClickListener {
    private String TAG = getClass().getSimpleName();
    UI_Interfaces UIInterfaces;
    UserDataInterfaces userDataInterfaces;
    private Realm realm;
    //private RealmChangeListener realmChangeListener;
    private Payload payload;
    //private RealmResults<Payload> person;

    ImageView profile_photo;
    TextView profile_name, profile_age, profile_plumpoints, profile_email, profile_phone, profile_gender;


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
        View view = inflater.inflate(R.layout.fr_profile_view, container, false);

        if (savedInstanceState != null) {
            //Restoring fragment state
        }
        Log.d(TAG, "Data ProfileView onCreateView");

        profile_photo = (ImageView)view.findViewById(R.id.profile_photo);
        profile_name = (TextView)view.findViewById(R.id.profile_name);
        profile_age = (TextView)view.findViewById(R.id.profile_age);
        profile_plumpoints = (TextView)view.findViewById(R.id.profile_plumpoints);
        profile_email = (TextView)view.findViewById(R.id.profile_email);
        profile_phone = (TextView)view.findViewById(R.id.profile_phone);
        profile_gender = (TextView)view.findViewById(R.id.profile_gender);

        ImageButton edit_button = (ImageButton)view.findViewById(R.id.edit_button);
        edit_button.setOnClickListener(this);
        ImageButton back_button = (ImageButton)view.findViewById(R.id.back_button);
        back_button.setOnClickListener(this);

        // Initialize Realm
        //Realm.init(getActivity());
        // Get a Realm instance for this thread

        userDataInterfaces = new UserDataImp();
        payload = userDataInterfaces.getPayload();

//        realm = Realm.getDefaultInstance();
//        payload = realm.where(Payload.class).findFirst();
        payload.addChangeListener(new RealmChangeListener<RealmModel>() {
            @Override
            public void onChange(RealmModel element) {
                Payload p = ((Payload)element);
                ShowData(p);
            }
        });

        ShowData(payload);
        return view;
    }

    void ShowData(Payload payload){
        if(payload.getSaved_image() != null) {
            profile_photo.setImageBitmap(Images.decodeBase64(payload.getSaved_image()));
        }
        if(payload.getName() != null && !payload.getName().trim().isEmpty()) {
            profile_name.setText(payload.getName());
        }
        if(payload.getAge() != 0){
            profile_age.setText(String.valueOf(payload.getAge()));
        }

        profile_plumpoints.setText(String.valueOf(payload.getPlumPoints()) + " "
                + getString(R.string.settings_plum_points));

        if(payload.getEmail() != null && !payload.getEmail().trim().isEmpty()){
            profile_email.setText(payload.getEmail());
        }
        if(payload.getPhone() != null && !payload.getPhone().trim().isEmpty()){
            profile_phone.setText(payload.getPhone());
        }
        if(payload.getGender() != null && !payload.getGender().trim().isEmpty()){
            if(payload.getGender().equals("f")) {
                profile_gender.setText("Female");
            } else if(payload.getGender().equals("m")){
                profile_gender.setText("Male");
            }
        } else {
            profile_gender.setText("Not set");
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit_button:
                UIInterfaces.ProfileEdit();
                break;
            case R.id.back_button:
                UIInterfaces.HideOuterFrame();
                UIInterfaces.onBackPressed();
                break;
        }
    }
}
