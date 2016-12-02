package com.plumsdealscalendar.utils;

import java.text.DateFormatSymbols;

/**
 * Created by NickNb on 30.11.2016.
 */
public class Converters {

    public static String getMonth(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }
}
