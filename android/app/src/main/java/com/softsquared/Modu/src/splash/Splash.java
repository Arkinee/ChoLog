package com.softsquared.Modu.src.splash;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
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

import static com.softsquared.Modu.src.ApplicationClass.sSharedPreferences;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Splash extends BaseActivity implements SplashActivityView {

    Context mContext;
    SplashActivityView mSplashActivityView = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this;
        getFcmToken();
        getKeyHash(this);
        Handler hd = new Handler();
        hd.postDelayed(new splashHandler(), 1000); // 1초 후에 hd handler 실행

    }

    private void getFcmToken() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    Log.d("로그", "getInstanceId failed", task.getException());
                    return;
                }

                String token = task.getResult().getToken();
//                Log.d("로그", "fcm token : " + token);

                JSONObject body = new JSONObject();
                String uuid = sSharedPreferences.getString("uuid", "");

                if(uuid.equals("")) return;

//                Log.d("로그", "uuid: " + uuid);

                try {
                    body.put("uuid", uuid);
                    body.put("fcm", token);
                } catch (Exception e) {
                    return;
                }

                final SplashService postTokenService = new SplashService(mSplashActivityView, body, mContext);
                postTokenService.tryPostToken();


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

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }

    public void getKeyHash(final Context context) {

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.softsquared.Modu", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
