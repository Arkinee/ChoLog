package com.makeus.ChoLog.src.main;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.BaseActivity;
import com.makeus.ChoLog.src.dialog.loginDialog.LoginDialog;
import com.makeus.ChoLog.src.home.HomeFragment;
import com.makeus.ChoLog.src.lookAround.LookFragment;
import com.makeus.ChoLog.src.myInfo.MyInfoFragment;
import com.makeus.ChoLog.src.serviceAdd.ServiceAddActivity;

public class MainActivity extends BaseActivity {

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

    private LoginDialog mLoginDialog;
    private WindowManager.LayoutParams mWm;
    private int mFragmentFlag;
    private int mWidth;
    private  int mHeight;
    String Tag = "로그";

    AlertDialog.Builder adb;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initialize();
        this.setFragment();

    }

    void initialize() {

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

        mRelativeMainHome = findViewById(R.id.relative_main_home);
        mRelativeMainMyInfo = findViewById(R.id.relative_main_my_info);

        mFragmentFlag = 2;

        //dialog
        mLoginDialog = new LoginDialog(this);
        adb = new AlertDialog.Builder(this);
        adb.setView(R.layout.dialog_login);

//        dialog = new Dialog(this, R.style.dialog);
        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        mWidth = dm.widthPixels;
        mHeight = dm.heightPixels;

        dialog = adb.create();

        mWm = new WindowManager.LayoutParams();
        mWm.copyFrom(dialog.getWindow().getAttributes());
        mWm.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mWm.height = mHeight / 3;


    }

    public void setFragment() {

        mFragmentManager = getSupportFragmentManager();
        mHomeFragment = new HomeFragment();
        mMyInfoFragment = new MyInfoFragment();
        mLookFragment = new LookFragment();
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.frame_main, mHomeFragment).commitAllowingStateLoss();
    }

    public void onClick(View view) {
        mTransaction = mFragmentManager.beginTransaction();

        switch (view.getId()) {
            case R.id.linear_main_my_info:
                mFragmentFlag = 1;
                mTransaction.replace(R.id.frame_main, mMyInfoFragment).commitAllowingStateLoss();
                mAppBar.setExpanded(true);

                //AppBar부분 구성요소들
                mTvMainServiceMonth.setVisibility(View.VISIBLE);
                mTvMainServiceMonth.setText(getResources().getString(R.string.tv_main_my_info_month));
                mRelativeMainHome.setVisibility(View.GONE);
                mRelativeMainMyInfo.setVisibility(View.VISIBLE);
                mTvMainMyInfoServiceMonth.setVisibility(View.VISIBLE);
                mIvMainMyInfoPrevious.setVisibility(View.VISIBLE);
                mIvMainMyInfoNext.setVisibility(View.VISIBLE);

                //하단 네비게이션바
                mTvMainMyInfo.setTextColor(getResources().getColor(R.color.colorTextMainNavigationClicked));
                mTvMainHome.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));
                mTvMainLook.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));

                break;
            case R.id.linear_main_home:
                mFragmentFlag = 2;
                mTransaction.replace(R.id.frame_main, mHomeFragment).commitAllowingStateLoss();
                mAppBar.setExpanded(true);

                //AppBar부분 구성요소들
                mTvMainServiceMonth.setVisibility(View.VISIBLE);
                mTvMainServiceMonth.setText(getResources().getString(R.string.tv_serviceFee_tool));
                mRelativeMainHome.setVisibility(View.VISIBLE);
                mRelativeMainMyInfo.setVisibility(View.GONE);
                mTvMainMyInfoServiceMonth.setVisibility(View.GONE);
                mIvMainMyInfoPrevious.setVisibility(View.GONE);
                mIvMainMyInfoNext.setVisibility(View.GONE);

                //하단 네비게이션바
                mTvMainMyInfo.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));
                mTvMainHome.setTextColor(getResources().getColor(R.color.colorTextMainNavigationClicked));
                mTvMainLook.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));
                break;
            case R.id.linear_main_look_around:
                mFragmentFlag = 3;
                mTransaction.replace(R.id.frame_main, mLookFragment).commitAllowingStateLoss();
                mAppBar.setExpanded(false, false);

                //AppBar부분 구성요소들
                mTvMainServiceMonth.setVisibility(View.GONE);
                mRelativeMainHome.setVisibility(View.GONE);
                mRelativeMainMyInfo.setVisibility(View.GONE);
                mTvMainMyInfoServiceMonth.setVisibility(View.GONE);
                mIvMainMyInfoPrevious.setVisibility(View.GONE);
                mIvMainMyInfoNext.setVisibility(View.GONE);

                //하단 네비게이션바
                mTvMainMyInfo.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));
                mTvMainHome.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));
                mTvMainLook.setTextColor(getResources().getColor(R.color.colorTextMainNavigationClicked));
                break;
            case R.id.linear_home_service_add:
                Intent intent = new Intent(this, ServiceAddActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_main_logo:
                if (mFragmentFlag == 1) {
                    MyInfoFragment mf = (MyInfoFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);
                    mf.scrollToTop();
                } else if (mFragmentFlag == 2) {
                    HomeFragment hf = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);
                    hf.scrollToTop();
                } else {

                }
                mAppBar.setExpanded(true);
                Log.d(Tag, "로고 클릭");
                break;
            case R.id.iv_main_setting:
//                mLoginDialog.show();
                dialog.show();
                Window window = dialog.getWindow();
                window.setAttributes(mWm);
                break;
        }
    }

}
