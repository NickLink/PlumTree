package com.plumsdealscalendar.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.plumsdealscalendar.Const;
import com.plumsdealscalendar.app.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by NickNb on 28.11.2016.
 */
public class RequestType {
    private static String TAG = "Requests";
    private Context context;
    private HttpRequest request;
    int req_type;

    public RequestType(Context context, int req_type, HttpRequest request) { //, Context context
        this.context = context;
        this.request = request;
        this.req_type = req_type;
    }

    public void StringPostRequest(final HashMap<String, String> params) {
            StringRequest stringObjReq = new StringRequest(Request.Method.POST, Const.API_PATH,
                    new Response.Listener<String>() { //jsonBody
                @Override
                public void onResponse(String response) {
                    request.string_result(req_type, response.toString());
                }
            },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        request.http_error(req_type, ErrorMessage(error));
                    }
                })
            {
                @Override
                public String getBodyContentType() {
                    return "application/json";
                }
                @Override
                public byte[] getBody() {
                    return getJsonBody(params);
                }
            };
            MyApplication.getInstance().addToRequestQueue(stringObjReq, Const.TAG_JSON);
    }

    private byte[] getJsonBody(HashMap<String, String> params){
        JSONObject jsonBody = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                jsonBody.put(entry.getKey().toString(), entry.getValue().toString());
            }
            return jsonBody == null ? null : jsonBody.toString().getBytes("utf-8");
        } catch (JSONException e) {
            return null;
        }
        catch (UnsupportedEncodingException uee) {
            return null;
        }
    }

    private String ErrorMessage(VolleyError error) {
        if (error instanceof NetworkError) {
            return "Cannot connect to Internet...Please check your connection!";
        } else if (error instanceof ServerError) {
            return "The server could not be found. Please try again after some time!!";
        } else if (error instanceof AuthFailureError) {
            return "Cannot connect to Internet...Please check your connection!";
        } else if (error instanceof ParseError) {
            return "Parsing error! Please try again after some time!!";
        } else if (error instanceof NoConnectionError) {
            return "Cannot connect to Internet...Please check your connection!";
        } else if (error instanceof TimeoutError) {
            return "Connection TimeOut! Please check your internet connection.";
        } else
            return "Absolytly unknown error...";
    }
}
