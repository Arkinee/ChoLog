package com.makeus.ChoLog.src.home.models;

public class HomeItem {

    private String mBrand;      //서비스명
    private String mCategory;   //카테고리
    private int mDDay;
    private int mPrice;         //가격
    private String mImageUrl;   //이미지 주소
    private int mDuration;      //주기
    private int mDurationPer;
    private int mAlarm;         //알람 몇 일 전
    private int mAlarmPer;
    private String mExtra;      //추가설명
    private String mChangeUrl;    //변경 url
    private String mCancelUrl;  //해지 url
    private String mPhone;      //해지 전화번호
    private int mCurrency;      //화폐 단위
    private boolean isChecked;  //알림 체크 되었는지

    public HomeItem(String mBrand, String mCategory, int mDDay, int mPrice, int mCurrency, String mImageUrl, int duration,int durationPer, int alarm, int alarmPer, String extra, String mChangeUrl, String mCancelUrl, String mPhone, boolean isChecked) {
        this.mBrand = mBrand;
        this.mCategory = mCategory;
        this.mDDay = mDDay;
        this.mPrice = mPrice;
        this.mImageUrl = mImageUrl;
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

    public int getmDDay() {
        return mDDay;
    }

    public void setmDDay(int mDDay) {
        this.mDDay = mDDay;
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
}