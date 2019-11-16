package com.makeus.Modu.src.lookAround.models;

public class LookItem {

    private String mImageUrl;
    private String mBrand;
    private String mCategory;
    private String mServiceName;
    private int mPrice;

    public LookItem(String mImageUrl, String mBrand, String mServiceName, String mCategory, int mPrice) {
        this.mImageUrl = mImageUrl;
        this.mCategory = mCategory;
        this.mBrand = mBrand;
        this.mServiceName = mServiceName;
        this.mPrice = mPrice;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmBrand() {
        return mBrand;
    }

    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public String getmServiceName() {
        return mServiceName;
    }

    public void setmServiceName(String mServiceName) {
        this.mServiceName = mServiceName;
    }
}