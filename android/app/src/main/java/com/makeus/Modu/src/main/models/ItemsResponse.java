package com.makeus.Modu.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ItemsResponse {
    @SerializedName("isSuccess")
    private boolean isSuccess;
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String messagee;
    @SerializedName("everyProduct")
    private ArrayList<Items> products;

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessagee() {
        return messagee;
    }

    public List<Items> getProducts() {
        return products;
    }
}