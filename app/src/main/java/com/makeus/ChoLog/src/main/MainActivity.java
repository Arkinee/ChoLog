package com.makeus.ChoLog.src.main;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.BaseActivity;
import com.makeus.ChoLog.src.dialog.loginDialog.LoginDialog;
import com.makeus.ChoLog.src.dialog.loginDialog.loginListener;
import com.makeus.ChoLog.src.home.HomeFragment;
import com.makeus.ChoLog.src.home.models.HomeItem;
import com.makeus.ChoLog.src.login.LoginActivity;
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
    private TextView mTvMainLookTop;

    private LoginDialog mLoginDialog;
    private WindowManager.LayoutParams mWm;
    private int mFragmentFlag;
    private int mWidth;
    private int mHeight;
    String Tag = "로그";

    private Context mContext;


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
        mWm.dimAmount = 0.8f;

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
                case 1000:
                    String mUrl = "https://52.79.123.156";
//                    HomeItem item = new HomeItem("10월 3일", 4, "", "영상 스트리밍", "멜론 Hi-Fi스트리밍", 1, 25100, true, mUrl, mUrl, "010-1111-2222", 1);
                    HomeFragment hf = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);
//                    hf.addItem(item);
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
                break;
            case R.id.linear_home_service_add:
                Intent intent = new Intent(this, ServiceAddActivity.class);
                //type 1 은 새로운 서비스 추가
                intent.putExtra("type", 1);
                intent.putExtra("currency", 1);
                startActivityForResult(intent, 1000);
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
                Log.d(Tag, "로고 클릭");
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

}
