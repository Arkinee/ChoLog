package com.makeus.ChoLog.src.dialog.lastDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.makeus.ChoLog.R;

public class LastDialog extends Dialog {

    private lastListener mListener;
    private TextView mTvLastComplete;
    private Context mContext;

    public LastDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_last);

        mTvLastComplete = findViewById(R.id.tv_dialog_last_complete);

        mTvLastComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tv_dialog_last_complete) {
                    mListener.onComplete();
                }
            }
        });

    }

    public void setDialogListener(lastListener listener) {
        this.mListener = listener;
    }

}