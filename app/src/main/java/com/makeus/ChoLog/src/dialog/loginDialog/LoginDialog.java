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

public class LoginDialog extends AlertDialog implements View.OnClickListener{

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
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.dialog_login);

        mTvLoginLater = findViewById(R.id.tv_dialog_login_later);
        mTvLoginNow = findViewById(R.id.tv_dialog_login_now);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dialog_login_later:
                dismiss();
                break;
            case R.id.tv_dialog_login_now:
                //login activity로 이동
                dismiss();
                break;
        }
    }

    public void setDialogListener(loginListener listener) {
        this.mListener = listener;
    }
}