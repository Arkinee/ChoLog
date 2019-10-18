package com.makeus.ChoLog.src.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.BaseActivity;
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

    private ImageView mIvMainLogo;
    private ImageView mIvMainSetting;
    private ImageView mIvMainMyInfo;
    private ImageView mIvMainHome;
    private ImageView mIvMainLook;

    private TextView mTvMainServiceMonth;
    private TextView mTvMainServiceFee;
    private TextView mTvMainMyInfo;
    private TextView mTvMainHome;
    private TextView mTvMainLook;

    String Tag = "로그";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initialize();
        this.setFragment();

    }

    void initialize() {

        mIvMainLogo = findViewById(R.id.iv_main_logo);
        mIvMainSetting = findViewById(R.id.iv_main_setting);
        mIvMainMyInfo = findViewById(R.id.iv_main_my_info);
        mIvMainHome = findViewById(R.id.iv_main_home);
        mIvMainLook = findViewById(R.id.iv_main_look_around);

        mToolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);
        mCollapseToolBar = findViewById(R.id.collapseBar_main);

        mTvMainServiceMonth = findViewById(R.id.tv_main_service_month);
        mTvMainServiceFee = findViewById(R.id.tv_main_service_fee);
        mTvMainMyInfo = findViewById(R.id.tv_main_my_info);
        mTvMainHome = findViewById(R.id.tv_main_home);
        mTvMainLook = findViewById(R.id.tv_main_look_around);

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
                mTransaction.replace(R.id.frame_main, mMyInfoFragment).commitAllowingStateLoss();

                mTvMainMyInfo.setTextColor(getResources().getColor(R.color.colorTextMainNavigationClicked));
                mTvMainHome.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));
                mTvMainLook.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));
                break;
            case R.id.linear_main_home:
                mTransaction.replace(R.id.frame_main, mHomeFragment).commitAllowingStateLoss();

                mTvMainMyInfo.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));
                mTvMainHome.setTextColor(getResources().getColor(R.color.colorTextMainNavigationClicked));
                mTvMainLook.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));
                break;
            case R.id.linear_main_look_around:
                mTransaction.replace(R.id.frame_main, mLookFragment).commitAllowingStateLoss();

                mTvMainMyInfo.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));
                mTvMainHome.setTextColor(getResources().getColor(R.color.colorTextMainNavigationNotClicked));
                mTvMainLook.setTextColor(getResources().getColor(R.color.colorTextMainNavigationClicked));
                break;
            case R.id.linear_home_service_add:
                Intent intent = new Intent(this, ServiceAddActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_main_logo:
                HomeFragment hf = (HomeFragment)getSupportFragmentManager().findFragmentById(R.id.frame_main);
                hf.scrollToTop();
                Log.d(Tag, "로고 클릭");
                break;
        }
    }

}
