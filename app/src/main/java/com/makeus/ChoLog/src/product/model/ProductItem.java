package com.makeus.ChoLog.src.product.model;

import com.google.gson.annotations.SerializedName;

public class ProductItem {

    @SerializedName("Brand")
    private String mBrand;

    @SerializedName("ImageUrl")
    private String mImageUrl;

    public ProductItem(String mImageUrl, String mBrand) {
        this.mImageUrl = mImageUrl;
        this.mBrand = mBrand;
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
}