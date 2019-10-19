package com.makeus.ChoLog.src.serviceAdd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.BaseActivity;
import com.makeus.ChoLog.src.main.MainActivity;
import com.makeus.ChoLog.src.product.ProductActivity;
import com.makeus.ChoLog.src.splash.interfaces.SplashActivityView;

import org.json.JSONObject;

public class ServiceAddActivity extends BaseActivity {

    private EditText mEdtProduct;
    private EditText mEdtPrice;
    private EditText mEdtLast;
    private EditText mEdtWhile;
    private EditText mEdtAlarm;
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

    private LinearLayout mLinearRemove;
//    private TextView mTvAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add);
        this.initialize();

    }

    void initialize() {
        mEdtProduct = findViewById(R.id.edt_service_add_product);
        mEdtPrice = findViewById(R.id.edt_service_add_price);
        mEdtLast = findViewById(R.id.edt_service_add_last);
        mEdtWhile = findViewById(R.id.edt_service_add_while);
        mEdtAlarm = findViewById(R.id.edt_service_add_alarm);
        mEdtExtra = findViewById(R.id.edt_service_add_extra);
        mEdtChange = findViewById(R.id.edt_service_add_change_plan);
        mEdtCancel = findViewById(R.id.edt_service_add_cancel_plan);

        mTvProductUnder = findViewById(R.id.tv_service_product_under);
        mTvPriceUnder = findViewById(R.id.tv_service_price_under);
        mTvLastUnder = findViewById(R.id.tv_service_last_under);
        mTvWhileUnder = findViewById(R.id.tv_service_while_under);
        mTvAlarmUnder = findViewById(R.id.tv_service_alarm_under);
        mTvExtraUnder = findViewById(R.id.tv_service_extra_under);
        mTvChangeUnder = findViewById(R.id.tv_service_change_plan_under);
        mTvCancelUnder = findViewById(R.id.tv_service_cancel_plan_under);

        mLinearRemove = findViewById(R.id.linear_service_add_remove);
//        mTvAdd = findViewById(R.id.tv_service_add_btn);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_service_add_back:
                this.finish();
                break;
            case R.id.tv_service_add_remove:
                mLinearRemove.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_service_add_btn_cancel:
                mLinearRemove.setVisibility(View.GONE);
                break;
            case R.id.tv_service_add_btn_remove:
                mLinearRemove.setVisibility(View.GONE);
                this.finish();
                break;
            case R.id.tv_service_search:
                Intent intent = new Intent(this, ProductActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_service_add_btn:
                this.finish();
                break;
        }
    }

}
