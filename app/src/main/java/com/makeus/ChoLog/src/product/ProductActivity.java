package com.makeus.ChoLog.src.product;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.BaseActivity;

public class ProductActivity extends BaseActivity {

    private EditText mEdtService;
    private EditText mEdtMemberShip;

    private TextView mTvServiceUnder;
    private TextView mTvMemberShipUnder;
    private TextView mTvProductComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        this.initialize();

    }

    void initialize(){
        mEdtService = findViewById(R.id.edt_product_service_name);
        mEdtMemberShip = findViewById(R.id.edt_product_membership);
        mTvServiceUnder = findViewById(R.id.tv_product_service_name_under);
        mTvMemberShipUnder = findViewById(R.id.tv_product_membership_under);
        mTvProductComplete = findViewById(R.id.tv_product_complete);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_product_back:
                this.finish();
                break;
            case R.id.tv_product_complete:
                this.finish();
                break;
        }
    }

}
