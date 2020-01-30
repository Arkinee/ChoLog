package com.softsquared.Modu.src.main.models;

import com.google.gson.annotations.SerializedName;

public class Items {
    @SerializedName("companyName")
    private String companyName;
    @SerializedName("productName")
    private String productName;
    @SerializedName("nameEng")
    private String englishName;
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
    @SerializedName("planChange")
    private String changeUrl;
    @SerializedName("cancel")
    private String cancelUrl;

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

    public String getEnglishName() {
        return englishName;
    }
}