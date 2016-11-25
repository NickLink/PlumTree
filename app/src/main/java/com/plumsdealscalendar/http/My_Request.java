package com.plumsdealscalendar.http;

import com.plumsdealscalendar.models.login.RegistrationBody;
import com.plumsdealscalendar.models.login.UserData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by NickNb on 24.11.2016.
 */
public interface My_Request {

//    @GET("/users/{username}")
//    Call<UserData> getUser(@Path("username") String username);
//        @Headers({
//                "Accept: application/json",
//                "Content-Type: application/json"
//        })
        @POST("users/check-user-auth")
        //Call<UserData> getUser(@Body RegistrationBody registrationBody);
        Call<UserData> getUser(@Body RegistrationBody registrationBody);

}
