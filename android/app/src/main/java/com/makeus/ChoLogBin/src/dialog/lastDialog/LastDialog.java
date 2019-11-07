package com.makeus.ChoLogBin.src.dialog.lastDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;

import com.makeus.ChoLogBin.R;

import java.util.Calendar;
import java.util.Date;

public class LastDialog extends Dialog {

    private LastListener mListener;
    private TextView mTvLastComplete;
    private DatePicker mDatePickerLast;
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
        mDatePickerLast = findViewById(R.id.picker_last);

        final Calendar cal = Calendar.getInstance();
        mDatePickerLast.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);

        Calendar minDate = cal;
        minDate.add(Calendar.YEAR, -3);
//        minDate.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 3);
//        minDate.set(Calendar.MONTH, cal.get(Calendar.MONTH));
//        minDate.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));

        mDatePickerLast.setMinDate(minDate.getTimeInMillis());
        mDatePickerLast.setMaxDate(new Date().getTime());

        mTvLastComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tv_dialog_last_complete) {
                    mListener.onLastComplete(mDatePickerLast.getYear(), mDatePickerLast.getMonth(), mDatePickerLast.getDayOfMonth());
                }
            }
        });

    }

    public void setDialogListener(LastListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onBackPressed() {

    }

}