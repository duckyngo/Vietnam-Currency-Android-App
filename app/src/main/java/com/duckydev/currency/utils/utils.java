package com.duckydev.currency.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DK on 8/15/2017.
 */

public class utils {

    public static Date stringToDate(String string) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
        try {
            Date date = format.parse(string);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
