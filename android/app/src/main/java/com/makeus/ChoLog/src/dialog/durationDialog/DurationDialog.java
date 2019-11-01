package com.makeus.ChoLog.src.dialog.durationDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.dialog.lastDialog.lastListener;

public class DurationDialog extends Dialog {

    private durationListener mListener;
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
        NumberPicker PickerNumber;
        NumberPicker PickerPer;

        TvDurationComplete = findViewById(R.id.tv_dialog_duration_complete);
        PickerNumber = findViewById(R.id.picker_duration_number);
        PickerPer = findViewById(R.id.picker_duration_per);

        TvDurationComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tv_dialog_duration_complete) {
                    mListener.onComplete();
                }
            }
        });

        //Number Picker Value 설정
        PickerNumber.setMinValue(1);
        PickerNumber.setMaxValue(30);

        String[] arrayString = new String[]{"일", "주", "달", "년"};
        PickerPer.setMinValue(0);
        PickerPer.setMaxValue(arrayString.length - 1);
//        mPickerPer.setFormatter(new NumberPicker.Formatter() {
//            @Override
//            public String format(int i) {
//                return arrayString[i];
//            }
//        });
        PickerPer.setDisplayedValues(arrayString);


    }

    public void setDialogListener(durationListener listener) {
        this.mListener = listener;
    }

}