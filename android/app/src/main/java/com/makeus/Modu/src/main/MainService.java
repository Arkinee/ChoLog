package com.makeus.Modu.src.main;

import android.util.Log;

import androidx.annotation.NonNull;

import com.makeus.Modu.src.lookAround.models.LookListResponse;
import com.makeus.Modu.src.main.interfaces.MainActivityView;
import com.makeus.Modu.src.main.interfaces.MainRetrofitInterface;
import com.makeus.Modu.src.main.models.CurrencyResponse;
import com.makeus.Modu.src.main.models.ItemsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.Modu.src.ApplicationClass.getCurrencyRetrofit;
import static com.makeus.Modu.src.ApplicationClass.getRetrofit;

class MainService {

    private MainActivityView mMainActivityView;
    private String mCode = "FRX.KRWUSD";

    //환율 생성자
    MainService(final MainActivityView mMainActivityView) {
        this.mMainActivityView = mMainActivityView;
    }

    //환율 가져오기
    void getCurrency() {
        final MainRetrofitInterface mainRetrofitInterface = getCurrencyRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getCurrency(mCode).enqueue(new Callback<List<CurrencyResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<CurrencyResponse>> call, @NonNull Response<List<CurrencyResponse>> response) {

                final @NonNull List<CurrencyResponse> currencyResponses = response.body();
                if (currencyResponses == null) {
                    mMainActivityView.getCurrencyFailure("환율 응답 없음");
                    //Log.d(TAG, "응답 없음");
                    return;
                }
                double currency = currencyResponses.get(0).getBasePrice();
                mMainActivityView.getCurrencySuccess(currency);

            }

            @Override
            public void onFailure(Call<List<CurrencyResponse>> call, Throwable t) {
                mMainActivityView.getCurrencyFailure("서버 연결 실패");
            }
        });
    }

    void getItems() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getItems().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(@NonNull Call<ItemsResponse> call, @NonNull Response<ItemsResponse> response) {

                final @NonNull ItemsResponse itemsResponse = response.body();
                if (itemsResponse == null) {
                    mMainActivityView.getItemsFailure("아이템 응답 없음");
                    return;
                }
                mMainActivityView.getItemsSuccess(itemsResponse.getProducts());

            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mMainActivityView.getItemsFailure("서버 연결 실패");
            }
        });
    }

    void getLookList() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getLookList().enqueue(new Callback<LookListResponse>() {
            @Override
            public void onResponse(@NonNull Call<LookListResponse> call, @NonNull Response<LookListResponse> response) {

                final @NonNull LookListResponse lookListResponse = response.body();
                if (lookListResponse == null) {
                    mMainActivityView.getLookItemsFailure("아이템 응답 없음");
                    return;
                }
                mMainActivityView.getLookItemsSuccess(lookListResponse.getPopularList(), lookListResponse.getRecommendList(), lookListResponse.getOnlineList(), lookListResponse.getOfflineList());
            }

            @Override
            public void onFailure(Call<LookListResponse> call, Throwable t) {
                mMainActivityView.getLookItemsFailure("서버 연결 실패");
            }
        });
    }

}
