package com.softsquared.Modu.src.main;

import android.content.Context;

import androidx.annotation.NonNull;

import com.softsquared.Modu.R;
import com.softsquared.Modu.src.lookAround.models.LookListResponse;
import com.softsquared.Modu.src.main.interfaces.MainActivityView;
import com.softsquared.Modu.src.main.interfaces.MainRetrofitInterface;
import com.softsquared.Modu.src.main.models.CurrencyResponse;
import com.softsquared.Modu.src.main.models.ItemsResponse;
import com.softsquared.Modu.src.main.models.UpLoadResponse;

import org.json.JSONObject;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.Modu.src.ApplicationClass.MEDIA_TYPE_JSON;
import static com.softsquared.Modu.src.ApplicationClass.getCurrencyRetrofit;
import static com.softsquared.Modu.src.ApplicationClass.getRetrofit;

class MainService {

    private MainActivityView mMainActivityView;
    private JSONObject mParams;
    private Context mContext;

    //환율 생성자
    MainService(MainActivityView mMainActivityView) {
        this.mMainActivityView = mMainActivityView;
    }

    MainService(MainActivityView mainActivityView, Context context, JSONObject param){
        this.mMainActivityView = mainActivityView;
        this.mContext = context;
        this.mParams = param;
    }

    //환율 가져오기
    void getCurrency() {

        String code = "FRX.KRWUSD";

        final MainRetrofitInterface mainRetrofitInterface = getCurrencyRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getCurrency(code).enqueue(new Callback<List<CurrencyResponse>>() {
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

    //아이템 가져오기
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

    //둘러보기 아이템 가져오기
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

    //아이템 업로드
    void tryPostUpload() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.postUpload(RequestBody.create(MEDIA_TYPE_JSON, mParams.toString())).enqueue(new Callback<UpLoadResponse>() {
            @Override
            public void onResponse(Call<UpLoadResponse> call, Response<UpLoadResponse> response) {

                final UpLoadResponse upLoadResponse = response.body();
                if (upLoadResponse == null) {
                    mMainActivityView.postUploadFailure(mContext.getString(R.string.network_failure));
                    return;
                }

                mMainActivityView.postUploadSuccess(upLoadResponse.getMessage());
            }

            @Override
            public void onFailure(Call<UpLoadResponse> call, Throwable t) {
                mMainActivityView.postUploadFailure(mContext.getString(R.string.network_failure));
            }
        });
    }


}
