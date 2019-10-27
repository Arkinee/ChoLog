package com.makeus.ChoLog.src.product;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.BaseActivity;
import com.makeus.ChoLog.src.product.adapter.ProductAdapter;
import com.makeus.ChoLog.src.product.model.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends BaseActivity implements TextWatcher {

    private AutoCompleteTextView mAutoService;
    private EditText mEdtMembership;
    private EditText mEdtCategory;

    private TextView mTvServiceUnder;
    private TextView mTvMembershipUnder;
    private TextView mTvCategoryUnder;
    private TextView mTvProductComplete;

    private ArrayList<ProductItem> mProductList;
    private ProductAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        this.initialize();
        this.setEdt();

    }

    void initialize() {

        mAutoService = findViewById(R.id.auto_product_service);
        mEdtMembership = findViewById(R.id.edt_product_membership);
        mEdtCategory = findViewById(R.id.edt_product_category);
        mTvServiceUnder = findViewById(R.id.tv_product_service_name_under);
        mTvMembershipUnder = findViewById(R.id.tv_product_membership_under);
        mTvCategoryUnder = findViewById(R.id.tv_product_category_under);
        mTvProductComplete = findViewById(R.id.tv_product_complete);

        mProductList = new ArrayList<>();
        //dummy data
        ProductItem dummy = new ProductItem("adobe", "https://wkdk.me/images/f/f1/Adobe_Creative_Cloud_%EC%95%84%EC%9D%B4%EC%BD%98.png", "저장 클라우드");
        mProductList.add(dummy);
        mProductList.add(new ProductItem("어도비", "https://wkdk.me/images/f/f1/Adobe_Creative_Cloud_%EC%95%84%EC%9D%B4%EC%BD%98.png", "저장 클라우드"));
        mProductList.add(new ProductItem("adobe2", "https://wkdk.me/images/f/f1/Adobe_Creative_Cloud_%EC%95%84%EC%9D%B4%EC%BD%98.png", "저장 클라우드"));
        mProductList.add(new ProductItem("어도비2", "https://wkdk.me/images/f/f1/Adobe_Creative_Cloud_%EC%95%84%EC%9D%B4%EC%BD%98.png", "저장 클라우드"));
        mProductList.add(new ProductItem("도비", "https://wkdk.me/images/f/f1/Adobe_Creative_Cloud_%EC%95%84%EC%9D%B4%EC%BD%98.png", "저장 클라우드"));

        mAdapter = new ProductAdapter(this, R.layout.item_product, mProductList);
        mAdapter.notifyDataSetChanged();
        mAutoService.setThreshold(1);
        mAutoService.setAdapter(mAdapter);
        mAutoService.addTextChangedListener(this);
        mAutoService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mEdtMembership.requestFocus();
                mEdtCategory.setText(mProductList.get(i).getmCategory());
            }
        });

    }

    void setEdt() {

        mAutoService.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mTvServiceUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
                } else {
                    mTvServiceUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
                }
            }
        });

        mEdtMembership.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mTvMembershipUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
                } else {
                    mTvMembershipUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
                }
            }
        });

        mEdtCategory.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mTvCategoryUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
                } else {
                    mTvCategoryUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
                }
            }
        });

        mAutoService.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    mEdtMembership.requestFocus();
                }
                return false;
            }
        });

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_product_back:
                this.finish();
                break;
            case R.id.tv_product_complete:

                Intent intent = new Intent();
                String name = mAutoService.getText().toString()
                        .concat(getResources().getString(R.string.middleDot))
                        .concat(mEdtMembership.getText().toString());
                intent.putExtra("product", name);
                intent.putExtra("category", mEdtCategory.getText().toString());
                setResult(RESULT_OK, intent);
                this.finish();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        if (mAutoService.isPerformingCompletion()) {
            return;
        } else {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdapter.notifyDataSetChanged();
                    mAutoService.showDropDown();
                }
            });
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
