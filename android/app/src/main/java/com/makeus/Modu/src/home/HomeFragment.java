package com.makeus.Modu.src.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.makeus.Modu.R;
import com.makeus.Modu.src.RecyclerViewDecoration;
import com.makeus.Modu.src.home.adapter.HomeAdapter;
import com.makeus.Modu.src.home.models.HomeItem;
import com.makeus.Modu.src.serviceAdd.ServiceAddActivity;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static com.makeus.Modu.src.ApplicationClass.DATE_FORMAT;
import static com.makeus.Modu.src.ApplicationClass.sSharedPreferences;

public class HomeFragment extends Fragment {

    private ArrayList<HomeItem> mHomeItemList;
    private RecyclerView mRvHome;
    private LinearLayout mLinearServiceAdd;
    private HomeAdapter mHomeAdapter;
    private ImageView mIvServiceAddAfter;
    private NestedScrollView mScrollHome;
    private final int SERVICE = 1000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("로그", "home oncreate");

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
        Log.d("로그", "home oncreate view");
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        this.setup();

        mRvHome = view.findViewById(R.id.rv_home);
        mLinearServiceAdd = view.findViewById(R.id.linear_home_service_add);
        mIvServiceAddAfter = view.findViewById(R.id.iv_home_service_add_after);
        mHomeAdapter = new HomeAdapter(getActivity(), mHomeItemList, new HomeAdapter.OnItemClickListener() {
            @Override
            public void onChangeClick(View v, int pos) {

            }

            @Override
            public void onCancelClick(View v, int pos) {

            }

            @Override
            public void onSettingClick(View v, int pos) {
                Intent settingIntent = new Intent(getActivity(), ServiceAddActivity.class);
                settingIntent.putExtra("type", 2);
                settingIntent.putExtra("currency", mHomeItemList.get(pos).getmCurrency());
                settingIntent.putExtra("index", pos);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", mHomeItemList.get(pos));
                settingIntent.putExtras(bundle);
                Objects.requireNonNull(getActivity()).startActivityForResult(settingIntent, SERVICE);
            }


            //알람 키고 끄기
            @Override
            public void onAlarmClick(View v, int pos) {
                HomeItem item = mHomeItemList.get(pos);
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
//                    item.setChecked(true);
                    Toast.makeText(getContext(), getString(R.string.tv_item_home_alarm_not), Toast.LENGTH_SHORT).show();

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
    }

    public void setItem(int pos, HomeItem item) {
        mHomeItemList.set(pos, item);
        mHomeAdapter.notifyDataSetChanged();
    }

    public void removeItem(int index) {
        if (mHomeItemList.size() != 0) {
            mHomeItemList.remove(index);
            mHomeAdapter.notifyDataSetChanged();
            Log.d("로그", "삭제");
        }
    }

    public void syncItems(int difference) {
        for (int i = 0; i < mHomeItemList.size(); i++) {
            HomeItem item = mHomeItemList.get(i);
            int day = item.getmDDay();
            // 결제일이 지난 경우
            if ((day - difference) <= -1) {
                item.setmDDay(day - difference + item.getmDDayFix());   // D-day 리셋
                try {
                    Date next = DATE_FORMAT.parse(item.getmLast());
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(next);
                    cal.add(Calendar.DAY_OF_MONTH, day - difference + item.getmDDayFix());
                    next = new Date(cal.getTimeInMillis());
                    String reset = new SimpleDateFormat("yyyy-MM-dd").format(next);
                    item.setmLast(reset);       //마지막 결제일 리셋
                } catch (ParseException e) {
                    Log.d("로그", "날짜 파싱 에러");
                }
            } else {
                // D-0일 까지
                item.setmDDay(day - difference);
            }
        }

        mHomeAdapter.notifyDataSetChanged();
    }

    public void scrollToTop() {
        Log.d("로그", "맨 위로");
        mScrollHome.fullScroll(ScrollView.FOCUS_UP);
    }

    public void setup() {
//        mHomeItemList = new ArrayList<>();
        String homeList = sSharedPreferences.getString("homeList", "");
        Type listType = new TypeToken<ArrayList<HomeItem>>() {
        }.getType();

        Gson gson = new GsonBuilder().create();
        if (gson.fromJson(homeList, listType) != null) {
            mHomeItemList = gson.fromJson(homeList, listType);
        }

        if (mHomeItemList.size() > 0) {
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putBoolean("firstTime", true);
            editor.apply();
        } else {
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putBoolean("firstTime", false);
            editor.apply();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("로그", "home resume");

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

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("로그", "home stop");
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<HomeItem>>() {
        }.getType();
        String json = gson.toJson(mHomeItemList, listType);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString("homeList", json);
        editor.apply();

//        for (int i = 0; i < mHomeItemList.size(); i++) {
//            Log.d("로그", i + "개 아이템");
//        }
    }

//    public void print() {
//        for (int i = 0; i < mHomeItemList.size(); i++) {
//            Log.d("로그", "아이템: " + mHomeItemList.get(i).getmBrand());
//        }
//    }

}
