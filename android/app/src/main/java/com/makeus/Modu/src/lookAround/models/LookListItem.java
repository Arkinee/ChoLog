package com.makeus.Modu.src.lookAround.models;

import com.google.gson.annotations.SerializedName;

public class LookListItem {
    @SerializedName("companyName")
    private String companyName;
    @SerializedName("productName")
    private String productName;
    @SerializedName("price")
    private double price;
    @SerializedName("category")
    private String category;
    @SerializedName("logo")
    private String logo;
    @SerializedName("exchangeRate")
    private String exchangeRate;

    public String getCompanyName() {
        return companyName;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getLogo() {
        return logo;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }
}