package com.duckydev.currency.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.duckydev.currency.Constains;

/**
 * Created by DK on 8/11/2017.
 */

public class SharedPreferenceUtils {
    public static void updateRateClickCount(Context context, int rateCount) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                Constains.CURRENCY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constains.RATE_CLICK_COUNT, rateCount);
        editor.commit();
    }

    public static int getRateClickCount(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                Constains.CURRENCY_PREFERENCES, Context.MODE_PRIVATE
        );
        return sharedPreferences.getInt(Constains.RATE_CLICK_COUNT, 0);
    }
}
