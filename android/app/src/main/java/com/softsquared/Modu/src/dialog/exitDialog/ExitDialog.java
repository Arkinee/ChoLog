package com.softsquared.Modu.src.dialog.exitDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.softsquared.Modu.R;

public class ExitDialog extends Dialog {

    private exitListener mListener;
    private Context mContext;

    public ExitDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_exit);

        TextView mTvExitPositive = findViewById(R.id.tv_dialog_exit_positive);
        TextView mTvExitNegative = findViewById(R.id.tv_dialog_exit_negative);

        mTvExitPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tv_dialog_exit_positive) {
                    mListener.onExitPositiveClicked();
                }
            }
        });

        mTvExitNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tv_dialog_exit_negative) {
                    mListener.onExitNegativeClicked();
                }
            }
        });

    }

    public void setDialogListener(exitListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onBackPressed() {

    }

}