package com.makeus.Modu.src.login;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.makeus.Modu.R;
import com.makeus.Modu.src.BaseActivity;

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
                this.finish();
                break;
            case R.id.tv_login_naver:
                this.finish();
                break;
            case R.id.tv_login_kakao:
                this.finish();
                break;

        }
    }

}
