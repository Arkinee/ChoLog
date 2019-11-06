package com.makeus.ChoLog.src.serviceAdd;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.BaseActivity;
import com.makeus.ChoLog.src.currency.CurrencyActivity;
import com.makeus.ChoLog.src.dialog.alarmDialog.AlarmDialog;
import com.makeus.ChoLog.src.dialog.alarmDialog.AlarmListener;
import com.makeus.ChoLog.src.dialog.durationDialog.DurationDialog;
import com.makeus.ChoLog.src.dialog.durationDialog.DurationListener;
import com.makeus.ChoLog.src.dialog.lastDialog.LastDialog;
import com.makeus.ChoLog.src.dialog.lastDialog.LastListener;
import com.makeus.ChoLog.src.dialog.removeDialog.RemoveDialog;
import com.makeus.ChoLog.src.dialog.removeDialog.removeListener;
import com.makeus.ChoLog.src.product.ProductActivity;
import com.makeus.ChoLog.src.serviceAdd.interfaces.ServiceAddActivityView;

public class ServiceAddActivity extends BaseActivity implements ServiceAddActivityView {

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
    private TextView mTvDurationUnder;
    private TextView mTvAlarmUnder;
    private TextView mTvExtraUnder;
    private TextView mTvChangeUnder;
    private TextView mTvCancelUnder;
    private TextView mTvCancelPhoneUnder;

    private TextView mTvServiceAddBtn;

    private ScrollView mScrollServiceAdd;
    private TextView mTvCurrency;

    private String mCategory;
    private int mIndex;

    private LastDialog mLastDialog;
    private RemoveDialog mRemoveDialog;
    private DurationDialog mDurationDialog;
    private AlarmDialog mAlarmDialog;

    private InputMethodManager mIM;

    // 1 : won , 2 : dollar
    private int mCurrency;
    private int mYear;
    private int mMonth;
    private int mDay;

    private int mDurationNum;
    private int mDurationPer;   // 0 : 일  1 : 주  2 : 달  3 : 년

    private int mAlarmNum;
    private int mAlarmPer;  // 0 : 일  1 : 주

    //requestCode
    private final int CURRENCY = 2000;
    private final int PRODUCT = 3000;

    //currency krw to usd
    private double mKRWtoUSD;
    private int mPrice;

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
        mTvServiceAddBtn = findViewById(R.id.tv_service_add_btn);

        mTvProductUnder = findViewById(R.id.tv_service_product_under);
        mTvPriceUnder = findViewById(R.id.tv_service_price_under);
        mTvLastUnder = findViewById(R.id.tv_service_last_under);
        mTvDurationUnder = findViewById(R.id.tv_service_duration_under);
        mTvAlarmUnder = findViewById(R.id.tv_service_alarm_under);
        mTvExtraUnder = findViewById(R.id.tv_service_extra_under);
        mTvChangeUnder = findViewById(R.id.tv_service_change_plan_under);
        mTvCancelUnder = findViewById(R.id.tv_service_cancel_plan_under);
        mTvCancelPhoneUnder = findViewById(R.id.tv_service_cancel_phone_under);

        mIM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mScrollServiceAdd = findViewById(R.id.scroll_service_add);
        mTvCurrency = findViewById(R.id.tv_service_add_currency_put);

        int type = getIntent().getExtras().getInt("type");
        mIndex = getIntent().getExtras().getInt("index");

        //type 1 : add , 2 : modify
        if (type == 1) {
            mTvServiceAddBtn.setText(getResources().getString(R.string.tv_service_add));
        } else if (type == 2) {
            mTvServiceAddBtn.setText(getResources().getString(R.string.tv_service_modify));
            this.setField();
        }

        //화폐 단위 선정
        if (type == 1) {
            mCurrency = 1;
            mTvCurrency.setText(getString(R.string.tv_currency_kor_won));
        } else {
            mCurrency = getIntent().getExtras().getInt("currency");
            if (mCurrency == 1) {
                mTvCurrency.setText(getString(R.string.tv_currency_kor_won));
                mEdtPrice.setHint(R.string.tv_currency_kor_won);
            } else {
                mTvCurrency.setText(getString(R.string.tv_currency_us_dollar));
                mEdtPrice.setHint(R.string.tv_currency_us_dollar);
            }
        }

