package com.softsquared.Modu.src.home;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.softsquared.Modu.R;
import com.softsquared.Modu.src.RecyclerViewDecoration;
import com.softsquared.Modu.src.alarm.AlarmReceiver;
import com.softsquared.Modu.src.home.adapter.HomeAdapter;
import com.softsquared.Modu.src.home.models.HomeItem;
import com.softsquared.Modu.src.serviceAdd.ServiceAddActivity;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import retrofit2.http.POST;

import static android.content.Context.ALARM_SERVICE;
import static com.softsquared.Modu.src.ApplicationClass.DATE_FORMAT;
import static com.softsquared.Modu.src.ApplicationClass.sSharedPreferences;

public class HomeFragment extends Fragment {

    private ArrayList<HomeItem> mHomeItemList;
    private RecyclerView mRvHome;
    private LinearLayout mLinearServiceAdd;
    private HomeAdapter mHomeAdapter;
    private ImageView mIvServiceAddAfter;
    private NestedScrollView mScrollHome;

    AlarmManager mAlarmManager;
    PendingIntent mPendingIntent;

    private boolean mOnceFlag;  // 아이템 하단 탭 더블클릭 방지 변수
    private final int SERVICE = 1000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d("로그", "home oncreate");

        mOnceFlag = false;
        mHomeItemList = new ArrayList<>();
        String homeList = sSharedPreferences.getString("homeList", "");
        Type listType = new TypeToken<ArrayList<HomeItem>>() {
        }.getType();

