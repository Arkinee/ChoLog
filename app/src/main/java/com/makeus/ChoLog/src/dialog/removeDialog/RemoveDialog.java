package com.makeus.ChoLog.src.dialog.removeDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.makeus.ChoLog.R;

public class RemoveDialog extends Dialog {

    private removeListener mListener;
    private TextView mTvCancel;
    private TextView mTvRemove;
    private Context mContext;

    public RemoveDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_remove);

        mTvCancel = findViewById(R.id.tv_dialog_remove_cancel);
        mTvRemove = findViewById(R.id.tv_dialog_remove_remove);

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tv_dialog_remove_cancel) {
                    mListener.onCancelClicked();
                }
            }
        });

        mTvRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tv_dialog_remove_remove) {
                    mListener.onRemoveClicked();
                }
            }
        });

    }

    public void setDialogListener(removeListener listener) {
        this.mListener = listener;
    }

}