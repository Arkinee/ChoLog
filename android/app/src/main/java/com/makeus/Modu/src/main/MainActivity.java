package com.makeus.Modu.src.main;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.makeus.Modu.R;
import com.makeus.Modu.src.BaseActivity;
import com.makeus.Modu.src.dialog.loginDialog.LoginDialog;
import com.makeus.Modu.src.dialog.loginDialog.loginListener;
import com.makeus.Modu.src.home.HomeFragment;
import com.makeus.Modu.src.home.models.HomeItem;
import com.makeus.Modu.src.login.LoginActivity;
import com.makeus.Modu.src.lookAround.LookFragment;
import com.makeus.Modu.src.main.interfaces.MainActivityView;
import com.makeus.Modu.src.myInfo.MyInfoFragment;
import com.makeus.Modu.src.serviceAdd.ServiceAddActivity;

import static com.makeus.Modu.src.ApplicationClass.myFormatter;
import static com.makeus.Modu.src.ApplicationClass.sSharedPreferences;

public class MainActivity extends BaseActivity implements MainActivityView {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private HomeFragment mHomeFragment;
    private MyInfoFragment mMyInfoFragment;
    private LookFragment mLookFragment;

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

    private TextView mTvMainServiceMonth;
    private TextView mTvMainHomeServiceFee;
    private TextView mTvMainMyInfoServiceMonth;
    private TextView mTvMainMyInfoServiceFee;
    private TextView mTvMainMyInfo;
    private TextView mTvMainHome;
    private TextView mTvMainLook;
    private TextView mTvMainLookTop;

    private LoginDialog mLoginDialog;
    private WindowManager.LayoutParams mWm;
    private int mFragmentFlag;
    private String TAG = "로그";
    private Context mContext;
    private int mPrice;
    private double mDollar;

    private final int SERVICE = 1000;
    private final int LOGIN = 2000;

    private int mHomeFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initialize();
        this.setFragment();
        this.setDialog();

    }

    void initialize() {

        mContext = this;
        mHomeFee = sSharedPreferences.getInt("homeFee", 0);

        mToolbar = findViewById(R.id.toolbar_main);
        mAppBar = findViewById(R.id.appbar_main);
        setSupportActionBar(mToolbar);
        mCollapseToolBar = findViewById(R.id.collapseBar_main);

        mIvMainMyInfo = findViewById(R.id.iv_main_my_info);
        mIvMainHome = findViewById(R.id.iv_main_home);
        mIvMainLook = findViewById(R.id.iv_main_look_around);
        mIvMainMyInfoPrevious = findViewById(R.id.iv_main_my_info_previous);
        mIvMainMyInfoNext = findViewById(R.id.iv_main_my_info_next);

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
        //Home 화면 최상단 가격
        mTvMainHomeServiceFee.setText(myFormatter.format(mHomeFee));

    }

    public void setFragment() {

        mFragmentManager = getSupportFragmentManager();
        mHomeFragment = new HomeFragment();
        mMyInfoFragment = new MyInfoFragment();
        mLookFragment = new LookFragment();
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.frame_main, mHomeFragment).commitAllowingStateLoss();
    }

    public void setDialog() {

        mLoginDialog = new LoginDialog(this);

        mWm = new WindowManager.LayoutParams();
        mWm.copyFrom(mLoginDialog.getWindow().getAttributes());

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SERVICE:
                    Intent homeIntent = data;
                    String name = homeIntent.getExtras().getString("name");
                    String category = homeIntent.getExtras().getString("category");
                    int currency = homeIntent.getExtras().getInt("currency");
                    int price = homeIntent.getExtras().getInt("price");
                    int duration = homeIntent.getExtras().getInt("duration");

                    String year = String.valueOf(homeIntent.getExtras().getInt("year"));
                    String month = String.valueOf(homeIntent.getExtras().getInt("month") + 1);
                    int day_int = homeIntent.getExtras().getInt("day");
                    String day;
                    if (day_int < 10) {
                        day = "0".concat(String.valueOf(day_int));
                    } else {
                        day = String.valueOf(day_int);
                    }
                    Log.d("로그", "main day:" + day);
                    String hyphen = this.getString(R.string.tv_item_home_hyphen);
                    String last = year.concat(hyphen).concat(month).concat(hyphen).concat(day);
                    int durationPer = homeIntent.getExtras().getInt("durationPer");
                    int alarm = homeIntent.getExtras().getInt("alarm");
                    int alarmPer = homeIntent.getExtras().getInt("alarmPer");
                    String extra = homeIntent.getExtras().getString("extra");
                    String changeUrl = homeIntent.getExtras().getString("change");
                    String cancelUrl = homeIntent.getExtras().getString("cancel");
                    String phone = homeIntent.getExtras().getString("phone");

                    mHomeFee += price;
                    setHomeFee(mHomeFee);

                    String melon = "https://cdnimg.melon.co.kr/resource/mobile40/cds/common/image/mobile_apple_180x180.png";
                    HomeItem item = new HomeItem(name, category, price, currency, melon, last, duration, durationPer, alarm, alarmPer, extra, changeUrl, cancelUrl, phone, true);
                    HomeFragment hf = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);
                    hf.addItem(item);
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
                mTvMainServiceMonth.setText(getResources().getString(R.string.tv_serviceFee_tool));
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
                mTvMainServiceMonth.setVisibility(View.VISIBLE);
                mTvMainServiceMonth.setVisibility(View.GONE);
                mRelativeMainHome.setVisibility(View.GONE);
                mRelativeMainMyInfo.setVisibility(View.VISIBLE);
                mTvMainMyInfoServiceMonth.setVisibility(View.VISIBLE);
                mIvMainMyInfoPrevious.setVisibility(View.VISIBLE);
                mIvMainMyInfoNext.setVisibility(View.VISIBLE);
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
                Intent addIntent = new Intent(this, ServiceAddActivity.class);
                //type 1 은 새로운 서비스 추가
                addIntent.putExtra("type", 1);
                addIntent.putExtra("currency", 1);
                startActivityForResult(addIntent, SERVICE);
                break;
            case R.id.iv_main_logo:
                if (mFragmentFlag == 1) {
                    HomeFragment hf = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);
                    hf.scrollToTop();
                } else if (mFragmentFlag == 2) {
                    MyInfoFragment mf = (MyInfoFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);
                    mf.scrollToTop();
                } else {
                    LookFragment lf = (LookFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);
                    lf.scrollToTop();
                }
                mAppBar.setExpanded(true);
                break;
            case R.id.iv_main_setting:
                mLoginDialog.show();
                Window window = mLoginDialog.getWindow();
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
        }
    }

    public void setHomeFee(int fee) {
        mTvMainHomeServiceFee.setText(myFormatter.format(fee));
    }


    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putInt("homeFee", mHomeFee);
        editor.apply();
    }
}
