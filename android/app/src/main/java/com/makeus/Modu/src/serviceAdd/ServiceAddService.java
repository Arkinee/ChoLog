package com.makeus.Modu.src.serviceAdd;

import android.util.Log;

import androidx.annotation.NonNull;

import com.makeus.Modu.src.serviceAdd.interfaces.ServiceAddActivityView;
import com.makeus.Modu.src.serviceAdd.interfaces.ServiceAddRetrofitInterface;
import com.makeus.Modu.src.serviceAdd.models.CurrencyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.Modu.src.ApplicationClass.getCurrencyRetrofit;

class ServiceAddService {

    private ServiceAddActivityView mServiceAddActivityView;
    private String mCode = "FRX.KRWUSD";

    //환율 생성자
    ServiceAddService(final ServiceAddActivityView mServiceAddActivityView) {
        this.mServiceAddActivityView = mServiceAddActivityView;
    }

//    //환율 가져오기
//    void getCurrency() {
//        final ServiceAddRetrofitInterface serviceAddRetrofitInterface = getCurrencyRetrofit().create(ServiceAddRetrofitInterface.class);
//        serviceAddRetrofitInterface.getCurrency(mCode).enqueue(new Callback<List<CurrencyResponse>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<CurrencyResponse>> call, @NonNull Response<List<CurrencyResponse>> response) {
//
//                final @NonNull List<CurrencyResponse> currencyResponses = response.body();
//                if (currencyResponses == null) {
//                    mServiceAddActivityView.getCurrencyFailure("응답 없음");
//                    //Log.d(TAG, "응답 없음");
//                    return;
//                }
//                double currency = currencyResponses.get(0).getBasePrice();
//                Log.d("로그", "response basePrice: " + currency);
//                //담기 성공
//                mServiceAddActivityView.getCurrencySuccess(currency);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<CurrencyResponse>> call, Throwable t) {
//                mServiceAddActivityView.getCurrencyFailure("서버 연결 실패");
//                Log.d("로그", "Failure: " + t.getMessage());
//            }
//        });
//    }

}
