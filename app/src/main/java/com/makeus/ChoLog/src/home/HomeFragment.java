package com.makeus.ChoLog.src.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.RecyclerViewDecoration;
import com.makeus.ChoLog.src.home.adapter.HomeAdapter;
import com.makeus.ChoLog.src.home.models.HomeItem;
import com.makeus.ChoLog.src.serviceAdd.ServiceAddActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<HomeItem> mHomeItemList;
    private RecyclerView mRvHome;
    private LinearLayout mLinearServiceAdd;
    private HomeAdapter mHomeAdapter;
    private String mUrl;
    NestedScrollView mScrollHome;

    private final int SETTING = 5000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mHomeItemList = new ArrayList<>();
        mRvHome = view.findViewById(R.id.rv_home);
        mLinearServiceAdd = view.findViewById(R.id.linear_home_service_add);
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
                settingIntent.putExtra("brand", mHomeItemList.get(pos).getmBrand());
                settingIntent.putExtra("price", mHomeItemList.get(pos).getmPrice());
                settingIntent.putExtra("dday", mHomeItemList.get(pos).getmDDay());
                settingIntent.putExtra("duration", mHomeItemList.get(pos).getmDuration());
                settingIntent.putExtra("alarm", mHomeItemList.get(pos).getmAlarm());
                settingIntent.putExtra("currency", mHomeItemList.get(pos).getmCurrency());
                settingIntent.putExtra("change", mHomeItemList.get(pos).getmChangeUrl());
                settingIntent.putExtra("cancel", mHomeItemList.get(pos).getmCancelUrl());
                settingIntent.putExtra("phone", mHomeItemList.get(pos).getmPhone());
                settingIntent.putExtra("isCheck", mHomeItemList.get(pos).isChecked());
                startActivityForResult(settingIntent,SETTING);
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

    public void scrollToTop() {
        Log.d("로그", "맨 위로");
        mScrollHome.fullScroll(ScrollView.FOCUS_UP);
    }
}
