package com.softsquared.Modu.src.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.softsquared.Modu.R;
import com.softsquared.Modu.src.BaseActivity;
import com.softsquared.Modu.src.dialog.exitDialog.ExitDialog;
import com.softsquared.Modu.src.dialog.exitDialog.exitListener;
import com.softsquared.Modu.src.dialog.loginDialog.LoginDialog;
import com.softsquared.Modu.src.dialog.loginDialog.loginListener;
import com.softsquared.Modu.src.home.HomeFragment;
import com.softsquared.Modu.src.home.models.HomeItem;
import com.softsquared.Modu.src.login.LoginActivity;
import com.softsquared.Modu.src.lookAround.fragments.LookFragment;
import com.softsquared.Modu.src.lookAround.fragments.LookOfflineFragment;
import com.softsquared.Modu.src.lookAround.fragments.LookOnlineFragment;
import com.softsquared.Modu.src.lookAround.fragments.LookPopularFragment;
import com.softsquared.Modu.src.lookAround.fragments.LookRecommendFragment;
import com.softsquared.Modu.src.lookAround.models.LookListItem;
import com.softsquared.Modu.src.main.interfaces.MainActivityView;
import com.softsquared.Modu.src.main.models.Items;
import com.softsquared.Modu.src.myInfo.MyInfoFragment;
import com.softsquared.Modu.src.myInfo.models.MyInfoItem;
import com.softsquared.Modu.src.serviceAdd.ServiceAddActivity;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.softsquared.Modu.src.ApplicationClass.DATE_FORMAT;
import static com.softsquared.Modu.src.ApplicationClass.myFormatter;
import static com.softsquared.Modu.src.ApplicationClass.sSharedPreferences;

public class MainActivity extends BaseActivity implements MainActivityView {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    private HomeFragment mHomeFragment;
    private MyInfoFragment mMyInfoFragment;
    private LookFragment mLookFragment;
    private LookPopularFragment mLookPoPularFragment;
    private LookRecommendFragment mLookRecommendFragment;
    private LookOnlineFragment mLookOnlineFragment;
    private LookOfflineFragment mLookOfflineFragment;

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapseToolBar;
    private AppBarLayout mAppBar;

    private RelativeLayout mRelativeMainHome;
    private RelativeLayout mRelativeMainMyInfo;

    private ImageView mIvMainMyInfo;
    private ImageView mIvMainHome;
    private ImageView mIvMainLook;
    private ImageView mIvMainMyInfoPrevious;
    private ImageView mIvMainMyInfoNext;
    private ImageView mIvMainSetting;

    private TextView mTvMainServiceMonth;
    private TextView mTvMainHomeServiceFee;
    private TextView mTvMainMyInfoServiceMonth;
    private TextView mTvMainMyInfoServiceFee;
    private TextView mTvMainMyInfo;
    private TextView mTvMainHome;
    private TextView mTvMainLook;
    private TextView mTvMainLookTop;

    private LoginDialog mLoginDialog;
    private ExitDialog mExitDialog;
    private WindowManager.LayoutParams mWm;
    private int mFragmentFlag;
    private String TAG = "로그";
    private Context mContext;
    private int mPrice;
    private double mDollar;

    ArrayList<MyInfoItem> mMainMyInfoList;

    private final int SERVICE = 1000;
    private final int LOGIN = 2000;

