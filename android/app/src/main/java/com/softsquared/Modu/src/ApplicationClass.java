package com.softsquared.Modu.src;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaDrm;
import android.media.UnsupportedSchemeException;
import android.provider.Settings;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.softsquared.Modu.config.XAccessTokenInterceptor;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationClass extends Application {
    public static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=uft-8");
    public static MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");

    //서버 주소
    public static String BASE_URL = "http://52.79.202.195";
    //currency 주소
    public static String CURRENCY_URL = "http://quotation-api-cdn.dunamu.com";
    //공용 sharedpreference
    public static SharedPreferences sSharedPreferences = null;

    public static DecimalFormat myFormatter = new DecimalFormat("###,###");
    // SharedPreferences 키 값
    public static String TAG = "Modu";
    public static String SCHEDULE_TAG = "DAILY_WORK";

    // JWT Token 값
    public static final String X_ACCESS_TOKEN = "X-ACCESS-TOKEN";

    //날짜 형식
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    public static SimpleDateFormat HOME_MONTH = new SimpleDateFormat("MM", Locale.KOREA);
    public static SimpleDateFormat HOME_DAY = new SimpleDateFormat("dd", Locale.KOREA);
    // Retrofit 인스턴스
    public static Retrofit retrofit;
    public static Retrofit currencyRetrofit;

    public static String DEVICE_UUID;

    @Override
    public void onCreate() {
        super.onCreate();

        if (sSharedPreferences == null) {
            sSharedPreferences = getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

    }

    public static String addDate(String dt, int d) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        Date date = format.parse(dt);
        calendar.add(Calendar.DATE, d);
        return format.format(calendar.getTime());

    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .addNetworkInterceptor(new XAccessTokenInterceptor()) // JWT 자동 헤더 전송
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static Retrofit getCurrencyRetrofit() {
        if (currencyRetrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .addNetworkInterceptor(new XAccessTokenInterceptor()) // JWT 자동 헤더 전송
                    .build();

            currencyRetrofit = new Retrofit.Builder()
                    .baseUrl(CURRENCY_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return currencyRetrofit;
    }


}