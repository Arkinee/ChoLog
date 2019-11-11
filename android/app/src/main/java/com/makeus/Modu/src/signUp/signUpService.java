package com.makeus.Modu.src.signUp;

import android.content.Context;

import com.makeus.Modu.R;
import com.makeus.Modu.src.signUp.interfaces.SignUpActivityView;
import com.makeus.Modu.src.signUp.interfaces.SignUpRetrofitInterface;
import com.makeus.Modu.src.signUp.models.SignUpResponse;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.Modu.src.ApplicationClass.MEDIA_TYPE_JSON;
import static com.makeus.Modu.src.ApplicationClass.getRetrofit;

class signUpService {

    Context mContext;
    private SignUpActivityView mSignUpActivityView;
    private JSONObject mParams;

    signUpService(final SignUpActivityView mSignUpActivityView, JSONObject mParams, Context mContext) {
        this.mSignUpActivityView = mSignUpActivityView;
        this.mParams = mParams;
        this.mContext = mContext;
    }

    void postSignUp() {

        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpRetrofitInterface.postAutoLogin(RequestBody.create(MEDIA_TYPE_JSON, mParams.toString())).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {

                final SignUpResponse signUpResponse = response.body();
                if (signUpResponse == null) {
                    mSignUpActivityView.postSignUpFailure(mContext.getString(R.string.network_failure));
                    return;
                }

                mSignUpActivityView.postSignUpSuccess(signUpResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mSignUpActivityView.postSignUpFailure(mContext.getString(R.string.network_failure));
            }
        });
    }

}
