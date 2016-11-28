package com.plumsdealscalendar.http;

/**
 * Created by NickNb on 28.11.2016.
 */
public interface HttpRequest {
    void string_result(int type, String result);
    void http_error(int type, String error);
}
