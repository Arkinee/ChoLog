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
import java.util.ArrayList;

import static com.makeus.Modu.src.ApplicationClass.sSharedPreferences;

public class HomeFragment extends Fragment {

    private ArrayList<HomeItem> mHomeItemList;
    private RecyclerView mRvHome;
    private LinearLayout mLinearServiceAdd;
    private HomeAdapter mHomeAdapter;
    private String mUrl;
    private ImageView mIvServiceAddAfter;
    private NestedScrollView mScrollHome;
    private final int SETTING = 5000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeItemList = new ArrayList<>();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

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
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", mHomeItemList.get(pos));
                settingIntent.putExtras(bundle);
                startActivityForResult(settingIntent, SETTING);
            }


            //알람 키고 끄기
            @Override
            public void onAlarmClick(View v, int pos) {
                HomeItem item = mHomeItemList.get(pos);
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                mHomeAdapter.notifyDataSetChanged();
            }

        });

        mScrollHome = view.findViewById(R.id.scroll_home);

        mRvHome.setAdapter(mHomeAdapter);
        mRvHome.addItemDecoration(new RecyclerViewDecoration(50));

        if (sSharedPreferences.getBoolean("firstTime", true)) {
            mLinearServiceAdd.setVisibility(View.GONE);
            mIvServiceAddAfter.setVisibility(View.VISIBLE);
        }


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


    @Override
    public void onStop() {
        super.onStop();
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<HomeItem>>() {
        }.getType();
        String json = gson.toJson(mHomeItemList, listType);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString("homeList", json);
        editor.apply();
    }
}
