package com.makeus.ChoLogBin.src.serviceAdd.interfaces;

import com.makeus.ChoLogBin.src.serviceAdd.models.CurrencyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceAddRetrofitInterface {

    @GET("/v1/forex/recent")
    Call<List<CurrencyResponse>>getCurrency(@Query("codes") String codes);

}
