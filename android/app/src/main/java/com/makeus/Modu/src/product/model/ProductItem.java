package com.makeus.Modu.src.product.model;

import com.google.gson.annotations.SerializedName;

public class ProductItem {

    @SerializedName("Brand")
    private String mBrand;

    @SerializedName("ImageUrl")
    private String mImageUrl;

    @SerializedName("Category")
    private String mCategory;

    @SerializedName("ProductName")
    private String mProductName;

    @SerializedName("price")
    private double mPrice;

    @SerializedName("currency")
    private String mCurrency;

    public ProductItem(String mBrand, String mProductName, String mImageUrl, String Category, double price, String currency) {
        this.mImageUrl = mImageUrl;
        this.mProductName = mProductName;
        this.mBrand = mBrand;
        this.mCategory = Category;
        this.mPrice = price;
        this.mCurrency = currency;
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
}