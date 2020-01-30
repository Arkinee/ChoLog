package com.softsquared.Modu.src.login;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.softsquared.Modu.R;
import com.softsquared.Modu.src.BaseActivity;

public class LoginActivity extends BaseActivity {

    private TextView mTvLoginNaver;
    private TextView mTvLoginKakao;
    private TextView mTvLoginGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.initialize();

    }

    void initialize() {

        mTvLoginNaver = findViewById(R.id.tv_login_naver);
        mTvLoginKakao = findViewById(R.id.tv_login_kakao);
        mTvLoginGoogle = findViewById(R.id.tv_login_google);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
            case R.id.tv_login_naver:
            case R.id.tv_login_kakao:
            case R.id.tv_login_google:
                this.finish();
                showCustomToast("추후 업데이트 예정입니다.");
                break;

        }
    }

}
