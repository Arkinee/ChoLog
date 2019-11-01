package com.makeus.ChoLog.src.serviceAdd;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.BaseActivity;
import com.makeus.ChoLog.src.currency.CurrencyActivity;
import com.makeus.ChoLog.src.dialog.durationDialog.DurationDialog;
import com.makeus.ChoLog.src.dialog.durationDialog.durationListener;
import com.makeus.ChoLog.src.dialog.lastDialog.LastDialog;
import com.makeus.ChoLog.src.dialog.lastDialog.lastListener;
import com.makeus.ChoLog.src.dialog.removeDialog.RemoveDialog;
import com.makeus.ChoLog.src.dialog.removeDialog.removeListener;
import com.makeus.ChoLog.src.product.ProductActivity;

public class ServiceAddActivity extends BaseActivity {

    private TextView mTvProduct;
    private EditText mEdtPrice;
    private TextView mTvLast;
    private TextView mTvDuration;
    private TextView mTvAlarm;
    private EditText mEdtExtra;
    private EditText mEdtChange;
    private EditText mEdtCancel;
    private EditText mEdtCancelPhone;

    private TextView mTvProductUnder;
    private TextView mTvPriceUnder;
    private TextView mTvLastUnder;
    private TextView mTvWhileUnder;
    private TextView mTvAlarmUnder;
    private TextView mTvExtraUnder;
    private TextView mTvChangeUnder;
    private TextView mTvCancelUnder;
    private TextView mTvCancelPhoneUnder;

    private TextView mTvCurrency;

    private String mCategory;
    private int mIndex;

    private LastDialog mLastDialog;
    private RemoveDialog mRemoveDialog;
    private DurationDialog mDurationDialog;

    // 1 : won , 2 : dollar
    private int mCurrency;