        //edt listener
        this.setEdtListener();

    }

    //서비스 수정일때 아이템의 값 가져와서 채우기
    public void setField() {

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

        mEdtPrice.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    LinearLayout linear = (LinearLayout) mTvLast.getParent();
                    int x = linear.getLeft();
                    int y = linear.getTop();
                    mScrollServiceAdd.smoothScrollTo(x, y);
                    mEdtPrice.clearFocus();
                    mIM.hideSoftInputFromWindow(mEdtPrice.getWindowToken(), 0);
                    showLastDialog();
                    return true;
                }
                return false;
            }
        });

        mEdtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                if (mEdtPrice.getText().length() == 0) {
                    if (mCurrency == 1) {
                        mTvCurrency.setText(getResources().getString(R.string.tv_currency_kor_won));
                    } else if (mCurrency == 2) {
                        mTvCurrency.setText(getResources().getString(R.string.tv_currency_us_dollar));
                    }
                    mTvCurrency.setVisibility(View.GONE);
                } else {
                    if (mCurrency == 1) {
                        mTvCurrency.setText(getResources().getString(R.string.tv_currency_kor_won));
                    } else if (mCurrency == 2) {
                        mTvCurrency.setText(getResources().getString(R.string.tv_currency_us_dollar));
                    }
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
                        mTvCurrency.setText(R.string.tv_currency_kor_won);
                    } else if (mCurrency == 2) {
                        mEdtPrice.setHint(R.string.tv_currency_us_dollar);
                        mTvCurrency.setText(R.string.tv_currency_us_dollar);
                        final ServiceAddService serviceAddService = new ServiceAddService(this);
                        serviceAddService.getCurrency();
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

        mRemoveDialog = new RemoveDialog(this);
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
        mRemoveDialog.show();

        WindowManager.LayoutParams wm;
        wm = new WindowManager.LayoutParams();

        Window window = mRemoveDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setAttributes(wm);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int x = (int) (size.x * 1f);
        int y = (int) (size.y * 0.96f);
        window.setLayout(x, y);
    }

    public void showLastDialog() {

        mTvLastUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
        mLastDialog = new LastDialog(this);
        mLastDialog.setCancelable(true);
        mLastDialog.setDialogListener(new LastListener() {
            @Override
            public void onLastComplete(int year, int month, int day) {
                mYear = year;
                mMonth = month;
                mDay = day;
                String last = String.valueOf(year).concat(".").concat(String.valueOf(month + 1)).concat(".").concat(String.valueOf(day));
                mTvLast.setText(last);
                mLastDialog.dismiss();
                mTvLastUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
            }
        });
        mLastDialog.show();
        this.setDialogWindow(mLastDialog);
    }

    public void showDurationDialog() {

        mTvDurationUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
        mDurationDialog = new DurationDialog(this);
        mDurationDialog.setCancelable(true);
        mDurationDialog.setDialogListener(new DurationListener() {
            @Override
            public void onDurationComplete(int number, int per) {
                mDurationNum = number;
                String strPer = "";
                switch (per) {
                    case 0:
                        //일
                        strPer = getResources().getStringArray(R.array.day_week_month_year)[0];
                        mDurationPer = 0;
                        break;
                    case 1:
                        //주
                        strPer = getResources().getStringArray(R.array.day_week_month_year)[1];
                        mDurationPer = 1;
                        break;
                    case 2:
                        //달
                        strPer = getResources().getStringArray(R.array.day_week_month_year)[2];
                        mDurationPer = 2;
                        break;
                    case 3:
                        //년
                        strPer = getResources().getStringArray(R.array.day_week_month_year)[3];
                        mDurationPer = 3;
                        break;
                }

                mTvDuration.setText(String.valueOf(number).concat(strPer));
                mDurationDialog.dismiss();
                mTvDurationUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
            }
        });

        mDurationDialog.show();
        this.setDialogWindow(mDurationDialog);
    }

    public void showAlarmDialog() {

        mTvAlarmUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
        mAlarmDialog = new AlarmDialog(this);
        mAlarmDialog.setCancelable(true);
        mAlarmDialog.setDialogListener(new AlarmListener() {
            @Override
            public void onAlarmComplete(int number, int per) {
                String strPer = "";
                switch (per) {
                    case 0: //일
                        strPer = getResources().getStringArray(R.array.day_week_month_year)[0];
                        mAlarmPer = 0;
                        break;
                    case 1: //주
                        strPer = getResources().getStringArray(R.array.day_week_month_year)[1];
                        mAlarmPer = 1;
                        break;
                    case 2: //달
                        strPer = getResources().getStringArray(R.array.day_week_month_year)[2];
                        mAlarmPer = 2;
                        break;
                    case 3: //년
                        strPer = getResources().getStringArray(R.array.day_week_month_year)[3];
                        mAlarmPer = 3;
                        break;
                }

                if (number == 0) {
                    mTvAlarm.setText(getResources().getString(R.string.tv_service_add_dialog_alarm_none));
                    mAlarmNum = -1;
                } else if (number == 1) {
                    mTvAlarm.setText(getResources().getString(R.string.tv_service_add_dialog_alarm_d_day));
                    mAlarmNum = -1;
                } else {
                    mTvAlarm.setText(String.valueOf(number - 1).concat(strPer));
                    mAlarmNum = number;
                }

                mAlarmDialog.dismiss();
                mTvAlarmUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));

            }
        });

        mAlarmDialog.show();
        this.setDialogWindow(mAlarmDialog);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_service_add_back:
                this.finish();
                break;
            case R.id.tv_service_add_remove:
                this.setRemoveDialog();
                break;
            case R.id.linear_service_add_price:
                mEdtPrice.requestFocus();
                mIM.showSoftInput(mEdtPrice, 0);
                break;
            case R.id.linear_service_add_won_dollar:
                Intent currencyIntent = new Intent(this, CurrencyActivity.class);
                currencyIntent.putExtra("index", mCurrency);
                startActivityForResult(currencyIntent, CURRENCY);
                break;
            case R.id.tv_service_add_btn:

                if (mTvProduct.getText().toString().equals("")) {
                    showCustomToast(getResources().getString(R.string.tv_service_toast_product));
                    break;
                }

                if (mEdtPrice.getText().toString().equals("")) {
                    showCustomToast(getResources().getString(R.string.tv_service_toast_price));
                    break;
                }

                if (mTvLast.getText().toString().equals("")) {
                    showCustomToast(getResources().getString(R.string.tv_service_toast_last));
                    break;
                }

                if (mTvDuration.getText().toString().equals("")) {
                    showCustomToast(getResources().getString(R.string.tv_service_toast_duration));
                    break;
                }

                if (mTvAlarm.getText().toString().equals("")) {
                    showCustomToast(getResources().getString(R.string.tv_service_toast_alarm));
                    break;
                }

                Intent resultIntent = new Intent();
                //필수정보
                resultIntent.putExtra("name", mTvProduct.getText().toString());
                resultIntent.putExtra("category", mCategory);
                if (mCurrency == 1) {
                    mPrice = Integer.parseInt(mEdtPrice.getText().toString());
                } else if (mCurrency == 2) {
                    mPrice = (int) (Double.parseDouble(mEdtPrice.getText().toString()) * mKRWtoUSD);
                }

                resultIntent.putExtra("price", mPrice);
                resultIntent.putExtra("currency", mCurrency);
                resultIntent.putExtra("year", mYear);
                resultIntent.putExtra("month", mMonth);
                resultIntent.putExtra("day", mDay);
                Log.d("로그", "service day:" + mDay);
                resultIntent.putExtra("duration", mDurationNum);
                resultIntent.putExtra("durationPer", mDurationPer);
                resultIntent.putExtra("alarm", mAlarmNum);
                resultIntent.putExtra("alarmPer", mAlarmPer);

                //추가정보
                resultIntent.putExtra("extra", mEdtExtra.getText().toString());
                resultIntent.putExtra("change", mEdtChange.getText().toString());
                resultIntent.putExtra("cancel", mEdtCancel.getText().toString());
                resultIntent.putExtra("phone", mEdtCancelPhone.getText().toString());

                setResult(RESULT_OK, resultIntent);
                finish();
                break;
            case R.id.tv_service_add_product:
                Intent productIntent = new Intent(this, ProductActivity.class);
                startActivityForResult(productIntent, PRODUCT);
                break;
            case R.id.tv_service_add_last:
                this.showLastDialog();
                mEdtPrice.clearFocus();
                break;
            case R.id.linear_service_add_duration:
                this.showDurationDialog();
                break;
            case R.id.linear_service_add_alarm:
                this.showAlarmDialog();
                break;
        }
    }

    public void setDialogWindow(Dialog dialog) {

        WindowManager.LayoutParams wm;
        wm = new WindowManager.LayoutParams();

        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wm.windowAnimations = R.style.AnimationDialogPopUp;
        window.setAttributes(wm);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int x = (int) (size.x * 1f);
        int y = (int) (size.y * 0.96f);
        window.setLayout(x, y);

    }

    public void removeService() {
        Intent remove = new Intent();
        remove.putExtra("index", mIndex);
        setResult(RESULT_OK, remove);
        this.finish();
    }

    @Override
    public void getCurrencySuccess(double basePrice) {
        mKRWtoUSD = basePrice;
        hideProgressDialog();
    }

    @Override
    public void getCurrencyFailure(String msg) {
        hideProgressDialog();
        showCustomToast(msg);
    }
}
