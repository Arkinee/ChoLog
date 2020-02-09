package com.softsquared.Modu.src.serviceAdd;

import android.content.Context;

import com.softsquared.Modu.src.serviceAdd.interfaces.ServiceAddActivityView;

import org.json.JSONObject;

class ServiceAddService {

    private ServiceAddActivityView mServiceAddActivityView;
    private JSONObject mParams;
    private Context mContext;

    ServiceAddService(final ServiceAddActivityView mServiceAddActivityView, Context context, JSONObject params) {
        this.mServiceAddActivityView = mServiceAddActivityView;
        this.mParams = params;
        this.mContext = context;
    }

}