    private int mHomeFee;
    private int mMyInfoFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initialize();
        this.setFragment();
        this.setLoginDialog();
        this.setExitDialog();

    }

    void initialize() {

        final MainService mainService = new MainService(this);
        mainService.getCurrency();
        final MainService mainService2 = new MainService(this);
        mainService2.getItems();
        mainService2.getLookList();
        showProgressDialog();

        mContext = this;
        mHomeFee = sSharedPreferences.getInt("homeFee", 0);
        mMyInfoFee = sSharedPreferences.getInt("myInfoFee", 0);

        mMainMyInfoList = new ArrayList<>();

        mToolbar = findViewById(R.id.toolbar_main);
        mAppBar = findViewById(R.id.appbar_main);
        setSupportActionBar(mToolbar);
        mCollapseToolBar = findViewById(R.id.collapseBar_main);

        mIvMainMyInfo = findViewById(R.id.iv_main_my_info);
        mIvMainHome = findViewById(R.id.iv_main_home);
        mIvMainLook = findViewById(R.id.iv_main_look_around);
        mIvMainMyInfoPrevious = findViewById(R.id.iv_main_my_info_previous);
        mIvMainMyInfoNext = findViewById(R.id.iv_main_my_info_next);
        mIvMainSetting = findViewById(R.id.iv_main_setting);

        mTvMainServiceMonth = findViewById(R.id.tv_main_service_month);
        mTvMainHomeServiceFee = findViewById(R.id.tv_main_home_service_fee);
        mTvMainMyInfo = findViewById(R.id.tv_main_my_info);
        mTvMainMyInfoServiceMonth = findViewById(R.id.tv_main_my_info_service_month);
        mTvMainMyInfoServiceFee = findViewById(R.id.tv_main_my_info_service_fee);
        mTvMainHome = findViewById(R.id.tv_main_home);
        mTvMainLook = findViewById(R.id.tv_main_look_around);
        mTvMainLookTop = findViewById(R.id.tv_main_look_top);

        mRelativeMainHome = findViewById(R.id.relative_main_home);
        mRelativeMainMyInfo = findViewById(R.id.relative_main_my_info);

        mFragmentFlag = 1;

        //몇 월 구독 서비스 이용료인지
//        Date currentTime = Calendar.getInstance().getTime();
//        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
//        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
//        String month = monthFormat.format(currentTime);
//        String year = yearFormat.format(currentTime);
//        mTvMainServiceMonth.setText(month.concat(getResources().getString(R.string.tv_serviceFee)));
        mTvMainServiceMonth.setText(getResources().getString(R.string.tv_serviceFee));

        //Home 화면 최상단 가격
        mTvMainHomeServiceFee.setText(myFormatter.format(mHomeFee));
//        mTvMainMyInfoServiceMonth.setText(month.concat(getString(R.string.tv_main_my_info_month)).concat(" ").concat(year));  // 추후 월별 소비량 적용시
        mTvMainMyInfoServiceFee.setText(myFormatter.format(mMyInfoFee));
        mTvMainMyInfoServiceMonth.setText(getString(R.string.tv_service_my_pattern));

        boolean isLogin = sSharedPreferences.getBoolean("isLogin", false);

        if (isLogin) {
            mIvMainSetting.setImageDrawable(getResources().getDrawable(R.drawable.ic_login_true));
        } else {
            mIvMainSetting.setImageDrawable(getResources().getDrawable(R.drawable.ic_login_false));
        }

    }

    public void setFragment() {

        mFragmentManager = getSupportFragmentManager();
        mHomeFragment = new HomeFragment();
        mMyInfoFragment = new MyInfoFragment();
        mLookFragment = new LookFragment();
        mLookPoPularFragment = new LookPopularFragment();
        mLookRecommendFragment = new LookRecommendFragment();
        mLookOnlineFragment = new LookOnlineFragment();
        mLookOfflineFragment = new LookOfflineFragment();

        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.frame_main, mHomeFragment).commitAllowingStateLoss();
    }

    public void setLoginDialog() {

        mLoginDialog = new LoginDialog(this);

        mWm = new WindowManager.LayoutParams();
        mWm.copyFrom(Objects.requireNonNull(mLoginDialog.getWindow()).getAttributes());

        mWm.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        mWm.dimAmount = 0.5f;

        mLoginDialog.setCancelable(false);
        mLoginDialog.setDialogListener(new loginListener() {
            @Override
            public void onLaterClicked() {
                mLoginDialog.dismiss();
            }

            @Override
            public void onNowClicked() {
                mLoginDialog.dismiss();
                Intent loginIntent = new Intent(mContext, LoginActivity.class);
                startActivityForResult(loginIntent, 3000);

            }
        });

    }

    public void setExitDialog() {

        mExitDialog = new ExitDialog(this);

        mWm = new WindowManager.LayoutParams();
        mWm.copyFrom(Objects.requireNonNull(mExitDialog.getWindow()).getAttributes());

        mWm.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        mWm.dimAmount = 0.5f;

        mExitDialog.setCancelable(false);
        mExitDialog.setDialogListener(new exitListener() {
            @Override
            public void onExitPositiveClicked() {
                mExitDialog.dismiss();
                finish();
            }

            @Override
            public void onExitNegativeClicked() {
                mExitDialog.dismiss();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


//        Log.d("로그", "request code: " + requestCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SERVICE:
                    Intent homeIntent = data;
                    HomeFragment hf = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);

                    assert homeIntent != null;
                    int type = Objects.requireNonNull(homeIntent.getExtras()).getInt("type");
//                    Log.d("로그", "Type: " + type);
//                    Log.d("로그", "index: " + homeIntent.getExtras().getInt("index"));

                    String category = homeIntent.getExtras().getString("category");
                    int price = homeIntent.getExtras().getInt("price");
//                    Log.d("로그", "price: " + price);

                    if (type == 1) {    //서비스 추가
                        mHomeFee += price;
                        mMyInfoFee += price;
                        setHomeFee(mHomeFee);
                        setMyInfoFee(mMyInfoFee);
                    } else if (type == 2) {     //서비스 관리
                        int compare = homeIntent.getExtras().getInt("compare");
                        mHomeFee += compare;
                        mMyInfoFee += compare;
                        setHomeFee(mHomeFee);
                        setMyInfoFee(mMyInfoFee);
                    } else if (type == 3) {     //서비스 삭제
                        assert hf != null;
                        hf.removeItem(homeIntent.getExtras().getInt("index"));
                        mHomeFee -= price;
                        mMyInfoFee -= price;
                        setHomeFee(mHomeFee);
                        setMyInfoFee(mMyInfoFee);
                        this.removeMyInfoItem(category, price, mMyInfoFee);
                        this.saveMyInfoList();
//                        Log.d("로그", "HomeFee: " + mHomeFee);
                        break;
                    }

                    String name = homeIntent.getExtras().getString("name");
                    int currency = homeIntent.getExtras().getInt("currency");
                    int duration = homeIntent.getExtras().getInt("duration");
                    int month_int = homeIntent.getExtras().getInt("month");
                    String month;
                    String day;
                    String year = String.valueOf(homeIntent.getExtras().getInt("year"));
                    int day_int = homeIntent.getExtras().getInt("day");

                    if (type == 1) {        //서비스 추가
                        month_int = month_int + 1;
                    }


                    //month 와 day 가 10보다 작으면 앞에 0 붙여주기
                    if (month_int < 10) {
                        month = "0".concat(String.valueOf(month_int));
                    } else {
                        month = String.valueOf(month_int);
                    }

                    if (day_int < 10) {
                        day = "0".concat(String.valueOf(day_int));
                    } else {
                        day = String.valueOf(day_int);
                    }
//                    Log.d("로그", "main day:" + day);

                    String hyphen = this.getString(R.string.tv_item_home_hyphen);
                    String last = year.concat(hyphen).concat(month).concat(hyphen).concat(day);
                    int durationPer = homeIntent.getExtras().getInt("durationPer");
                    int alarm = homeIntent.getExtras().getInt("alarm");
                    int alarmPer = homeIntent.getExtras().getInt("alarmPer");
                    String extra = homeIntent.getExtras().getString("extra");
                    String changeUrl = homeIntent.getExtras().getString("change");
                    String cancelUrl = homeIntent.getExtras().getString("cancel");
                    String phone = homeIntent.getExtras().getString("phone");

                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat now_format = new SimpleDateFormat("yyyy-MM-dd");
                    Date todayDate = new Date();
                    String today = now_format.format(todayDate);
                    Date nextDate;
                    int calDateDays = 0;

                    try {
                        todayDate = DATE_FORMAT.parse(today);
                        nextDate = DATE_FORMAT.parse(last);
                        cal.setTime(nextDate);
                        if (durationPer == 0) {
                            cal.add(Calendar.DAY_OF_MONTH, duration);
                        } else if (durationPer == 1) {
                            cal.add(Calendar.WEEK_OF_MONTH, duration);
                        } else if (durationPer == 2) {
                            cal.add(Calendar.MONTH, duration);
                        } else if (durationPer == 3) {
                            cal.add(Calendar.YEAR, duration);
                        }

                        nextDate = new Date(cal.getTimeInMillis());
                        long calDate = nextDate.getTime() - todayDate.getTime();
                        calDateDays = (int) (calDate / (24 * 60 * 60 * 1000));
                        calDateDays = Math.abs(calDateDays);
                    } catch (ParseException e) {
                        break;
                    }

//                    Log.d("로그", "main dday: " + calDateDays);
                    String url = homeIntent.getStringExtra("imageUrl");
                    HomeItem item = new HomeItem(name, category, price, currency, url, calDateDays, last, duration, durationPer, alarm, alarmPer, extra, changeUrl, cancelUrl, phone, false);

                    if (type == 1) {
                        assert hf != null;
                        hf.addItem(item);
                        this.addMyInfoItem(category, price, mMyInfoFee);
                        this.saveMyInfoList();
                    } else if (type == 2) {
                        assert hf != null;
                        hf.setItem(homeIntent.getExtras().getInt("index"), item);
                        int compare = homeIntent.getExtras().getInt("compare");
//                        Log.d("로그", "compare: " + compare);
                        this.fixMyInfoItem(category, compare, mMyInfoFee);
                        this.saveMyInfoList();
                    }

                    break;
                case LOGIN:
                    break;
            }
        }
    }

    public void onClick(View view) {
        mTransaction = mFragmentManager.beginTransaction();

        switch (view.getId()) {
            case R.id.linear_main_home:
                //Fragment 교체
                mFragmentFlag = 1;
                mTransaction.replace(R.id.frame_main, mHomeFragment).commitAllowingStateLoss();

                //AppBar부분 구성요소들
                mTvMainServiceMonth.setVisibility(View.VISIBLE);
                mRelativeMainHome.setVisibility(View.VISIBLE);
                mRelativeMainMyInfo.setVisibility(View.GONE);
                mTvMainMyInfoServiceMonth.setVisibility(View.GONE);
                mIvMainMyInfoPrevious.setVisibility(View.GONE);
                mIvMainMyInfoNext.setVisibility(View.GONE);
                mTvMainLookTop.setVisibility(View.GONE);

                mAppBar.setExpanded(true);

                //하단 네비게이션바
                mTvMainMyInfo.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));
                mTvMainHome.setTextColor(getResources().getColor(R.color.colorTextMainNavigationClicked));
                mTvMainLook.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));

                mIvMainHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_clicked));
                mIvMainMyInfo.setImageDrawable(getResources().getDrawable(R.drawable.ic_consumption_normal));
                mIvMainLook.setImageDrawable(getResources().getDrawable(R.drawable.ic_look_around_normal));

                break;

            case R.id.linear_main_my_info:
                //Fragment 교체
                mFragmentFlag = 2;
                mTransaction.replace(R.id.frame_main, mMyInfoFragment).commitAllowingStateLoss();

                //AppBar부분 구성요소들
                mTvMainServiceMonth.setVisibility(View.GONE);
                mRelativeMainHome.setVisibility(View.GONE);
                mRelativeMainMyInfo.setVisibility(View.VISIBLE);
                mTvMainMyInfoServiceMonth.setVisibility(View.VISIBLE);

                mIvMainMyInfoPrevious.setVisibility(View.GONE);
                mIvMainMyInfoNext.setVisibility(View.GONE);
                mTvMainLookTop.setVisibility(View.GONE);

                mAppBar.setExpanded(true);

                //하단 네비게이션바
                mTvMainMyInfo.setTextColor(getResources().getColor(R.color.colorTextMainNavigationClicked));
                mTvMainHome.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));
                mTvMainLook.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));

                mIvMainHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_normal));
                mIvMainMyInfo.setImageDrawable(getResources().getDrawable(R.drawable.ic_consumption_clicked));
                mIvMainLook.setImageDrawable(getResources().getDrawable(R.drawable.ic_look_around_normal));

                break;

            case R.id.linear_main_look_around:
                //Fragment 교체
                mFragmentFlag = 3;
                mTransaction.replace(R.id.frame_main, mLookFragment).commitAllowingStateLoss();

                //AppBar부분 구성요소들
                mTvMainServiceMonth.setVisibility(View.GONE);
                mRelativeMainHome.setVisibility(View.GONE);
                mRelativeMainMyInfo.setVisibility(View.GONE);
                mTvMainMyInfoServiceMonth.setVisibility(View.GONE);
                mIvMainMyInfoPrevious.setVisibility(View.GONE);
                mIvMainMyInfoNext.setVisibility(View.GONE);
                mTvMainLookTop.setVisibility(View.VISIBLE);

                mAppBar.setExpanded(false, false);

                //하단 네비게이션바
                mTvMainMyInfo.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));
                mTvMainHome.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));
                mTvMainLook.setTextColor(getResources().getColor(R.color.colorTextMainNavigationClicked));

                mIvMainHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_normal));
                mIvMainMyInfo.setImageDrawable(getResources().getDrawable(R.drawable.ic_consumption_normal));
                mIvMainLook.setImageDrawable(getResources().getDrawable(R.drawable.ic_look_around_clicked));

                break;
            case R.id.linear_home_service_add:
            case R.id.iv_home_service_add_after:
                mHomeFragment.setClickNone();
                Intent addIntent = new Intent(this, ServiceAddActivity.class);
                //type 1 은 새로운 서비스 추가
                addIntent.putExtra("type", 1);
                addIntent.putExtra("currency", 1);
                startActivityForResult(addIntent, SERVICE);
                break;
            case R.id.iv_main_logo:
                if (mFragmentFlag == 1) {
                    HomeFragment hf = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);
                    assert hf != null;
                    hf.scrollToTop();
                } else if (mFragmentFlag == 2) {
                    MyInfoFragment mf = (MyInfoFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);
                    assert mf != null;
                    mf.scrollToTop();
                } else if (mFragmentFlag == 3) {
                    LookFragment lf = (LookFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);
                    assert lf != null;
                    lf.scrollToTop();
                } else if (mFragmentFlag == 4) {
                    LookPopularFragment pf = (LookPopularFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);
                    assert pf != null;
                    pf.scrollToTop();
                } else if (mFragmentFlag == 5) {
                    LookRecommendFragment rf = (LookRecommendFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);
                    assert rf != null;
                    rf.scrollToTop();
                } else if (mFragmentFlag == 6) {
                    LookOnlineFragment onf = (LookOnlineFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);
                    assert onf != null;
                    onf.scrollToTop();
                } else if (mFragmentFlag == 7) {
                    LookOfflineFragment off = (LookOfflineFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);
                    assert off != null;
                    off.scrollToTop();
                }

                mAppBar.setExpanded(true);
                break;
            case R.id.iv_main_setting:
                mLoginDialog.show();
                Window window = mLoginDialog.getWindow();
                assert window != null;
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setAttributes(mWm);

                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
