package com.makeus.ChoLog.src.serviceAdd;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.BaseActivity;
import com.makeus.ChoLog.src.dialog.removeDialog.RemoveDialog;
import com.makeus.ChoLog.src.dialog.removeDialog.removeListener;
import com.makeus.ChoLog.src.main.MainActivity;
import com.makeus.ChoLog.src.product.ProductActivity;
import com.makeus.ChoLog.src.splash.interfaces.SplashActivityView;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class ServiceAddActivity extends BaseActivity {

    private EditText mEdtProduct;
    private EditText mEdtPrice;
    private TextView mTvLast;
    private TextView mTvDuration;
    private TextView mTvAlarm;
    private EditText mEdtExtra;
    private EditText mEdtChange;
    private EditText mEdtCancel;

    private TextView mTvProductUnder;
    private TextView mTvPriceUnder;
    private TextView mTvLastUnder;
    private TextView mTvWhileUnder;
    private TextView mTvAlarmUnder;
    private TextView mTvExtraUnder;
    private TextView mTvChangeUnder;
    private TextView mTvCancelUnder;

//    private LinearLayout mLinearRemove;
//    private TextView mTvAdd;

    private RemoveDialog mRemoveDialog;
    private WindowManager.LayoutParams mWm;

    private int mWidth;
    private int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add);
        this.initialize();
        this.setEdtListener();
        this.setmRemoveDialog();

    }

    void initialize() {
        mEdtProduct = findViewById(R.id.edt_service_add_product);
        mEdtPrice = findViewById(R.id.edt_service_add_price);
        mTvLast = findViewById(R.id.tv_service_add_last);
        mTvDuration = findViewById(R.id.tv_service_add_duration);
        mTvAlarm = findViewById(R.id.tv_service_add_alarm);
        mEdtExtra = findViewById(R.id.edt_service_add_extra);
        mEdtChange = findViewById(R.id.edt_service_add_change_plan);
        mEdtCancel = findViewById(R.id.edt_service_add_cancel_plan);

        mTvProductUnder = findViewById(R.id.tv_service_product_under);
        mTvPriceUnder = findViewById(R.id.tv_service_price_under);
        mTvLastUnder = findViewById(R.id.tv_service_last_under);
        mTvWhileUnder = findViewById(R.id.tv_service_duration_under);
        mTvAlarmUnder = findViewById(R.id.tv_service_alarm_under);
        mTvExtraUnder = findViewById(R.id.tv_service_extra_under);
        mTvChangeUnder = findViewById(R.id.tv_service_change_plan_under);
        mTvCancelUnder = findViewById(R.id.tv_service_cancel_plan_under);

//        mLinearRemove = findViewById(R.id.linear_service_add_remove);

        TextView tvEssential = findViewById(R.id.tv_service_add_essential);
        tvEssential.requestFocus();

    }

    public void setEdtListener() {

        mEdtProduct.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mTvProductUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
                } else {
                    mTvProductUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
                }
            }
        });

        mEdtPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mTvPriceUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
                } else {
                    mTvPriceUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
                }
            }
        });

        mEdtExtra.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mTvExtraUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
                } else {
                    mTvExtraUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
                }
            }
        });

        mEdtChange.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mTvChangeUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
                } else {
                    mTvChangeUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
                }
            }
        });

        mEdtCancel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mTvCancelUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
                } else {
                    mTvCancelUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
                }
            }
        });


    }

    public void setmRemoveDialog(){

        mRemoveDialog = new RemoveDialog(this);

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        mWidth = dm.widthPixels;
        mHeight = dm.heightPixels;

        mWm = new WindowManager.LayoutParams();
        mWm.copyFrom(mRemoveDialog.getWindow().getAttributes());

        mWm.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        mWm.dimAmount = 0.8f;

        mWm.height = mHeight;
        mWm.width = mWidth;

        mRemoveDialog.setCancelable(true);
        mRemoveDialog.setDialogListener(new removeListener() {
            @Override
            public void onCancelClicked() {
                mRemoveDialog.dismiss();
            }

            @Override
            public void onRemoveClicked() {
                mRemoveDialog.dismiss();
            }
        });

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_service_add_back:
                this.finish();
                break;
            case R.id.tv_service_add_remove:
                mRemoveDialog.show();
                Window window = mRemoveDialog.getWindow();
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setAttributes(mWm);

//                Display display = getWindowManager().getDefaultDisplay();
//                Point size = new Point();
//                display.getSize(size);
//                Window window2 = mRemoveDialog.getWindow();
//                window2.setLayout(x, y);

                break;
//            case R.id.tv_service_add_btn_cancel:
//                mLinearRemove.setVisibility(View.GONE);
//                break;
//            case R.id.tv_service_add_btn_remove:
//                mLinearRemove.setVisibility(View.GONE);
//                this.finish();
//                break;
            case R.id.tv_service_search:
                Intent intent = new Intent(this, ProductActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_service_add_btn:
                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", mEdtProduct.getText().toString());
                resultIntent.putExtra("price", mEdtPrice.getText().toString());
                resultIntent.putExtra("last", mTvLast.getText().toString());
                resultIntent.putExtra("duration", mTvDuration.getText().toString());
                resultIntent.putExtra("alarm", mTvAlarm.getText().toString());
                resultIntent.putExtra("change", mEdtChange.getText().toString());
                resultIntent.putExtra("cancel", mEdtCancel.getText().toString());
                resultIntent.putExtra("extra", mEdtExtra.getText().toString());
                setResult(RESULT_OK, resultIntent);
                finish();
                break;
        }
    }

}
