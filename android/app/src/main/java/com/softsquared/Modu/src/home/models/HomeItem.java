package com.softsquared.Modu.src.home.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomeItem implements Serializable {

    @SerializedName("brand")
    private String mBrand;      //서비스명
    @SerializedName("product")
    private String mProduct;    //상품명
    @SerializedName("Category")
    private String mCategory;   //카테고리
    @SerializedName("price")
    private int mPrice;         //가격
    @SerializedName("imageUrl")
    private String mImageUrl;   //이미지 주소
    @SerializedName("dday")
    private int mDDay;          // 결제일 까지 디데이
    @SerializedName("Last")
    private String mLast;       // 마지막 결제일
    @SerializedName("duration")
    private int mDuration;      //주기
    @SerializedName("durationPer")
    private int mDurationPer;   //주기 단위
    @SerializedName("alarm")
    private int mAlarm;         //알람 몇 일 전
    @SerializedName("extra")
    private String mExtra;      //추가설명
    @SerializedName("change")
    private String mChangeUrl;    //변경 url
    @SerializedName("cancel")
    private String mCancelUrl;  //해지 url
    @SerializedName("phone")
    private String mPhone;      //해지 전화번호
    @SerializedName("currency")
    private int mCurrency;      //화폐 단위
    @SerializedName("isChecked")
    private boolean isChecked;  //알림 체크 되었는지

    public HomeItem(String mBrand, String mProduct, String mCategory, int mPrice, int mCurrency, String mImageUrl, int mDDay, String mLast, int duration, int durationPer, int alarm, String extra, String mChangeUrl, String mCancelUrl, String mPhone, boolean isChecked) {
        this.mBrand = mBrand;
        this.mProduct = mProduct;
        this.mCategory = mCategory;
        this.mImageUrl = mImageUrl;
        this.mPrice = mPrice;
        this.mDDay = mDDay;
        this.mLast = mLast;
        this.mDuration = duration;
        this.mDurationPer = durationPer;
        this.mAlarm = alarm;
        this.mExtra = extra;
        this.mChangeUrl = mChangeUrl;
        this.mCancelUrl = mCancelUrl;
        this.mPhone = mPhone;
        this.mCurrency = mCurrency;
        this.isChecked = isChecked;
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
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

    public int getmDuration() {
        return mDuration;
    }

    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public int getmAlarm() {
        return mAlarm;
    }

    public void setmAlarm(int mAlarm) {
        this.mAlarm = mAlarm;
    }

    public String getmExtra() {
        return mExtra;
    }

    public void setmExtra(String mExtra) {
        this.mExtra = mExtra;
    }

    public String getmChangeUrl() {
        return mChangeUrl;
    }

    public void setmChangeUrl(String mChangeUrl) {
        this.mChangeUrl = mChangeUrl;
    }

    public int getmDurationPer() {
        return mDurationPer;
    }

    public void setmDurationPer(int mDurationPer) {
        this.mDurationPer = mDurationPer;
    }

    public String getmLast() {
        return mLast;
    }

    public void setmLast(String mLast) {
        this.mLast = mLast;
    }

    public int getmDDay() {
        return mDDay;
    }

    public void setmDDay(int mDDay) {
        this.mDDay = mDDay;
    }

    public String getmProduct() {
        return mProduct;
    }

    public void setmProduct(String mProduct) {
        this.mProduct = mProduct;
    }
}