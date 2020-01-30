package com.softsquared.Modu.src.product.model;

import com.google.gson.annotations.SerializedName;

public class ProductItem {

    @SerializedName("Brand")
    private String mBrand;

    @SerializedName("ImageUrl")
    private String mImageUrl;

    @SerializedName("Category")
    private String mCategory;

    @SerializedName("nameEng")
    private String mEnglishName;

    @SerializedName("ProductName")
    private String mProductName;

    @SerializedName("price")
    private double mPrice;

    @SerializedName("currency")
    private String mCurrency;

    @SerializedName("changeUrl")
    private String changeUrl;

    @SerializedName("cancelUrl")
    private String cancelUrl;

    public ProductItem(String mBrand, String mProductName, String mEnglishName, String mImageUrl, String Category, double price, String currency, String changeUrl, String cancelUrl) {
        this.mImageUrl = mImageUrl;
        this.mProductName = mProductName;
        this.mEnglishName = mEnglishName;
        this.mBrand = mBrand;
        this.mCategory = Category;
        this.mPrice = price;
        this.mCurrency = currency;
        this.changeUrl = changeUrl;
        this.cancelUrl = cancelUrl;
    }

    public String getmBrand() {
        return mBrand;
    }

    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
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

    public String getmProductName() {
        return mProductName;
    }

    public void setmProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public String getmCurrency() {
        return mCurrency;
    }

    public void setmCurrency(String mCurrency) {
        this.mCurrency = mCurrency;
    }

    public String getmEnglishName() {
        return mEnglishName;
    }

    public void setmEnglishName(String mEnglishName) {
        this.mEnglishName = mEnglishName;
    }

    public String getChangeUrl() {
        return changeUrl;
    }

    public void setChangeUrl(String changeUrl) {
        this.changeUrl = changeUrl;
    }


    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }
}