        Gson gson = new GsonBuilder().create();
        if (gson.fromJson(homeList, listType) != null) {
            mHomeItemList = gson.fromJson(homeList, listType);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Log.d("로그", "home oncreate view");
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        this.setup();

        mRvHome = view.findViewById(R.id.rv_home);
        mLinearServiceAdd = view.findViewById(R.id.linear_home_service_add);
        mIvServiceAddAfter = view.findViewById(R.id.iv_home_service_add_after);

        mHomeAdapter = new HomeAdapter(getActivity(), mHomeItemList, new HomeAdapter.OnItemClickListener() {
            @Override
            public void onChangeClick(View v, int pos) {
                if (mOnceFlag) {
                    return;
                }

                if (!mHomeItemList.get(pos).getmChangeUrl().equals("")) {
                    if (URLUtil.isValidUrl(mHomeItemList.get(pos).getmChangeUrl())) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mHomeItemList.get(pos).getmChangeUrl()));
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), getString(R.string.tv_item_home_no_valid_url), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), getActivity().getString(R.string.tv_item_home_empty_url), Toast.LENGTH_SHORT).show();
                }

                mOnceFlag = true;
            }

            @Override
            public void onCancelClick(View v, int pos) {
                if (mOnceFlag) return;

                if (!mHomeItemList.get(pos).getmCancelUrl().equals("")) {
                    if (URLUtil.isValidUrl(mHomeItemList.get(pos).getmCancelUrl())) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mHomeItemList.get(pos).getmCancelUrl()));
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), getString(R.string.tv_item_home_no_valid_url), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), getActivity().getString(R.string.tv_item_home_empty_url), Toast.LENGTH_SHORT).show();
                }

                mOnceFlag = true;
            }

            @Override
            public void onSettingClick(View v, int pos) {
                if (mOnceFlag) return;

                Intent settingIntent = new Intent(getActivity(), ServiceAddActivity.class);
                settingIntent.putExtra("type", 2);
                settingIntent.putExtra("currency", mHomeItemList.get(pos).getmCurrency());
                settingIntent.putExtra("index", pos);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", mHomeItemList.get(pos));
                settingIntent.putExtras(bundle);
                Objects.requireNonNull(getActivity()).startActivityForResult(settingIntent, SERVICE);
                mOnceFlag = true;
            }


            //알람 키고 끄기
            @Override
            public void onAlarmClick(View v, int pos) {
                HomeItem item = mHomeItemList.get(pos);

                final Intent alarmIntent = new Intent(getContext(), AlarmReceiver.class);
                mAlarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                mPendingIntent = PendingIntent.getBroadcast(getContext(), pos, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                if (item.isChecked()) { // 알람 체크 되어있을 시
                    item.setChecked(false);

                    //알림 끄기
                    mAlarmManager.cancel(mPendingIntent);
                    alarmIntent.putExtra("index", pos);
                    alarmIntent.putExtra("state", "off");
                    Toast.makeText(getContext(), "알람 해제", Toast.LENGTH_SHORT).show();
//                    getActivity().sendBroadcast(alarmIntent);

                } else { // 알람 체크 안되어 있을 시
                    item.setChecked(true);
//                    Toast.makeText(getContext(), getString(R.string.tv_item_home_alarm_not), Toast.LENGTH_SHORT).show();

//                    //알림 켜기
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTimeInMillis(System.currentTimeMillis());
//                    int DDay = item.getmDDay();
//                    int alarmBeforePay = item.getmAlarm();
//
//                    if(alarmBeforePay != -1) {
//                        calendar.add(Calendar.DAY_OF_YEAR, DDay - alarmBeforePay);
//                        calendar.set(Calendar.HOUR_OF_DAY, 12);
//                        calendar.set(Calendar.MINUTE, 0);
//                        calendar.set(Calendar.SECOND, 0);
//
//                        alarmIntent.putExtra("state", "on");
//                        alarmIntent.putExtra("index", pos);
//                        alarmIntent.putExtra("title", item.getmBrand());
//                        alarmIntent.putExtra("before", item.getmAlarm());
//                        mPendingIntent = PendingIntent.getBroadcast(getContext(), pos, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//                        mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), mPendingIntent);
//                    }

                }
                mHomeAdapter.notifyDataSetChanged();
            }

        });

        mScrollHome = view.findViewById(R.id.scroll_home);

        mRvHome.setAdapter(mHomeAdapter);
        mRvHome.addItemDecoration(new RecyclerViewDecoration(50));

        return view;
    }

    public void addItem(HomeItem item) {
        mHomeItemList.add(item);
        mHomeAdapter.notifyDataSetChanged();
        this.saveList();
    }

    public void setItem(int pos, HomeItem item) {
        mHomeItemList.set(pos, item);
        mHomeAdapter.notifyDataSetChanged();
    }

    public void removeItem(int index) {
        if (mHomeItemList.size() != 0) {
            mHomeItemList.remove(index);
            mHomeAdapter.notifyDataSetChanged();
//            Log.d("로그", "삭제");
        }
    }

    public ArrayList<HomeItem> getItemList(){

        return mHomeItemList;
    }

    public void setItemList(ArrayList<HomeItem> itemList){
        mHomeItemList = itemList;
    }

    public void adapterNotify(){
        mHomeAdapter.notifyDataSetChanged();
    }

    public void saveList() {

        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<HomeItem>>() {
        }.getType();
        String json = gson.toJson(mHomeItemList, listType);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString("homeList", json);
        editor.apply();

    }

    public void setClickNone() {
        mLinearServiceAdd.setClickable(false);
        mIvServiceAddAfter.setClickable(false);
    }

    public void scrollToTop() {
//        Log.d("로그", "맨 위로");
        mScrollHome.fullScroll(ScrollView.FOCUS_UP);
    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.d("로그", "home resume");

        Activity activity = getActivity();
        if(activity != null){
            FirebaseAnalytics.getInstance(activity).setCurrentScreen(getActivity(), getClass().getSimpleName(), "HomeFragment");
        }

        mOnceFlag = false;

        if(sSharedPreferences.getBoolean("change", false)) {
            String homeList = sSharedPreferences.getString("homeList", "");
            Type listType = new TypeToken<ArrayList<HomeItem>>() {
            }.getType();

            Gson gson = new GsonBuilder().create();
            if (gson.fromJson(homeList, listType) != null) {
                mHomeItemList = gson.fromJson(homeList, listType);
            }
            adapterNotify();

            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putBoolean("change", false);
            editor.apply();
        }

        SharedPreferences.Editor editor = sSharedPreferences.edit();
        if (mHomeItemList.size() > 0) {
            editor.putBoolean("firstTime", true);
        } else {
            editor.putBoolean("firstTime", false);
        }
        editor.apply();

        if (sSharedPreferences.getBoolean("firstTime", true)) {
            mLinearServiceAdd.setVisibility(View.GONE);
            mIvServiceAddAfter.setVisibility(View.VISIBLE);
        } else {
            mLinearServiceAdd.setVisibility(View.VISIBLE);
            mIvServiceAddAfter.setVisibility(View.GONE);
        }

        mLinearServiceAdd.setClickable(true);
        mIvServiceAddAfter.setClickable(true);

    }



    @Override
    public void onStop() {
        super.onStop();
//        Log.d("로그", "home stop");
        this.saveList();
//        print();

    }

//    private void print() {
//        for (int i = 0; i < mHomeItemList.size(); i++) {
//            Log.d("로그", "Last Day: " + mHomeItemList.get(i).getmLast());
//        }
//    }

}
