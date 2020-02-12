package com.softsquared.Modu.src.dialog.durationDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.softsquared.Modu.R;

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
        final String[] durationArray = mContext.getResources().getStringArray(R.array.duration_num);
        String[] durationPer = mContext.getResources().getStringArray(R.array.day_week_month_year);
        PickerNumber.setMinValue(0);
        PickerNumber.setMaxValue(durationArray.length - 1);
        PickerPer.setMinValue(0);
        PickerPer.setMaxValue(durationPer.length - 1);
        PickerPer.setValue(2);  //default 값을 '달'로 세팅
//        mPickerPer.setFormatter(new NumberPicker.Formatter() {
//            @Override
//            public String format(int i) {
//                return arrayString[i];
//            }
//        });
        PickerNumber.setDisplayedValues(durationArray);
        PickerPer.setDisplayedValues(durationPer);

        TvDurationComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tv_dialog_duration_complete) {
                    mListener.onDurationComplete(Integer.valueOf(durationArray[PickerNumber.getValue()]), PickerPer.getValue());
                }
            }
        });

    }

    public void setDialogListener(DurationListener listener) {
        this.mListener = listener;
    }

}