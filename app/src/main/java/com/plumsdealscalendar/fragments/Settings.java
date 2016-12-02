package com.plumsdealscalendar.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.plumsdealscalendar.R;
import com.plumsdealscalendar.models.login.Payload;
import com.plumsdealscalendar.utils.Images;
import com.plumsdealscalendar.interfaces.UI_Interfaces;

import io.realm.Realm;

/**
 * Created by NickNb on 23.11.2016.
 */
public class Settings extends Fragment implements View.OnClickListener {
    private String TAG = getClass().getSimpleName();
    private Payload payload;
    UI_Interfaces UIInterfaces;

//    public Settings newInstance(Payload payload){
//        Settings fragment = new Settings();
//        Bundle args = new Bundle();
//        args.putString("name", payload.getName());
//        fragment.setArguments(args);
//        return fragment;
//    }

    public Settings(){
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

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
//            myYear = year;
//            myMonth = monthOfYear;
//            myDay = dayOfMonth;
//            tvDate.setText("Today is " + myDay + "/" + myMonth + "/" + myYear);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fr_settings, container, false);

        if(savedInstanceState != null){
            //Restoring fragment state
        }

        RelativeLayout profile_rl = (RelativeLayout)view.findViewById(R.id.profile_rl);
        profile_rl.setOnClickListener(this);

        RelativeLayout fbusines_rl = (RelativeLayout)view.findViewById(R.id.fbusines_rl);
        fbusines_rl.setOnClickListener(this);

        RelativeLayout appsettings_rl = (RelativeLayout)view.findViewById(R.id.appsettings_rl);
        appsettings_rl.setOnClickListener(this);

        RelativeLayout feedback_rl = (RelativeLayout)view.findViewById(R.id.feedback_rl);
        feedback_rl.setOnClickListener(this);

        RelativeLayout privatpolicy_rl = (RelativeLayout)view.findViewById(R.id.privatpolicy_rl);
        privatpolicy_rl.setOnClickListener(this);

        RelativeLayout show_tutorial_rl = (RelativeLayout)view.findViewById(R.id.show_tutorial_rl);
        show_tutorial_rl.setOnClickListener(this);

        RelativeLayout exit_rl = (RelativeLayout)view.findViewById(R.id.exit_rl);
        exit_rl.setOnClickListener(this);

        ImageView profile_photo = (ImageView)view.findViewById(R.id.profile_photo);
        TextView profile_name = (TextView)view.findViewById(R.id.profile_name);
        TextView profile_email = (TextView)view.findViewById(R.id.profile_email);
        TextView fbusines_count = (TextView)view.findViewById(R.id.fbusines_count);

        // Initialize Realm
        Realm.init(getActivity());
        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();
        Payload payload = realm.where(Payload.class).findFirst();

        if(payload.getSaved_image() != null){
            profile_photo.setImageBitmap(Images.decodeBase64(payload.getSaved_image()));
        }
        if(payload.getName() != null && !payload.getName().trim().isEmpty()){
            profile_name.setText(payload.getName());
        }
        if(payload.getEmail() != null && !payload.getEmail().trim().isEmpty()){
            profile_email.setText(payload.getEmail());
        }
        if(payload.getFollowedBusiness() != 0){
            fbusines_count.setText(String.valueOf(payload.getFollowedBusiness()));
        }



        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_rl:
                UIInterfaces.ProfileView();
                break;

            case R.id.fbusines_rl:
                break;

            case R.id.appsettings_rl:
                break;

            case R.id.feedback_rl:
                break;

            case R.id.privatpolicy_rl:
                break;

            case R.id.show_tutorial_rl:
                break;

            case R.id.exit_rl:
                break;

        }
    }
}
