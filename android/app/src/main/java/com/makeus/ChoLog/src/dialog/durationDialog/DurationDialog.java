package com.makeus.ChoLog.src.dialog.durationDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.makeus.ChoLog.R;

public class DurationDialog extends Dialog {

    private DurationListener mListener;
    private Context mContext;

    public DurationDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_duration);

        TextView TvDurationComplete;
        final NumberPicker PickerNumber;
        final NumberPicker PickerPer;

        TvDurationComplete = findViewById(R.id.tv_dialog_duration_complete);
        PickerNumber = findViewById(R.id.picker_duration_number);
        PickerPer = findViewById(R.id.picker_duration_per);

        //Number Picker Value 설정
        PickerNumber.setMinValue(1);
        PickerNumber.setMaxValue(30);

        String[] arrayString = mContext.getResources().getStringArray(R.array.day_week_month_year);
        PickerPer.setMinValue(0);
        PickerPer.setMaxValue(arrayString.length - 1);
//        mPickerPer.setFormatter(new NumberPicker.Formatter() {
//            @Override
//            public String format(int i) {
//                return arrayString[i];
//            }
//        });
        PickerPer.setDisplayedValues(arrayString);

        TvDurationComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tv_dialog_duration_complete) {
                    mListener.onDurationComplete(PickerNumber.getValue(), PickerPer.getValue());
                }
            }
        });

    }

    public void setDialogListener(DurationListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onBackPressed() {

    }

}