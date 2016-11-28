package com.plumsdealscalendar.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.plumsdealscalendar.R;
import com.plumsdealscalendar.http.HttpRequest;
import com.plumsdealscalendar.http.RequestType;
import com.plumsdealscalendar.models.Parser;
import com.plumsdealscalendar.models.login.Error;
import com.plumsdealscalendar.models.login.Payload;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.realm.Realm;

/**
 * Created by NickNb on 24.11.2016.
 */
public class Login extends Fragment implements HttpRequest{
    private String TAG = getClass().getSimpleName();
    LoginCompleteListener loginCompleteListener;

    public interface LoginCompleteListener {
        public void LoginComplete(Payload payload);
    }

    public Login(){
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            loginCompleteListener = (LoginCompleteListener) activity;
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
            switch (data.optInt("status")){
                case 1:
                    Payload payload = Parser.getPayload(data.getJSONObject("payload"));
                    Login_ok(payload);
                    break;
                case 2:
                    Error error = Parser.getError(data.getJSONArray("error").getJSONObject(0));
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
    public void http_error(int type, String error) {

    }

    void Login_ok(Payload payload){
        // Initialize Realm
        Realm.init(getActivity());
        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();

        // Persist your data in a transaction
        realm.beginTransaction();
        realm.delete(Payload.class);
        Payload copyToRealm = realm.copyToRealm(payload); // Persist unmanaged objects
        realm.commitTransaction();
        loginCompleteListener.LoginComplete(payload);
    }

    void Login_error(Error error){

    }
}