    //requestCode
    private final int CURRENCY = 2000;
    private final int PRODUCT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add);
        this.initialize();

    }

    void initialize() {

        mTvProduct = findViewById(R.id.tv_service_add_product);
        mEdtPrice = findViewById(R.id.edt_service_add_price);
        mTvLast = findViewById(R.id.tv_service_add_last);
        mTvDuration = findViewById(R.id.tv_service_add_duration);
        mTvAlarm = findViewById(R.id.tv_service_add_alarm);
        mEdtExtra = findViewById(R.id.edt_service_add_extra);
        mEdtChange = findViewById(R.id.edt_service_add_change_plan);
        mEdtCancel = findViewById(R.id.edt_service_add_cancel_plan);
        mEdtCancelPhone = findViewById(R.id.edt_service_add_cancel_phone);

        mTvProductUnder = findViewById(R.id.tv_service_product_under);
        mTvPriceUnder = findViewById(R.id.tv_service_price_under);
        mTvLastUnder = findViewById(R.id.tv_service_last_under);
        mTvWhileUnder = findViewById(R.id.tv_service_duration_under);
        mTvAlarmUnder = findViewById(R.id.tv_service_alarm_under);
        mTvExtraUnder = findViewById(R.id.tv_service_extra_under);
        mTvChangeUnder = findViewById(R.id.tv_service_change_plan_under);
        mTvCancelUnder = findViewById(R.id.tv_service_cancel_plan_under);
        mTvCancelPhoneUnder = findViewById(R.id.tv_service_cancel_phone_under);

        mTvCurrency = findViewById(R.id.tv_service_add_currency_put);

        int type = getIntent().getExtras().getInt("type");
        mIndex = getIntent().getExtras().getInt("index");

        //type 1 : add , 2 : modify
        if (type == 1) {

        } else if (type == 2) {

        }

        //화폐 단위 선정
        if (type == 1) {
            mCurrency = 1;
            mTvCurrency.setText(getString(R.string.tv_currency_kor_won));
        } else {
            mCurrency = getIntent().getExtras().getInt("currency");
            if (mCurrency == 1)
                mTvCurrency.setText(getString(R.string.tv_currency_kor_won));
            else mTvCurrency.setText(getString(R.string.tv_currency_us_dollar));
        }


        //Components들 setting
        this.setEdtListener();
        this.setRemoveDialog();
        this.setLastDialog();
        this.setDurationDialog();

    }

    public void setAccordingToType() {

    }

    //EditText Listener setting
    public void setEdtListener() {

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

        mEdtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                if (mEdtPrice.getText().length() == 0) {
                    mTvCurrency.setVisibility(View.GONE);
                } else {
                    mTvCurrency.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

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

        mEdtCancelPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mTvCancelPhoneUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
                } else {
                    mTvCancelPhoneUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CURRENCY:
                    mCurrency = data.getExtras().getInt("index");
                    if (mCurrency == 1) {
                        mEdtPrice.setHint(R.string.tv_currency_kor_won);
                    } else if (mCurrency == 2) {
                        mEdtPrice.setHint(R.string.tv_currency_us_dollar);
                    }
                    break;
                case PRODUCT:
                    String product = data.getExtras().getString("product");
                    mCategory = data.getExtras().getString("category");
                    mTvProduct.setText(product);
                    break;
            }
        }
    }


    //Dialog setting
    public void setRemoveDialog() {

        WindowManager.LayoutParams wm;
        mRemoveDialog = new RemoveDialog(this);

        wm = new WindowManager.LayoutParams();
        wm.copyFrom(mRemoveDialog.getWindow().getAttributes());

        wm.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        wm.dimAmount = 0.7f;

        Window window = mRemoveDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setAttributes(wm);

        mRemoveDialog.setCancelable(true);
        mRemoveDialog.setDialogListener(new removeListener() {
            @Override
            public void onCancelClicked() {
                mRemoveDialog.dismiss();
            }

            @Override
            public void onRemoveClicked() {
                mRemoveDialog.dismiss();
                removeService();
            }
        });

    }

    public void setLastDialog() {

        mLastDialog = new LastDialog(this);

        WindowManager.LayoutParams wm = new WindowManager.LayoutParams();
        wm.copyFrom(mLastDialog.getWindow().getAttributes());
        wm = new WindowManager.LayoutParams();
        wm.copyFrom(mRemoveDialog.getWindow().getAttributes());

        Window window = mLastDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        window.setAttributes(wm);

        mLastDialog.setCancelable(true);
        mLastDialog.setDialogListener(new lastListener() {
            @Override
            public void onComplete() {
                mLastDialog.dismiss();
            }
        });

    }

    public void setDurationDialog() {

        mDurationDialog = new DurationDialog(this);

        WindowManager.LayoutParams wm = new WindowManager.LayoutParams();
        wm.copyFrom(mLastDialog.getWindow().getAttributes());
        wm = new WindowManager.LayoutParams();
        wm.copyFrom(mDurationDialog.getWindow().getAttributes());

        Window window = mDurationDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        window.setAttributes(wm);

        mDurationDialog.setCancelable(true);
        mDurationDialog.setDialogListener(new durationListener() {
            @Override
            public void onComplete() {
                mDurationDialog.dismiss();
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
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int x = (int) (size.x * 1f);
                int y = (int) (size.y * 0.96f);
                window.setLayout(x, y);

                break;
            case R.id.tv_service_add_won_dollar:
                Intent currencyIntent = new Intent(this, CurrencyActivity.class);
                currencyIntent.putExtra("index", mCurrency);
                startActivityForResult(currencyIntent, CURRENCY);
                break;
            case R.id.tv_service_add_btn:

                if (mTvProduct.getText().toString().equals("")) {
                    showCustomToast(getResources().getString(R.string.tv_service_toast_product));
                }

                if (mEdtPrice.getText().toString().equals("")) {
                    showCustomToast(getResources().getString(R.string.tv_service_toast_product));
                }


                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", mTvProduct.getText().toString());
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
            case R.id.tv_service_add_product:
                Intent productIntent = new Intent(this, ProductActivity.class);
                startActivityForResult(productIntent, PRODUCT);
                break;
            case R.id.tv_service_add_last:
                mLastDialog.show();
                Window lastWindow = mLastDialog.getWindow();
                Display lastDisplay = getWindowManager().getDefaultDisplay();
                Point lastSize = new Point();
                lastDisplay.getSize(lastSize);
                int lastX = (int) (lastSize.x * 1f);
                int lastY = (int) (lastSize.y * 0.96f);
                lastWindow.setLayout(lastX, lastY);
                break;
            case R.id.tv_service_add_duration:


        }
    }

    public void removeService() {
        Intent remove = new Intent();
        remove.putExtra("index", mIndex);
        setResult(RESULT_OK, remove);
        this.finish();
    }

}
