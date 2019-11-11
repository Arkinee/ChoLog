package com.makeus.Modu.src.currency;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.makeus.Modu.R;
import com.makeus.Modu.src.BaseActivity;

public class CurrencyActivity extends BaseActivity {

    private LinearLayout mLinearWon;
    private LinearLayout mLinearDollar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        this.initialize();

    }

    void initialize() {

        mLinearWon = findViewById(R.id.linear_currency_kor);
        mLinearDollar = findViewById(R.id.linear_currency_us);

        Intent now = getIntent();
        int currencySet = now.getExtras().getInt("index");

        if (currencySet == 1) {
            mLinearWon.setBackgroundResource(R.drawable.src_currency_clicked);
        } else if (currencySet == 2) {
            mLinearDollar.setBackgroundResource(R.drawable.src_currency_clicked);
        }

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_currency_back:
                this.finish();
                break;
            case R.id.linear_currency_kor:
                mLinearWon.setBackgroundResource(R.drawable.src_currency_clicked);
                mLinearDollar.setBackgroundResource(R.drawable.src_currency);
                Intent won = new Intent();
                won.putExtra("index", 1);
                setResult(RESULT_OK, won);
                finish();
                break;
            case R.id.linear_currency_us:
                mLinearWon.setBackgroundResource(R.drawable.src_currency);
                mLinearDollar.setBackgroundResource(R.drawable.src_currency_clicked);
                Intent dollar = new Intent();
                dollar.putExtra("index", 2);
                setResult(RESULT_OK, dollar);
                finish();
                break;
        }
    }

}
