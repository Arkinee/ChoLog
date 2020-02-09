package com.softsquared.Modu.src.main.interfaces;

import com.softsquared.Modu.src.lookAround.models.LookListResponse;
import com.softsquared.Modu.src.main.models.CurrencyResponse;
import com.softsquared.Modu.src.main.models.ItemsResponse;
import com.softsquared.Modu.src.main.models.UpLoadResponse;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MainRetrofitInterface {

    @GET("/v1/forex/recent")
    Call<List<CurrencyResponse>> getCurrency(@Query("codes") String codes);

    @GET("/everything")
    Call<ItemsResponse> getItems();

    @GET("/productList")
    Call<LookListResponse> getLookList();

    @POST("/upload")
    Call<UpLoadResponse> postUpload(@Body RequestBody params);
}
