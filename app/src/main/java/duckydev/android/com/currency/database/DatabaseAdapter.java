package duckydev.android.com.currency.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import duckydev.android.com.currency.Constains;
import duckydev.android.com.currency.utils.LogUtils;

/**
 * Created by DK on 8/14/2017.
 */

public class DatabaseAdapter extends SQLiteOpenHelper {
    public static final String TAG = DatabaseAdapter.class.getName();

    public static final int DATABASE_VERSION = 1;

    public static final String CURRENCY_TABLE_CREATE = "create table " + Constains.CURRENCY_TABLE + " (" +
            Constains.KEY_ID + " integer primary key autoincrement, " +
            Constains.KEY_NAME + " text not null, " +
            Constains.KEY_BUY + " text not null, " +
            Constains.KEY_TRANSFER + " text not null, " +
            Constains.KEY_SELL + " text not null, " +
            Constains.KEY_UPDATE_DATE + " text not null, " +
            Constains.KEY_TYPE + " real);";


    public DatabaseAdapter(Context context) {
        super(context, Constains.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CURRENCY_TABLE_CREATE);
            LogUtils.log(TAG, "Currency table created");
        } catch (SQLException e) {
            LogUtils.log(TAG, "Currency Table createion error");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        clearCurrencyTable(db);
        onCreate(db);
    }

    private void clearCurrencyTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + Constains.CURRENCY_TABLE);
    }


}
