package com.plumsdealscalendar.models.login;

/**
 * Created by NickNb on 24.11.2016.
 */
public class RegistrationBody {
    public String email;
    public String password;

    public RegistrationBody(String email, String password){
        this.email = email;
        this.password = password;
    }
}
