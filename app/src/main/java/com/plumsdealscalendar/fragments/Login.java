package com.plumsdealscalendar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.plumsdealscalendar.R;
import com.plumsdealscalendar.app.MyApplication;
import com.plumsdealscalendar.models.login.RegistrationBody;
import com.plumsdealscalendar.models.login.UserData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NickNb on 24.11.2016.
 */
public class Login extends Fragment {

    public Login(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fr_login, container, false);

        Call<UserData> call = MyApplication.getInstance().service.getUser(new RegistrationBody("max@max.com", "123123"));
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if(response.body() instanceof UserData) {
                    Log.d("Success", "UserData success code " + response.code());
                    Log.d("Success", "UserData success body name "
                            + ((UserData) response.body()).getPayload().getName());
                } else {
                    Log.d("Success", "UserData unknown code " + response.code());
                }

            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });


        return view;
    }
}
