package com.makeus.ChoLog.src.product.model;

import com.google.gson.annotations.SerializedName;

public class ProductItem {

    @SerializedName("Brand")
    private String mBrand;

    @SerializedName("ImageUrl")
    private String mImageUrl;

    @SerializedName("Category")
    private String mCategory;

    public ProductItem(String mBrand, String mImageUrl, String Category) {
        this.mImageUrl = mImageUrl;
        this.mBrand = mBrand;
        this.mCategory = Category;
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
}