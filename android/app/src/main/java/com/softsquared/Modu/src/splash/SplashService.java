package com.softsquared.Modu.src.splash;

import android.content.Context;

import org.json.JSONObject;

import com.softsquared.Modu.R;
import com.softsquared.Modu.src.splash.interfaces.SplashActivityView;
import com.softsquared.Modu.src.splash.interfaces.SplashRetrofitInterface;
import com.softsquared.Modu.src.splash.model.SplashResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.Modu.src.ApplicationClass.*;

class SplashService {

    Context mContext;
    private SplashActivityView mSplashActivityView;
    private JSONObject mParams;

    SplashService(final SplashActivityView mSplashActivityView, JSONObject mParams, Context mContext) {
        this.mSplashActivityView = mSplashActivityView;
        this.mParams = mParams;
        this.mContext = mContext;
    }

    void postAutoLogin() {

        final SplashRetrofitInterface splashRetrofitInterface = getRetrofit().create(SplashRetrofitInterface.class);
        splashRetrofitInterface.postAutoLogin(RequestBody.create(MEDIA_TYPE_JSON, mParams.toString())).enqueue(new Callback<SplashResponse>() {
            @Override
            public void onResponse(Call<SplashResponse> call, Response<SplashResponse> response) {

                final SplashResponse splashResponse = response.body();
                if (splashResponse == null) {
                    mSplashActivityView.postAutoLoginFailure(mContext.getString(R.string.network_failure));
                    return;
                }

                mSplashActivityView.postAutoLoginSuccess(splashResponse.getCode());
            }

            @Override
            public void onFailure(Call<SplashResponse> call, Throwable t) {
                mSplashActivityView.postAutoLoginFailure(mContext.getString(R.string.network_failure));
            }
        });
    }

}
