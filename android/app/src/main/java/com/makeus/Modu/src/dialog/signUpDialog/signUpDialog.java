package com.makeus.Modu.src.dialog.signUpDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.makeus.Modu.R;

public class signUpDialog extends Dialog {

    private signUpListener mListener;
    private Context mContext;

    public signUpDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_sign_up);

        TextView mTvSignUpCancel = findViewById(R.id.tv_dialog_sign_up_cancel);
        TextView mTvSignUpOk = findViewById(R.id.tv_dialog_sign_up_ok);

        mTvSignUpCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tv_dialog_sign_up_cancel) {
                    mListener.onCancelClicked();
                }
            }
        });

        mTvSignUpOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tv_dialog_login_now) {
                    mListener.onOkClicked();
                }
            }
        });

    }

    public void setDialogListener(signUpListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onBackPressed() {

    }

}