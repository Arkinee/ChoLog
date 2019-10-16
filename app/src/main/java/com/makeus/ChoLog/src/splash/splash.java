package com.makeus.ChoLog.src.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import org.json.JSONObject;

import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.main.MainActivity;
import com.makeus.ChoLog.src.splash.interfaces.SplashActivityView;

public class splash extends Activity implements SplashActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 1500); // 1초 후에 hd handler 실행

    }

    private void postAutoLogin() {
        JSONObject params = new JSONObject();
//        final splashService splashService = new splashService(this, params, this);
//        splashService.postAutoLogin();
    }



    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }

    @Override
    public void postAutoLoginSuccess(int code) {

    }

    @Override
    public void postAutoLoginFailure(String message) {

    }

    private class splashhandler implements Runnable {
        public void run() {
                startActivity(new Intent(getApplication(), MainActivity.class)); //로딩이 끝난 후, ChoiceFunction 이동
                splash.this.finish(); // 로딩페이지 Activity stack에서 제거
        }
    }
}
