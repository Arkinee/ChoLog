package com.makeus.Modu.src.product;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.makeus.Modu.R;
import com.makeus.Modu.src.BaseActivity;
import com.makeus.Modu.src.main.models.Items;
import com.makeus.Modu.src.product.adapter.ProductAdapter;
import com.makeus.Modu.src.product.model.ProductItem;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.makeus.Modu.src.ApplicationClass.sSharedPreferences;

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
    private String mImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        this.initialize();
        this.setEdt();

    }

    void initialize() {

        ArrayList<Items> items = new ArrayList<>();
        mProductList = new ArrayList<>();
        mImageUrl = "";

        String itemList = sSharedPreferences.getString("items", "");
        Type listType = new TypeToken<ArrayList<Items>>() {
        }.getType();

        Gson gson = new GsonBuilder().create();
        if (gson.fromJson(itemList, listType) != null) {
            items = gson.fromJson(itemList, listType);
        }

        for (int i = 0; i < items.size(); i++) {
            Items temp = items.get(i);
            mProductList.add(new ProductItem(temp.getCompanyName(), temp.getLogo(), temp.getCategory()));
        }

        mAutoService = findViewById(R.id.auto_product_service);
        mEdtMembership = findViewById(R.id.edt_product_membership);
        mEdtCategory = findViewById(R.id.edt_product_category);
        mTvServiceUnder = findViewById(R.id.tv_product_service_name_under);
        mTvMembershipUnder = findViewById(R.id.tv_product_membership_under);
        mTvCategoryUnder = findViewById(R.id.tv_product_category_under);
        mTvProductComplete = findViewById(R.id.tv_product_complete);

        //dummy data
//        mProductList.add(new ProductItem("어도비", "https://wkdk.me/images/f/f1/Adobe_Creative_Cloud_%EC%95%84%EC%9D%B4%EC%BD%98.png", "저장 클라우드(어도비)"));

        mAdapter = new ProductAdapter(this, R.layout.item_product, mProductList);
        mAdapter.notifyDataSetChanged();
        mAutoService.setThreshold(1);
//        mAutoService.setDropDownBackgroundResource();
        mAutoService.setElevation(0);
        mAutoService.setAdapter(mAdapter);
        mAutoService.addTextChangedListener(this);
        mAutoService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mEdtMembership.requestFocus();
                ProductItem temp = (ProductItem) adapterView.getAdapter().getItem(position);
                mEdtCategory.setText(temp.getmCategory());
                mImageUrl = temp.getmImageUrl();
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

                if (mAutoService.getText().toString().equals("")) {
                    showCustomToast(getResources().getString(R.string.tv_product_service_not));
                    break;
                }

                if (mEdtCategory.getText().toString().equals("")) {
                    showCustomToast(getResources().getString(R.string.tv_product_category_not));
                    break;
                }

                Intent intent = new Intent();
                String name;
                //멤버십 적었으면 미들닷으로 구분, 안적었으면 서비스명만 기입
                if (!mEdtMembership.getText().toString().equals("")) {
                    name = mAutoService.getText().toString()
                            .concat(getResources().getString(R.string.middleDot))
                            .concat(mEdtMembership.getText().toString());
                } else {
                    name = mAutoService.getText().toString();
                }
                intent.putExtra("product", name);
                intent.putExtra("category", mEdtCategory.getText().toString());
                intent.putExtra("imageUrl", mImageUrl);
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
