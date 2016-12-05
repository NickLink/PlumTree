package com.plumsdealscalendar.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import com.plumsdealscalendar.models.login.Payload;

import java.text.DateFormatSymbols;

/**
 * Created by NickNb on 30.11.2016.
 */
public class Converters {

    public static Payload cloneObject(Payload payloadFrom){
        Payload payloadTo = new Payload();
        payloadTo.setUserId(payloadFrom.getUserId());
        payloadTo.setName(payloadFrom.getName());
        payloadTo.setEmail(payloadFrom.getEmail());
        payloadTo.setGender(payloadFrom.getGender());
        payloadTo.setThemeId(payloadFrom.getThemeId());
        payloadTo.setAge(payloadFrom.getAge());
        payloadTo.setImage(payloadFrom.getImage());
        payloadTo.setApiHash(payloadFrom.getApiHash());
        payloadTo.setCity(payloadFrom.getCity());
        payloadTo.setZip(payloadFrom.getZip());
        payloadTo.setPhone(payloadFrom.getPhone());
        payloadTo.setBirthday(payloadFrom.getBirthday());
        payloadTo.setPlumPoints(payloadFrom.getPlumPoints());
        payloadTo.setContactsCount(payloadFrom.getContactsCount());
        payloadTo.setFollowedBusiness(payloadFrom.getFollowedBusiness());
        return payloadTo;
    }

    public static String getMonth(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
}
