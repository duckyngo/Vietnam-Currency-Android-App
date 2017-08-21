package com.duckydev.currency.object;

/**
 * Created by DK on 8/11/2017.
 */

public class GoldPrice {
    public static final int ITEM = 0;
    public static final int SECTION = 1;

    private final int mType;
    private String mCityName;
    private String mGoldType;
    private String mBuy;
    private String mSell;
    private String mUpdated;
    private String mUnit;

    public GoldPrice(int mType) {
        this.mType = mType;
    }

    public GoldPrice(int mType, String mCityName, String mGoldType, String mBuy, String mSell) {
        this.mType = mType;
        this.mCityName = mCityName;
        this.mGoldType = mGoldType;
        this.mBuy = mBuy;
        this.mSell = mSell;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public String getGoldType() {
        return mGoldType;
    }

    public void setGoldType(String mType) {
        this.mGoldType = mType;
    }

    public String getBuy() {
        return mBuy;
    }

    public void setBuy(String mBuy) {
        this.mBuy = mBuy;
    }

    public String getSell() {
        return mSell;
    }

    public void setSell(String mSell) {
        this.mSell = mSell;
    }

    public String getUpdated() {
        return mUpdated;
    }

    public void setUpdated(String mUpdated) {
        this.mUpdated = mUpdated;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String mUnit) {
        this.mUnit = mUnit;
    }

    public int getType() {
        return mType;
    }
}
