package com.makeus.ChoLog.src.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.BaseActivity;

import static com.makeus.ChoLog.src.ApplicationClass.myFormatter;

public class DetailActivity extends BaseActivity {

    private ImageView mIvDetailImage;
    private TextView mTvServiceName;
    private TextView mTvServicePrice;
    private TextView mTvServiceDuration;
    private TextView mTvServiceCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.initialize();

    }

    void initialize() {

        mIvDetailImage = findViewById(R.id.iv_detail_image);
        mTvServiceCategory = findViewById(R.id.tv_detail_category);
        mTvServiceName = findViewById(R.id.tv_detail_service_name);
        mTvServicePrice = findViewById(R.id.tv_detail_service_fee);
        mTvServiceDuration = findViewById(R.id.tv_detail_service_duration);

        Intent intent = getIntent();
        Glide.with(this).load(intent.getExtras().getString("url")).placeholder(R.drawable.ic_adobe_cloud).override(200, 200).into(mIvDetailImage);
        mTvServiceName.setText(intent.getExtras().getString("name"));
        mTvServicePrice.setText(myFormatter.format(intent.getExtras().getInt("price")).concat("원"));
        int month = intent.getExtras().getInt("duration") / 30;
        mTvServiceDuration.setText(String.valueOf(month).concat("개월"));
        mTvServiceCategory.setText(intent.getExtras().getString("category"));

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_detail_back:
                this.finish();
                break;
        }
    }

}
