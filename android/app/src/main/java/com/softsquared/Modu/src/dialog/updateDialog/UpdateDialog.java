package com.softsquared.Modu.src.dialog.updateDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.softsquared.Modu.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class UpdateDialog extends Dialog {

    private UpdateListener mListener;
    private Context mContext;
    private int mFlag;
    private Animation mAnimFadeOut;

    public UpdateDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_ready_for_update);

        mFlag = 1;
        final TextView mTvContent = findViewById(R.id.tv_dialog_update_content);
        mTvContent.setMovementMethod(new ScrollingMovementMethod());
        mAnimFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_left);

        final TextView mTvPositive = findViewById(R.id.tv_dialog_positive);
        TextView mTvNegative = findViewById(R.id.tv_dialog_negative);

        ImageView mIvClose = findViewById(R.id.iv_dialog_close);

        mTvPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tv_dialog_positive) {
                    if(mFlag == 1) {
                        mTvPositive.setText(R.string.tv_dialog_ready_for_update_ok);
                        mTvContent.setText(R.string.tv_dialog_ready_for_update_content_2);
                        mTvContent.startAnimation(mAnimFadeOut);
                        mTvPositive.setText(R.string.tv_dialog_go_survey_ok);
                        mFlag += 1;
                    }else{
                        mListener.onPositiveClicked();
                    }
                }
            }
        });

        mTvNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tv_dialog_negative) {
                    mListener.onNegativeClicked();
                }
            }
        });

        mIvClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.iv_dialog_close) {
                    mListener.onCloseClicked();
                }
            }
        });

    }

    public void setDialogListener(UpdateListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onBackPressed() {

    }

}