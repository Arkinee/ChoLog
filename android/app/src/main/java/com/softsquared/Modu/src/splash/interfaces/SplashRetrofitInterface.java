package com.softsquared.Modu.src.splash.interfaces;


import com.softsquared.Modu.src.splash.model.SplashResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SplashRetrofitInterface {

    //uuid와 fcm token 서버에 보내기
    @POST("/user")
    Call<SplashResponse> postToken(@Body RequestBody params);

}
