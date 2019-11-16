package com.makeus.Modu.src.home.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomeItem implements Serializable {

    @SerializedName("brand")
    private String mBrand;      //서비스명
    @SerializedName("Category")
    private String mCategory;   //카테고리
    @SerializedName("price")
    private int mPrice;         //가격
    @SerializedName("imageUrl")
    private String mImageUrl;   //이미지 주소
    @SerializedName("dday")
    private int mDDay;
    @SerializedName("ddayFix")
    private int mDDayFix;
    @SerializedName("Last")
    private String mLast;
    @SerializedName("duration")
    private int mDuration;      //주기
    @SerializedName("durationPer")
    private int mDurationPer;
    @SerializedName("alarm")
    private int mAlarm;         //알람 몇 일 전
    @SerializedName("alarmPer")
    private int mAlarmPer;
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

    public HomeItem(String mBrand, String mCategory, int mPrice, int mCurrency, String mImageUrl, int mDDay, int mDDayFix, String mLast, int duration, int durationPer, int alarm, int alarmPer, String extra, String mChangeUrl, String mCancelUrl, String mPhone, boolean isChecked) {
        this.mBrand = mBrand;
        this.mCategory = mCategory;
        this.mImageUrl = mImageUrl;
        this.mPrice = mPrice;
        this.mDDay = mDDay;
        this.mDDayFix = mDDayFix;
        this.mLast = mLast;
        this.mDuration = duration;
        this.mDurationPer = durationPer;
        this.mAlarm = alarm;
        this.mAlarmPer = alarmPer;
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

    public int getmAlarmPer() {
        return mAlarmPer;
    }

    public void setmAlarmPer(int mAlarmPer) {
        this.mAlarmPer = mAlarmPer;
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

    public int getmDDayFix() {
        return mDDayFix;
    }

    public void setmDDayFix(int mDDayFix) {
        this.mDDayFix = mDDayFix;
    }
}