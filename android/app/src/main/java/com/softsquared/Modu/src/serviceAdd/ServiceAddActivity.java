package com.softsquared.Modu.src.serviceAdd;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.softsquared.Modu.R;
import com.softsquared.Modu.src.BaseActivity;
import com.softsquared.Modu.src.currency.CurrencyActivity;
import com.softsquared.Modu.src.dialog.alarmDialog.AlarmDialog;
import com.softsquared.Modu.src.dialog.alarmDialog.AlarmListener;
import com.softsquared.Modu.src.dialog.durationDialog.DurationDialog;
import com.softsquared.Modu.src.dialog.durationDialog.DurationListener;
import com.softsquared.Modu.src.dialog.lastDialog.LastDialog;
import com.softsquared.Modu.src.dialog.lastDialog.LastListener;
import com.softsquared.Modu.src.dialog.removeDialog.RemoveDialog;
import com.softsquared.Modu.src.dialog.removeDialog.removeListener;
import com.softsquared.Modu.src.home.models.HomeItem;
import com.softsquared.Modu.src.product.ProductActivity;
import com.softsquared.Modu.src.serviceAdd.interfaces.ServiceAddActivityView;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Objects;

import static com.softsquared.Modu.src.ApplicationClass.sSharedPreferences;

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

    private ImageView mIvImageAdd;

    private TextView mTvServiceAddBtn;

    private ScrollView mScrollServiceAdd;
    private TextView mTvCurrency;

    private String mCategory;
    private String mMembership;
    private String mBrand;
    private Boolean mChecked;
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

    //requestCode
    private final int CURRENCY = 2000;
    private final int PRODUCT = 3000;

    //currency krw to usd
    private double mKRWtoUSD;
    private int mPrice;
    private int mType;
    private int mCompare;
    private String mImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add);
        this.initialize();

    }

    void initialize() {

        mKRWtoUSD = Double.parseDouble(sSharedPreferences.getString("KRWtoUSD", "0"));
        mImageUrl = "";

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

        mIvImageAdd = findViewById(R.id.iv_service_image_add);

        mIM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mScrollServiceAdd = findViewById(R.id.scroll_service_add);
        mTvCurrency = findViewById(R.id.tv_service_add_currency_put);

        mType = Objects.requireNonNull(getIntent().getExtras()).getInt("type");
        mIndex = getIntent().getExtras().getInt("index");
        mMembership = "";
        mBrand = "";
        mChecked = false;

        Log.d("로그", "type: " + mType + " ," + "index: " + mIndex);

        //화폐 단위 선정
        if (mType == 1) {
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

        //type 1 : add , 2 : modify
        if (mType == 1) {
            mTvServiceAddBtn.setText(getResources().getString(R.string.tv_service_add));
        } else if (mType == 2) {
            mTvServiceAddBtn.setText(getResources().getString(R.string.tv_service_modify));
            HomeItem item = (HomeItem) getIntent().getExtras().get("item");
            mCompare = Objects.requireNonNull(item).getmPrice();
            this.setField(item);
        }

        //edt listener
        this.setEdtListener();

    }

    //서비스 수정일때 아이템의 값 가져와서 채우기
    public void setField(HomeItem item) {

        //필수 정보 채우기
        mTvProduct.setText(item.getmBrand().concat(getResources().getString(R.string.middleDot)).concat(item.getmProduct()));
        mCategory = item.getmCategory();
        mBrand = item.getmBrand();
        mMembership = item.getmProduct();
        mChecked = item.isChecked();

//        Log.d("로그", "price: " + item.getmPrice());
//        Log.d("로그", "currency: " + mCurrency);

        if (mCurrency == 1) {
            mEdtPrice.setText(String.valueOf(item.getmPrice()));
        } else if (mCurrency == 2) {
            double temp = item.getmPrice() / mKRWtoUSD;
            mEdtPrice.setText(new DecimalFormat("##.##").format(temp));
        }

        mTvCurrency.setVisibility(View.VISIBLE);
        String[] num = item.getmLast().split("-");
        int[] parse = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            parse[i] = Integer.valueOf(num[i]);
        }

        mTvLast.setText(item.getmLast().replaceAll("-", "."));
        mYear = parse[0];
        mMonth = parse[1];
        mDay = parse[2];

        mDurationNum = item.getmDuration();
        mDurationPer = item.getmDurationPer();

        if (mDurationPer == 0) {
            mTvDuration.setText(String.valueOf(mDurationNum).concat(getResources().getString(R.string.day)));
        } else if (mDurationPer == 1) {
            mTvDuration.setText(String.valueOf(mDurationNum).concat(getResources().getString(R.string.week)));
        } else if (mDurationPer == 2) {
            mTvDuration.setText(String.valueOf(mDurationNum).concat(getResources().getString(R.string.month)));
        } else if (mDurationPer == 3) {
            mTvDuration.setText(String.valueOf(mDurationNum).concat(getResources().getString(R.string.year)));
        }

        //추후 알림 업데이트 시에 알림 채우는 양식 필요
        mAlarmNum = item.getmAlarm();

        String alarmNum = String.valueOf(mAlarmNum);
        String alarmPer = getResources().getStringArray(R.array.day_week_month_year)[0];    // 일 단위

        if (mAlarmNum == -1) {
            mTvAlarm.setText(getResources().getString(R.string.tv_service_add_dialog_alarm_none));
        } else {
            mTvAlarm.setText(alarmNum.concat(alarmPer));
        }

        //추가 정보 채우기
        if (item.getmExtra() != null && !item.getmExtra().equals("")) {
            mEdtExtra.setText(item.getmExtra());
        }

        if (item.getmChangeUrl() != null && !item.getmChangeUrl().equals("")) {
            mEdtChange.setText(item.getmChangeUrl());
        }

        if (item.getmCancelUrl() != null && !item.getmCancelUrl().equals("")) {
            mEdtCancel.setText(item.getmCancelUrl());
        }

        if (item.getmPhone() != null && !item.getmPhone().equals("")) {
            mEdtCancelPhone.setText(item.getmPhone());
        }

        mImageUrl = item.getmImageUrl();
        Glide.with(this).load(mImageUrl).placeholder(R.drawable.ic_default).override(50, 50).into(mIvImageAdd);

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
                    mEdtPrice.clearFocus();
                    mIM.hideSoftInputFromWindow(mEdtPrice.getWindowToken(), 0);
                    showLastDialog();
                    mScrollServiceAdd.smoothScrollTo(x, y);
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
                    mCurrency = data.getExtras().getInt("cur");
                    if (mCurrency == 1) {
                        mEdtPrice.setHint(R.string.tv_currency_kor_won);
                        mTvCurrency.setText(R.string.tv_currency_kor_won);
                    } else if (mCurrency == 2) {
                        mEdtPrice.setHint(R.string.tv_currency_us_dollar);
                        mTvCurrency.setText(R.string.tv_currency_us_dollar);
                    }
                    break;
                case PRODUCT:
                    String product = data.getExtras().getString("product");
                    mCategory = data.getExtras().getString("category");
                    mImageUrl = data.getExtras().getString("imageUrl");
                    mBrand = data.getStringExtra("brand");
                    mMembership = data.getStringExtra("membership");
                    String change = data.getExtras().getString("changeUrl");
                    String cancel = data.getExtras().getString("cancelUrl");

                    if (change != null) {
                        mEdtChange.setText(change);
                    }

                    if (cancel != null) {
                        mEdtCancel.setText(cancel);
                    }

                    if (data.getExtras().getString("currency").equals("원")) {
                        mCurrency = 1;
                        mEdtPrice.setText(String.valueOf((int) (data.getExtras().getDouble("price"))));
                    } else {
                        mCurrency = 2;
                        mEdtPrice.setText(String.valueOf(data.getExtras().getDouble("price")));
                    }

                    if (mImageUrl != null) {
                        mImageUrl = mImageUrl.replace("drive.google.com/open", "docs.google.com/uc");
                    }
                    Glide.with(this).load(mImageUrl).placeholder(R.drawable.ic_default).override(100, 100).into(mIvImageAdd);
                    mTvProduct.setText(product);
                    break;
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edt_service_add_price:
                mEdtPrice.setSelection(mEdtPrice.length());
                break;
            case R.id.iv_service_add_back:
                this.finish();
                break;
            case R.id.linear_service_add_price:
                mEdtPrice.requestFocus();
                mIM.showSoftInput(mEdtPrice, 0);
                break;
            case R.id.linear_service_add_won_dollar:
                Intent currencyIntent = new Intent(this, CurrencyActivity.class);
                currencyIntent.putExtra("cur", mCurrency);
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

                if (mCurrency == 1 && !isStringInt(mEdtPrice.getText().toString())) {
                    showCustomToast(getResources().getString(R.string.tv_service_toast_price_not_int));
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
                resultIntent.putExtra("category", mCategory);

                if (mCurrency == 1) {
                    mPrice = Integer.parseInt(mEdtPrice.getText().toString());
                } else if (mCurrency == 2) {
                    mPrice = (int) (Double.parseDouble(mEdtPrice.getText().toString()) * mKRWtoUSD);
                }

                resultIntent.putExtra("brand", mBrand);
                resultIntent.putExtra("membership", mMembership);
                Log.d("로그", "service add activity price: " + mPrice);
                resultIntent.putExtra("price", mPrice);
                resultIntent.putExtra("currency", mCurrency);
                resultIntent.putExtra("year", mYear);
                resultIntent.putExtra("month", mMonth);
                resultIntent.putExtra("day", mDay);
                Log.d("로그", "service day:" + mDay);
                resultIntent.putExtra("duration", mDurationNum);
                resultIntent.putExtra("durationPer", mDurationPer);
                resultIntent.putExtra("alarm", mAlarmNum);
                resultIntent.putExtra("check", mChecked);

                //추가정보
                resultIntent.putExtra("imageUrl", mImageUrl);
                resultIntent.putExtra("extra", mEdtExtra.getText().toString());
                resultIntent.putExtra("change", mEdtChange.getText().toString());
                resultIntent.putExtra("cancel", mEdtCancel.getText().toString());
                resultIntent.putExtra("phone", mEdtCancelPhone.getText().toString());

                if (mType == 2) {
                    mCompare = mPrice - mCompare;
                    resultIntent.putExtra("compare", mCompare);
                }

                resultIntent.putExtra("type", mType);
                Log.d("로그", "타입: " + mType);
                resultIntent.putExtra("index", mIndex);

                resultIntent.putExtra("brand", mBrand);
                resultIntent.putExtra("membership", mMembership);

                setResult(RESULT_OK, resultIntent);
                finish();
                break;
            case R.id.tv_service_add_product:
                Intent productIntent = new Intent(this, ProductActivity.class);
                startActivityForResult(productIntent, PRODUCT);
                break;
            case R.id.tv_service_add_last:
                LinearLayout linear = (LinearLayout) mTvLast.getParent();
                int x = linear.getLeft();
                int y = linear.getTop();
                mScrollServiceAdd.smoothScrollTo(x, y);

                this.showLastDialog();
                mEdtPrice.clearFocus();
                break;
            case R.id.linear_service_add_duration:
                this.showDurationDialog();
                break;
            case R.id.linear_service_add_alarm:
                this.showAlarmDialog();
                break;
            case R.id.tv_service_add_remove:
                if (mType == 1) {
                    showCustomToast(getString(R.string.tv_service_toast_remove));
                    break;
                }
                this.showRemoveDialog();
                break;
        }
    }

    public void setDialogWindow(Dialog dialog) {
        WindowManager.LayoutParams wm;
        wm = new WindowManager.LayoutParams();

        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wm.windowAnimations = R.style.AnimationDialogPopUp;

        wm.copyFrom(dialog.getWindow().getAttributes());
        window.setAttributes(wm);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int x = (int) (size.x * 1f);
        int y = (int) (size.y * 1f);
        window.setLayout(x, y);
    }

    public void setRemoveWindow(Dialog dialog) {

        WindowManager.LayoutParams wm;
        wm = new WindowManager.LayoutParams();
//        wm.copyFrom(dialog.getWindow().getAttributes());
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wm.windowAnimations = R.style.AnimationDialogPopUp;
        wm.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        wm.dimAmount = 0.5f;

        wm.copyFrom(dialog.getWindow().getAttributes());
        window.setAttributes(wm);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int x = (int) (size.x * 1f);
        int y = (int) (size.y * 0.97f);
        window.setLayout(x, y);

    }

    public void removeService() {

        if (mEdtPrice.getText().toString().equals("")) {
            showCustomToast(getResources().getString(R.string.tv_service_toast_price));
        }

        Intent remove = new Intent();
        remove.putExtra("type", 3);
        remove.putExtra("index", mIndex);
        remove.putExtra("currency", mCurrency);
        remove.putExtra("category", mCategory);

        if (mCurrency == 1) {
            remove.putExtra("price", Integer.parseInt(mEdtPrice.getText().toString()));
        } else if (mCurrency == 2) {
            remove.putExtra("price", (int) (Double.parseDouble(mEdtPrice.getText().toString()) * mKRWtoUSD));
        }

        setResult(RESULT_OK, remove);
        this.finish();
    }


    //Dialog setting
    public void showRemoveDialog() {

        mRemoveDialog = new RemoveDialog(this);
        mRemoveDialog.setCancelable(false);
        mRemoveDialog.setDialogListener(new removeListener() {
            @Override
            public void onCancelClicked() {
                mRemoveDialog.dismiss();
            }

            @Override
            public void onRemoveClicked() {
                removeService();
                mRemoveDialog.dismiss();
            }
        });
        mRemoveDialog.show();
        this.setRemoveWindow(mRemoveDialog);

    }

    public void showLastDialog() {

        mTvLastUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
        mLastDialog = new LastDialog(this);
        mLastDialog.setCancelable(true);
        mLastDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                mTvLastUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
            }
        });
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

                LinearLayout linear = (LinearLayout) mTvDuration.getParent();
                int x = linear.getLeft();
                int y = linear.getTop();
