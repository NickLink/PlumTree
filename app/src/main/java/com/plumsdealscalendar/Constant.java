package com.plumsdealscalendar;

/**
 * Created by NickNb on 28.11.2016.
 */
public class Constant {
    public static final String API_BASE = "http://api.plumtreeapp.com/v1/";
    public static final String API_Acess_token = "?access-token=";
    public static final String API_Login = API_BASE + "users/check-user-auth";
    public static final String API_Save_Profile = API_BASE + "users/update-user-profile/";

    public static final String TAG_JSON = "json_request1974";

    public static final int REQUEST_SUCCESS = 1;
    public static final int REQUEST_ERROR = 2;
}
