package com.softsquared.Modu.src.main.models;

import com.google.gson.annotations.SerializedName;

public class UpLoadResponse {

    @SerializedName("result")
    private int result;
    @SerializedName("isSuccess")
    private boolean isSuccess;
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getResult() {
        return result;
    }
}