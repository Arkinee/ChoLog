package com.softsquared.Modu.src.signUp.interfaces;


import com.softsquared.Modu.src.signUp.models.SignUpResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpRetrofitInterface {

    //token 필요로 하는 api에 전송
    @POST("/user/token")
    Call<SignUpResponse> postAutoLogin(@Body RequestBody params);

}
