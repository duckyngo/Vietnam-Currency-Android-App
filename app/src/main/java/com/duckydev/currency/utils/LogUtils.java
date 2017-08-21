package com.duckydev.currency.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by DK on 8/11/2017.
 */

public class LogUtils {
    public static StringBuffer sStringBuffer = new StringBuffer();

    public interface LogListenner{
        void onLogged(StringBuffer log);
    }

    private static LogListenner sLogListenner;

    public static void log(String tag, String message) {
        Log.d(tag, message);
        StringBuilder stringBuilder = new StringBuilder();
        String date = formatDate(Calendar.getInstance());
        stringBuilder.append(date);
        stringBuilder.append(" ");
        stringBuilder.append(tag);
        stringBuilder.append(" ");
        stringBuilder.append(message);
        stringBuilder.append("\n\n");
        sStringBuffer.insert(0, stringBuilder.toString());
        printLogs();
    }

    private static String formatDate(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd hh:mm:ss");
        return simpleDateFormat.format(calendar.getTime());
    }

    private static void printLogs() {
        if (sLogListenner != null) {
            sLogListenner.onLogged(sStringBuffer);
        }
    }

    public static void clearLogs() {
        sStringBuffer = new StringBuffer();
        printLogs();
    }

    public static void setLogListener(LogListenner logListener) {
        sLogListenner = logListener;
    }
}
