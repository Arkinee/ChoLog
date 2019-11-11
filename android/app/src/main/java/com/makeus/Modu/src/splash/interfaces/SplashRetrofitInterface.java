package com.makeus.Modu.src.splash.interfaces;


import com.makeus.Modu.src.splash.model.SplashResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SplashRetrofitInterface {

    //token 필요로 하는 api에 전송
    @POST("/user/token")
    Call<SplashResponse> postAutoLogin(@Body RequestBody params);

}
