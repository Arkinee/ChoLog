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
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
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
    private AppUpdateManager mUpdateManager;
    private AppUpdateInfo mUpdateInfo;
    private int REQUEST_CODE = 366;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this;
        getFcmToken();

        mUpdateManager = AppUpdateManagerFactory.create(getApplicationContext());
        Task<AppUpdateInfo> appUpdateInfoTask = mUpdateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(mUpdateInfo -> {
            // UpdateAvailability.UPDATE_AVAILABLE == 2 이면 앱 true
            if (mUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && mUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                // 허용된 타입의 앱 업데이트이면 실행 (AppUpdateType.IMMEDIATE || AppUpdateType.FLEXIBLE)
                // 업데이트가 가능하고, 상위 버전 코드의 앱이 존재하면 업데이트를 실행한다.
                requestUpdate(mUpdateInfo);
            }else{
                Handler hd = new Handler();
                hd.postDelayed(new splashHandler(), 750); // 1초 후에 hd handler 실행
            }
        });

//        getKeyHash(this);
//        Handler hd = new Handler();
//        hd.postDelayed(new splashHandler(), 750); // 1초 후에 hd handler 실행

    }

    private void requestUpdate(AppUpdateInfo appUpdateInfo) {
        try {
            mUpdateManager.startUpdateFlowForResult(
                    // 'getAppUpdateInfo()' 에 의해 리턴된 인텐트
                    appUpdateInfo,
                    // 'AppUpdateType.FLEXIBLE': 사용자에게 업데이트 여부를 물은 후 업데이트 실행 가능
                    // 'AppUpdateType.IMMEDIATE': 사용자가 수락해야만 하는 업데이트 창을 보여줌
                    AppUpdateType.IMMEDIATE,
                    // 현재 업데이트 요청을 만든 액티비티, 여기선 MainActivity.
                    this,
                    // onActivityResult 에서 사용될 REQUEST_CODE.
                    REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getFcmToken() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    Log.d("로그", "getInstanceId failed", task.getException());
                    return;
                }

                String token = task.getResult().getToken();
//                Log.d("로그", "fcm token : " + token);

                JSONObject body = new JSONObject();
                String uuid = sSharedPreferences.getString("uuid", "");

                if (uuid.equals("")) return;

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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            showCustomToast("MY_REQUEST_CODE");

            // 업데이트가 성공적으로 끝나지 않은 경우
            if (resultCode != RESULT_OK) {
                Log.d("로그", "Update flow failed! Result code: " + resultCode);
                // 업데이트가 취소되거나 실패하면 업데이트를 다시 요청할 수 있다.,
                // 업데이트 타입을 선택한다 (IMMEDIATE || FLEXIBLE).
                Task<AppUpdateInfo> appUpdateInfoTask = mUpdateManager.getAppUpdateInfo();

                appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
                    if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                            // flexible한 업데이트를 위해서는 AppUpdateType.FLEXIBLE을 사용한다.
                            && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                        // 업데이트를 다시 요청한다.
                        requestUpdate(appUpdateInfo);
                    }
                });
            }
        }
    }

}
