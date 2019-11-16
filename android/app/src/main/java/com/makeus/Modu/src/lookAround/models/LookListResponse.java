package com.makeus.Modu.src.lookAround.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LookListResponse {
    @SerializedName("isSuccess")
    private boolean isSuccess;
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String messagee;
    @SerializedName("popularList")
    private ArrayList<LookListItem> popularList;
    @SerializedName("recommendList")
    private ArrayList<LookListItem> recommendList;
    @SerializedName("onlineList")
    private ArrayList<LookListItem> onlineList;
    @SerializedName("offlineList")
    private ArrayList<LookListItem> offlineList;

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessagee() {
        return messagee;
    }

    public ArrayList<LookListItem> getPopularList() {
        return popularList;
    }

    public ArrayList<LookListItem> getRecommendList() {
        return recommendList;
    }

    public ArrayList<LookListItem> getOnlineList() {
        return onlineList;
    }

    public ArrayList<LookListItem> getOfflineList() {
        return offlineList;
    }
}