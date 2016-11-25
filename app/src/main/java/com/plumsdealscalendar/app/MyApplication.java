package com.plumsdealscalendar.app;

import android.app.Application;

import com.plumsdealscalendar.R;
import com.plumsdealscalendar.http.My_Request;
import com.plumsdealscalendar.http.SessionRequestInterceptor;

import io.realm.Realm;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by NickNb on 24.11.2016.
 */
public class MyApplication extends Application {

    public My_Request service;
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        Realm.init(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/robotoregular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(UserData.class, new ItemInstanceCreator());

        OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new SessionRequestInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.plumtreeapp.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(defaultHttpClient)
                .build();

        service = retrofit.create(My_Request.class);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

}