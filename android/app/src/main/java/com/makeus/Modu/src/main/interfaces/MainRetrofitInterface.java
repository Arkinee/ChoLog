package com.makeus.Modu.src.main.interfaces;

import com.makeus.Modu.src.lookAround.models.LookListResponse;
import com.makeus.Modu.src.main.models.CurrencyResponse;
import com.makeus.Modu.src.main.models.ItemsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainRetrofitInterface {

    @GET("/v1/forex/recent")
    Call<List<CurrencyResponse>> getCurrency(@Query("codes") String codes);

    @GET("/everything")
    Call<ItemsResponse> getItems();

    @GET("/productList")
    Call<LookListResponse> getLookList();

}
