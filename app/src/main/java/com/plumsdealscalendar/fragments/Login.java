package com.plumsdealscalendar.fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.plumsdealscalendar.R;
import com.plumsdealscalendar.http.HttpRequest;
import com.plumsdealscalendar.http.RequestType;
import com.plumsdealscalendar.interfaces.UI_Interfaces;
import com.plumsdealscalendar.models.Parser;
import com.plumsdealscalendar.models.login.Error;
import com.plumsdealscalendar.models.login.Payload;
import com.plumsdealscalendar.utils.Images;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.realm.Realm;

/**
 * Created by NickNb on 24.11.2016.
 */
public class Login extends Fragment implements HttpRequest {
    private String TAG = getClass().getSimpleName();
    UI_Interfaces UIInterfaces;
    Payload payload;
    Error error;
    boolean get_data;

    public Login() {
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fr_login, container, false);

        HashMap params = new HashMap<String, String>();
        params.put("email", "max@max.com");
        params.put("password", "123123");

        RequestType login = new RequestType(getActivity(), 1, this);
        login.StringPostRequest(params);

        return view;
    }

    @Override
    public void string_result(int type, String result) {
        try {
            JSONObject data = new JSONObject(result);
            switch (data.optInt("status")) {
                case 1:
                    payload = Parser.getPayload(data.getJSONObject("payload"));
                    if (payload.getImage() != null) {
                        Log.d(TAG, "Data url =" + payload.getImage());
                        LoadImage(payload.getImage());
                    }

                    break;
                case 2:
                    error = Parser.getError(data.getJSONArray("error").getJSONObject(0));
                    Login_error(error);
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void image_result(int type, Bitmap bitmap) {
        Log.d(TAG, "Data image_result OK");
        if (bitmap != null) {
            payload.setSaved_image(Images.encodeTobase64(bitmap));
            Login_ok();
        }
    }

    @Override
    public void http_error(int type, String error) {
        switch (type) {
            case 1:
                break;
            case 9:
                Log.d(TAG, "Data http_error 9");
                Login_ok();
                break;
        }
    }

    void Login_ok() {
        Log.d(TAG, "Data Login_ok");
        // Initialize Realm
        Realm.init(getActivity());
        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();

        // Persist your data in a transaction
        realm.beginTransaction();
        realm.delete(Payload.class);
        Payload copyToRealm = realm.copyToRealm(payload); // Persist unmanaged objects
        realm.commitTransaction();
        UIInterfaces.LoginComplete(payload);
    }

    void Login_error(Error error) {
        Toast.makeText(getActivity(), "Error type " + error.getMessage(), Toast.LENGTH_LONG).show();
    }

    void LoadImage(String image) {
        RequestType login = new RequestType(getActivity(), 9, this);
        login.makeImageRequest(image);
    }
}
