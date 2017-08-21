package com.duckydev.currency.object;

/**
 * Created by DK on 8/11/2017.
 */

public class Currency {
    public static final int ITEM = 0;
    public static final int SECTION = 1;

    private final int mType;
    private String mCurrencyCode;
    private String mCurrencyName;
    private String mBuy;
    private String mTransfer;
    private String mSell;
    private String mUpdated;
    private int imageFlag;

    public int sectionPosition;
    public int listPosition;

    public Currency(int mType) {
        this.mType = mType;
    }

    public Currency(int mType, String mCurrencyCode, String mCurrencyName, String mBuy, String mTransfer, String mSell) {
        this.mType = mType;
        this.mCurrencyCode = mCurrencyCode;
        this.mCurrencyName = mCurrencyName;
        this.mBuy = mBuy;
        this.mTransfer = mTransfer;
        this.mSell = mSell;
    }

    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    public void setCurrencyCode(String mCurrencyCode) {
        this.mCurrencyCode = mCurrencyCode;
    }

    public String getCurrencyName() {
        return mCurrencyName;
    }

    public void setCurrencyName(String mCurrencyName) {
        this.mCurrencyName = mCurrencyName;
    }

    public String getBuy() {
        return mBuy;
    }

    public void setBuy(String mBuy) {
        this.mBuy = mBuy;
    }

    public String getTransfer() {
        return mTransfer;
    }

    public void setTransfer(String mTransfer) {
        this.mTransfer = mTransfer;
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

    public int getImageFlag() {
        return imageFlag;
    }

    public void setImageFlag(int imageFlag) {
        this.imageFlag = imageFlag;
    }

    public int getType() {
        return mType;
    }
}
