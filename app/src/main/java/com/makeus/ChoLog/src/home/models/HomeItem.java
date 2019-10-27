package com.makeus.ChoLog.src.home.models;

public class HomeItem {

    private String mDate;
    private int mDDay;
    private String mImageUrl;
    private String mCategory;
    private String mBrand;
    private int mWhile;
    private int mPrice;
    private boolean isChecked;
    private String mPlanUrl;
    private String mCancelUrl;
    private String mPhone;
    private int mCurrency;

    public HomeItem(String mDate, int mDDay, String mImageUrl, String mCategory, String mBrand, int mWhile, int mPrice, boolean isChecked, String mPlanUrl, String mCancelUrl, String mPhone, int mCurrency) {
        this.mDate = mDate;
        this.mDDay = mDDay;
        this.mImageUrl = mImageUrl;
        this.mCategory = mCategory;
        this.mBrand = mBrand;
        this.mWhile = mWhile;
        this.mPrice = mPrice;
        this.isChecked = isChecked;
        this.mPlanUrl = mPlanUrl;
        this.mCancelUrl = mCancelUrl;
        this.mPhone = mPhone;
        this.mCurrency = mCurrency;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public int getmDDay() {
        return mDDay;
    }

    public void setmDDay(int mDDay) {
        this.mDDay = mDDay;
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

    public int getmWhile() {
        return mWhile;
    }

    public void setmWhile(int mWhile) {
        this.mWhile = mWhile;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getmPlanUrl() {
        return mPlanUrl;
    }

    public void setmPlanUrl(String mPlanUrl) {
        this.mPlanUrl = mPlanUrl;
    }

    public String getmCancelUrl() {
        return mCancelUrl;
    }

    public void setmCancelUrl(String mCancelUrl) {
        this.mCancelUrl = mCancelUrl;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public int getmCurrency() {
        return mCurrency;
    }

    public void setmCurrency(int mCurrency) {
        this.mCurrency = mCurrency;
    }
}