//                Window window2 = mLoginDialog.getWindow();
                int x = (int) (size.x * 0.9f);
                int y = (int) (size.y * 0.3f);
                window.setLayout(x, y);
                break;
            case R.id.tv_look_popular_all:
                mFragmentFlag = 4;
                mTransaction.replace(R.id.frame_main, mLookPoPularFragment).commitAllowingStateLoss();
                break;
            case R.id.tv_look_recommend_all:
                mFragmentFlag = 5;
                mTransaction.replace(R.id.frame_main, mLookRecommendFragment).commitAllowingStateLoss();
                break;
            case R.id.tv_look_online_all:
                mFragmentFlag = 6;
                mTransaction.replace(R.id.frame_main, mLookOnlineFragment).commitAllowingStateLoss();
                break;
            case R.id.tv_look_offline_all:
                mFragmentFlag = 7;
                mTransaction.replace(R.id.frame_main, mLookOfflineFragment).commitAllowingStateLoss();
                break;
        }
    }

    public void setHomeFee(int fee) {
        mTvMainHomeServiceFee.setText(myFormatter.format(fee));
    }

    public void setMyInfoFee(int fee) {
        mTvMainMyInfoServiceFee.setText(myFormatter.format(fee));
    }

    public void saveMyInfoList() {

        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<HomeItem>>() {
        }.getType();
        String json = gson.toJson(mMainMyInfoList, listType);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString("myInfoList", json);
        editor.apply();
    }

    public void addMyInfoItem(String category, int price, int total) {

        boolean flag = false;
        for (int i = 0; i < mMainMyInfoList.size(); i++) {
            MyInfoItem item = mMainMyInfoList.get(i);
            if (item.getmCategory().equals(category)) {
                item.setmPrice(item.getmPrice() + price);
                double percent = (double) item.getmPrice() / total * 100;
                item.setmPercent((int) Math.round(percent));
                flag = true;
            } else {
                double percent = (double) item.getmPrice() / total * 100;
                item.setmPercent((int) Math.round(percent));
            }
        }

        if (!flag) {
            double percent = (double) price / total * 100;
//            Log.d("로그", "total: " + total + "price: " + price + "percent : " + (int) percent);
            mMainMyInfoList.add(new MyInfoItem(category, price, (int) Math.round(percent)));
        }
    }

    public void fixMyInfoItem(String category, int price, int total) {
        for (int i = 0; i < mMainMyInfoList.size(); i++) {
            MyInfoItem item = mMainMyInfoList.get(i);
            if (item.getmCategory().equals(category)) {
                item.setmPrice(item.getmPrice() + price);
                double percent = (double) item.getmPrice() / total * 100;
                item.setmPercent((int) Math.round(percent));
            } else {
                double percent = (double) item.getmPrice() / total * 100;
                item.setmPercent((int) Math.round(percent));
            }

        }
    }

    public void removeMyInfoItem(String category, int price, int total) {

        for (int i = 0; i < mMainMyInfoList.size(); i++) {
            MyInfoItem item = mMainMyInfoList.get(i);
            //같은 이름의 카테고리가 존재 할 수 밖에 없음
            if (item.getmCategory().equals(category)) {
                item.setmPrice(item.getmPrice() - price);
                double percent = (double) item.getmPrice() / total * 100;
                item.setmPercent((int) Math.round(percent));
            } else {
                double percent = (double) item.getmPrice() / total * 100;
                item.setmPercent((int) Math.round(percent));
            }
        }

        for (int i = 0; i < mMainMyInfoList.size(); i++) {
            MyInfoItem item = mMainMyInfoList.get(i);
            if (item.getmPrice() == 0) {
                mMainMyInfoList.remove(i);
                return;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Log.d("로그", "Main onStart");

        mMainMyInfoList = new ArrayList<>();
        String myInfoList = sSharedPreferences.getString("myInfoList", "");
        Type listType = new TypeToken<ArrayList<MyInfoItem>>() {
        }.getType();

        Gson gson = new GsonBuilder().create();
        if (gson.fromJson(myInfoList, listType) != null) {
            mMainMyInfoList = gson.fromJson(myInfoList, listType);
        }


        //마지막 저장된 날짜에서 다음 시작일의 차이만큼 아이템 날짜 업데이트
        Date nowTime = Calendar.getInstance().getTime();
//        String now_date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(nowTime);
        String last_date = sSharedPreferences.getString("lastTime", "");
//        Log.d("로그", "current date: " + last_date);
        int difference = 0;
        try {
            Date lastTime = DATE_FORMAT.parse(last_date);
            long calDate = nowTime.getTime() - lastTime.getTime();
            difference = (int) (calDate / (24 * 60 * 60 * 1000));
            difference = Math.abs(difference);

        } catch (ParseException e) {
            Log.d("로그", "parsing error");
        }

//        Log.d("로그", "differ: " + difference);
        mHomeFragment.syncItems(difference);

    }

    @Override
    public void onStop() {
        super.onStop();
        //홈 월 가격 저장, 마지막 날짜 저장
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        Date lastTime = Calendar.getInstance().getTime();
        String last_date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(lastTime);
//        Log.d("로그", "last date: " + last_date);
        editor.putInt("homeFee", mHomeFee);
        editor.putString("lastTime", last_date);
        editor.putInt("myInfoFee", mMyInfoFee);
        editor.apply();
    }

    @Override
    public void getCurrencySuccess(double basePrice) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString("KRWtoUSD", String.valueOf(basePrice));
        editor.apply();
        hideProgressDialog();
    }

    @Override
    public void getCurrencyFailure(String msg) {
        hideProgressDialog();
        showCustomToast(msg);
    }

    @Override
    public void getItemsSuccess(List<Items> result) {

        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<Items>>() {
        }.getType();
        String json = gson.toJson(result, listType);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString("items", json);
        editor.apply();
        hideProgressDialog();

    }

    @Override
    public void getItemsFailure(String msg) {
        hideProgressDialog();
        showCustomToast(msg);
    }

    @Override
    public void getLookItemsSuccess(List<LookListItem> popular, List<LookListItem> recommend, List<LookListItem> online, List<LookListItem> offline) {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<LookListItem>>() {
        }.getType();
        String popularJson = gson.toJson(popular, listType);
        String recommendJson = gson.toJson(recommend, listType);
        String onlineJson = gson.toJson(online, listType);
        String offlineJson = gson.toJson(offline, listType);

        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString("popularList", popularJson);
        editor.putString("recommendList", recommendJson);
        editor.putString("onlineList", onlineJson);
        editor.putString("offlineList", offlineJson);
        editor.apply();
        hideProgressDialog();
    }

    @Override
    public void getLookItemsFailure(String msg) {
        hideProgressDialog();
        showCustomToast(msg);
    }

    @Override
    public void onBackPressed() {
        //나갈지 물어보는 다이얼로그
        mExitDialog.show();
        Window window = mExitDialog.getWindow();
        assert window != null;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setAttributes(mWm);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
//                Window window2 = mLoginDialog.getWindow();
        int x = (int) (size.x * 0.9f);
        int y = (int) (size.y * 0.3f);
        window.setLayout(x, y);
    }
}
