package com.makeus.ChoLog.src.dialog.loginDialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.makeus.ChoLog.R;

public class LoginDialog extends Dialog {

    private loginListener mListener;
    private TextView mTvLoginLater;
    private TextView mTvLoginNow;
    private Context mContext;

    public LoginDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_login);

        mTvLoginLater = findViewById(R.id.tv_dialog_login_later);
        mTvLoginNow = findViewById(R.id.tv_dialog_login_now);

        mTvLoginLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tv_dialog_login_later) {
                    mListener.onLaterClicked();
                }
            }
        });

        mTvLoginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tv_dialog_login_now) {
                    mListener.onNowClicked();
                }
            }
        });

    }

    public void setDialogListener(loginListener listener) {
        this.mListener = listener;
    }

}