package com.duckydev.currency.database;

import android.database.Cursor;

import com.duckydev.currency.Constains;
import com.duckydev.currency.object.Currency;

/**
 * Created by DK on 8/14/2017.
 */

public class DatabaseHelper {
    public static final String TAG = DatabaseHelper.class.getName();

    private DatabaseAdapter mDatabaseAdapter;

    public DatabaseHelper(DatabaseAdapter mDatabaseAdapter) {
        this.mDatabaseAdapter = mDatabaseAdapter;
    }

//    public long insertCurrency(Currency currency) {
//        ArrayList<Currency> currencies = getCu
//    }

    private Currency getCurrency(long id) {
        Cursor cursor = mDatabaseAdapter.getWritableDatabase().query(
                Constains.CURRENCY_TABLE,
                new String[] {Constains.KEY_ID, Constains.KEY_NAME, Constains.KEY_BUY,
                            Constains.KEY_TRANSFER, Constains.KEY_SELL, Constains.KEY_UPDATE_DATE, Constains.KEY_TYPE},
                Constains.KEY_ID + " = " + id, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                return parseCurrency(cursor);
            }
        }
        return null;
    }

    private Currency parseCurrency(Cursor cursor) {
        int type = cursor.getInt(cursor.getColumnIndex(Constains.KEY_TYPE));
        Currency currency = new Currency(type);
        currency.setCurrencyName(cursor.getString(cursor.getColumnIndex(Constains.KEY_NAME)));
        currency.setBuy(cursor.getString(cursor.getColumnIndex(Constains.KEY_BUY)));
        currency.setTransfer(cursor.getString(cursor.getColumnIndex(Constains.KEY_TRANSFER)));
        currency.setSell(cursor.getString(cursor.getColumnIndex(Constains.KEY_SELL)));
        currency.setUpdated(cursor.getString(cursor.getColumnIndex(Constains.KEY_UPDATE_DATE)));

        return currency;
    }
}
