package com.plumsdealscalendar.http;

import android.graphics.Bitmap;

/**
 * Created by NickNb on 28.11.2016.
 */
public interface HttpRequest {
    void string_result(int type, String result);
    void image_result(int type, Bitmap bitmap);
    void http_error(int type, String error);
}
