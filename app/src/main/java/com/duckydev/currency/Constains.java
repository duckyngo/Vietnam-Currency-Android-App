package com.duckydev.currency;

/**
 * Created by DK on 8/11/2017.
 */

public class Constains {
    // URL for retrieval of currency exchange rates and gold price
    public static final String CURRENCY_URL = "https://www.vietcombank.com.vn/ExchangeRates/ExrateXML.aspx";
    public static final String CURRENCY_SRC = "https://www.vietcombank.com.vn/ExchangeRates/";
    public static final String GOLD_PRICE_URL = "http://sjc.com.vn/xml/tygiavang.xml";
    public static final String GOLD_PRICE_SRC = "http://sjc.com.vn/price/";

    //  Constrains used for Database and table

    public static final String DATABASE_NAME = "Gold_CurrencyDB";

    public static final String CURRENCY_TABLE = "currencies";
    public static final String KEY_ID = "_id";
    public static final String KEY_BUY = "buy";
    public static final String KEY_TRANSFER = "transfer";
    public static final String KEY_SELL = "sell";
    public static final String KEY_NAME = "name";
    public static final String KEY_UPDATE_DATE = "update_date";
    public static final String KEY_TYPE = "type";

    // Constrains for all Currency code and name
    public static final int CURRENCY_CODE_SIZE = 19;

    public static final String[] CURRENCY_CODES = {
            "AUD", "CAD", "CHF", "DKK", "EUR", "GBP", "HKD", "INR", "JPY", "KRW",
            "KWD", "MYR", "NOK", "RUB", "SAR", "SEK", "SGD", "THB", "USD"
    };

    public static final String[] CURRENCY_NAMES = {
            "Australian Dollar", "Canadian Dollar", "Swiss Franc", "Danish Krone", "Euro", "British Pound", "Hong Kong Dollar", "Indian Rupee", "Japanese Yen",
            "Sound Korean Won", "Kuwaiti Dinar", "Malaysian Ringgit", "Norwegian Krone", "Russian Ruble", "Saudi Rial", "Swedish Krona", "Singapore Dollar", "Thai Baht","US Dollar"
    };

    public static final String[] CURRENCY_NAMES_VI = {
            "Dollar Úc", "Dollar Canada", "Franc Thụy Sĩ", "Krone Đan Mạch", "Euro", "Bảng Anh", "Dollar Hong Kong", "Rupee Ấn Độ", "Yên Nhật",
            "Won Hàn Quốc", "Dinar Kuwait", "Ringgit Malaysia", "Krone Na Uy", "Rúp Nga", "Rial Saudi", "Krona Thụy Điển", "Dollar Singapore", "Baht Thai","Dollar Mỹ"
    };

    //Constants for notification
    public static final int NOTIFICATION_ID = 100;
    public static final int REQUEST_ID_NUM = 101;

    //Constains for SharedPreferences
    public static final String CURRENCY_PREFERENCES = "CURRENCY_PREFERENCES";
    public static final String LANGUAGE = "LANGUAGE";
    public static final String RATE_CLICK_COUNT = "rate_count";

    //Constants for web connection

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 10000;

    // Constans for parse XML inputstrean
    public static final String XML_EXRATE = "Exrate";
    public static final String XML_CURRENCY_CODE = "CurrencyCode";
    public static final String XML_CURRENCY_NAME = "CurrencyName";
    public static final String XML_BUY = "Buy";
    public static final String XML_TRANSFER = "Transfer";
    public static final String XML_SELL = "Sell";

    // Constans for parse XML sjc
    public static final String XML_GOLD_CITY = "city";
    public static final String XML_GOLD_BUY = "buy";
    public static final String XML_GOLD_SELL = "sell";
    public static final String XML_GOLD_TYPE = "type";
    public static final String XML_GOLD_CITY_NAME = "name";
    public static final String XML_GOLD_RATELIST = "ratelist";
    public static final String XML_GOLD_UPDATED = "updated";
    public static final String XML_GOLD_UNIT = "unit";


}
