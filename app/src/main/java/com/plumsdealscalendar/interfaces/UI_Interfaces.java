package com.plumsdealscalendar.interfaces;

import com.plumsdealscalendar.models.login.Payload;

/**
 * Created by NickNb on 29.11.2016.
 */
public interface UI_Interfaces {
    void LoginComplete(Payload payload);
    void ProfileView();
    void ProfileEdit();



    void HideOuterFrame();
    void onBackPressed();
}
