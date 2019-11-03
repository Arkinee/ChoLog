package com.makeus.ChoLog.src.imageAdd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.BaseActivity;

public class ImageAddActivity extends BaseActivity {

    private LinearLayout mLinearWon;
    private LinearLayout mLinearDollar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        this.initialize();

    }

    void initialize() {



    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_image_add_back:
                this.finish();
                break;
        }
    }

}
