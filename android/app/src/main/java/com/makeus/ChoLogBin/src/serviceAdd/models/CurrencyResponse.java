package com.makeus.ChoLogBin.src.serviceAdd.models;

import com.google.gson.annotations.SerializedName;

public class CurrencyResponse {
    @SerializedName("code")
    private String code;
    @SerializedName("currencyCode")
    private String currencyCoe;
    @SerializedName("currencyName")
    private String currencyName;
    @SerializedName("country")
    private String country;
    @SerializedName("name")
    private String name;
    @SerializedName("date")
    private String date;
    @SerializedName("time")
    private String time;
    @SerializedName("recurrenceCount")
    private int recurrenceCount;
    @SerializedName("basePrice")
    private Double basePrice;
    @SerializedName("openingPrice")
    private Double openingPrice;
    @SerializedName("highPrice")
    private Double highPrice;
    @SerializedName("lowPrice")
    private Double lowPrice;
    @SerializedName("change")
    private String change;
    @SerializedName("changePrice")
    private Double changePrice;
    @SerializedName("cashBuyingPrice")
    private Double cashBuyingPrice;
    @SerializedName("cashSellingPrice")
    private Double cashSellingPrice;
    @SerializedName("ttBuyingPrice")
    private Double ttBuyingPrice;
    @SerializedName("ttSellingPrice")
    private Double ttSellingPrice;
    @SerializedName("tcBuyingPrice")
    private Double tcBuyingPrice;
    @SerializedName("fcSellingPrice")
    private Double fcSellingPrice;
    @SerializedName("exchangeCommission")
    private Double exchangeCommission;
    @SerializedName("usDollarRate")
    private Double usDollarRate;
    @SerializedName("high52wPrice")
    private Double high52wPrice;
    @SerializedName("high52wDate")
    private String high52wDate;
    @SerializedName("low52wPrice")
    private Double low52wPrice;
    @SerializedName("low52wDate")
    private String low52wDate;
    @SerializedName("currencyUnit")
    private int currencyUnit;
    @SerializedName("provider")
    private String provider;

    public Double getBasePrice() {
        return basePrice;
    }
}