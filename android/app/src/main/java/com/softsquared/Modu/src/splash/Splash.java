package com.softsquared.Modu.src.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.softsquared.Modu.R;
import com.softsquared.Modu.src.ApplicationClass;
import com.softsquared.Modu.src.BaseActivity;
import com.softsquared.Modu.src.main.MainActivity;
import com.softsquared.Modu.src.splash.interfaces.SplashActivityView;

import org.json.JSONObject;

import static com.softsquared.Modu.src.ApplicationClass.sSharedPreferences;

public class Splash extends BaseActivity implements SplashActivityView {

    Context mContext;
    SplashActivityView mSplashActivityView = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this;
        getFcmToken();
        Handler hd = new Handler();
        hd.postDelayed(new splashHandler(), 1000); // 1초 후에 hd handler 실행

    }

    private void getFcmToken(){
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(!task.isSuccessful()){
                    Log.d("로그", "getInstanceId failed", task.getException());
                    return;
                }

                String token = task.getResult().getToken();
//                Log.d("로그", "fcm token : " + token);
                //이전 fcm 토큰이 없거나 현재와 다를 때 로컬에 저장 후 서버에 날림
                if(!sSharedPreferences.getString("fcmToken", "").equals(token)){
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    editor.putString("fcmToken", token);
                    editor.apply();

                    JSONObject body = new JSONObject();
                    String uuid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

                    try {
                        body.put("uuid", uuid);
                        body.put("fcm", sSharedPreferences.getString("fcmToken", ""));
                    }catch (Exception e){
                        return;
                    }

                    final SplashService postTokenService = new SplashService(mSplashActivityView, body, mContext);
                    postTokenService.tryPostToken();

                }
            }
        });
    }

    @Override
    public void postTokenSuccess(String message) {
        Log.d(ApplicationClass.TAG, message);
    }

    @Override
    public void postTokenFailure(String message) {
        Log.d(ApplicationClass.TAG, message);
    }

    private class splashHandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class)); //로딩이 끝난 후, ChoiceFunction 이동
            Splash.this.finish(); // 로딩페이지 Activity stack에서 제거
        }
    }

//    private void postAutoLogin() {
//        JSONObject params = new JSONObject();
//        final splashService splashService = new splashService(this, params, this);
//        splashService.postAutoLogin();
//    }

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }



}