//                mScrollServiceAdd.smoothScrollTo(x, y);
                showDurationDialog();
                mScrollServiceAdd.smoothScrollTo(x, y);

            }
        });

        mLastDialog.show();
        this.setDialogWindow(mLastDialog);
    }

    public void showDurationDialog() {

        mTvDurationUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
        mDurationDialog = new DurationDialog(this);
        mDurationDialog.setCancelable(true);
        mDurationDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                mTvDurationUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
            }
        });
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

                LinearLayout linear = (LinearLayout) mTvAlarm.getParent();
                int x = linear.getLeft();
                int y = linear.getTop();
//                mScrollServiceAdd.smoothScrollTo(x, y);
                showAlarmDialog();
                mScrollServiceAdd.smoothScrollTo(x, y);

            }
        });

        mDurationDialog.show();
        this.setDialogWindow(mDurationDialog);
    }

    public void showAlarmDialog() {

        mTvAlarmUnder.setBackgroundColor(getResources().getColor(R.color.colorConceptPrimary));
        mAlarmDialog = new AlarmDialog(this);
        mAlarmDialog.setCancelable(true);
        mAlarmDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                mTvAlarmUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));
            }
        });

        mAlarmDialog.setDialogListener(new AlarmListener() {
            @Override
            public void onAlarmComplete(int number) {
                String strPer = getResources().getStringArray(R.array.day_week_month_year)[0];

                if (number == 0) {
                    mTvAlarm.setText(getResources().getString(R.string.tv_service_add_dialog_alarm_none));
                    mAlarmNum = -1;
                } else if (number == 1) {
                    mTvAlarm.setText(getResources().getString(R.string.tv_service_add_dialog_alarm_d_day));
                    mAlarmNum = 0;
                } else {
                    mTvAlarm.setText(String.valueOf(number - 1).concat(strPer));
                    mAlarmNum = number - 1;
                }

                mAlarmDialog.dismiss();
                mTvAlarmUnder.setBackgroundColor(getResources().getColor(R.color.colorTextServiceUnderBarBefore));

            }
        });

        mAlarmDialog.show();
        this.setDialogWindow(mAlarmDialog);

    }

    public boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
