package com.softsquared.Modu.src.signUp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.softsquared.Modu.R;
import com.softsquared.Modu.src.BaseActivity;
import com.softsquared.Modu.src.signUp.interfaces.SignUpActivityView;

public class signUpActivity extends BaseActivity implements SignUpActivityView {

    private EditText mEdtSignUpName;
    private EditText mEdtSignUpId;
    private EditText mEdtSignUpPassword;
    private EditText mEdtSignUpPasswordVerify;

    private TextView mTvSignUpNameUnder;
    private TextView mTvSignUpIdUnder;
    private TextView mTvSignUpPasswordUnder;
    private TextView mTvSignUpPasswordVerifyUnder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sign_up);
        this.initialize();
        this.setListener();

    }

    void initialize() {

        mEdtSignUpName = findViewById(R.id.edt_sign_up_name);
        mEdtSignUpId = findViewById(R.id.edt_sign_up_id);
        mEdtSignUpPassword = findViewById(R.id.edt_sign_up_password);
        mEdtSignUpPasswordVerify = findViewById(R.id.edt_sign_up_password_verify);

        mTvSignUpNameUnder = findViewById(R.id.tv_sign_up_name_under);
        mTvSignUpIdUnder = findViewById(R.id.tv_sign_up_id_under);
        mTvSignUpPasswordUnder = findViewById(R.id.tv_sign_up_password_under);
        mTvSignUpPasswordVerifyUnder = findViewById(R.id.tv_sign_up_password_verify_under);

    }


    void setListener() {

        mEdtSignUpName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mTvSignUpNameUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
                } else {
                    mTvSignUpNameUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
                }
            }
        });

        mEdtSignUpId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mTvSignUpIdUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
                } else {
                    mTvSignUpIdUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
                }
            }
        });

        mEdtSignUpPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mTvSignUpPasswordUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
                } else {
                    mTvSignUpPasswordUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
                }
            }
        });

        mEdtSignUpPasswordVerify.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mTvSignUpPasswordVerifyUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
                } else {
                    mTvSignUpPasswordVerifyUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
                }
            }
        });

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sign_up_btn:
                this.finish();
                break;
            case R.id.iv_sign_up_close:
                this.finish();
                break;
        }
    }

    @Override
    public void postSignUpSuccess(String message) {

    }

    @Override
    public void postSignUpFailure(String message) {

    }
}
