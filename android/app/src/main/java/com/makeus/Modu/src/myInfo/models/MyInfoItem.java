package com.makeus.Modu.src.myInfo.models;

public class MyInfoItem {

    private String mCategory;
    private int mPercent;
    private int mPrice;

    public MyInfoItem(String mCategory, int mPrice, int mPercent) {
        this.mCategory = mCategory;
        this.mPercent = mPercent;
        this.mPrice = mPrice;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public int getmPercent() {
        return mPercent;
    }

    public void setmPercent(int mPercent) {
        this.mPercent = mPercent;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }
}