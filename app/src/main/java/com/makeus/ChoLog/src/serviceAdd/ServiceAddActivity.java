package com.makeus.ChoLog.src.serviceAdd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.BaseActivity;
import com.makeus.ChoLog.src.main.MainActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add);
        this.initialize();

    }

    void initialize(){
        mEdtProduct = findViewById(R.id.edt_service_add_product);
        mEdtPrice = findViewById(R.id.edt_service_add_price);
        mEdtLast = findViewById(R.id.edt_service_add_last);
        mEdtWhile = findViewById(R.id.edt_service_add_while);
        mEdtAlarm = findViewById(R.id.edt_service_add_alarm);
        mEdtExtra = findViewById(R.id.edt_service_add_extra);
        mEdtChange = findViewById(R.id.edt_service_add_change_plan);
        mEdtCancel = findViewById(R.id.edt_service_add_cancel_plan);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_service_add_back:
                this.finish();
                break;
        }
    }

}
