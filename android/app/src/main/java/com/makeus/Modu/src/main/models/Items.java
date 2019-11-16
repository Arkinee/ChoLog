package com.makeus.Modu.src.main.models;

import com.google.gson.annotations.SerializedName;

public class Items {
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
    @SerializedName("type")
    private String type;
    @SerializedName("exchangeRate")
    private String currency;

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

    public String getType() {
        return type;
    }

    public String getCurrency() {
        return currency;
    }
}