package com.makeus.ChoLogBin.src.home;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.makeus.ChoLogBin.R;
import com.makeus.ChoLogBin.src.RecyclerViewDecoration;
import com.makeus.ChoLogBin.src.home.adapter.HomeAdapter;
import com.makeus.ChoLogBin.src.home.models.HomeItem;
import com.makeus.ChoLogBin.src.serviceAdd.ServiceAddActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.makeus.ChoLogBin.src.ApplicationClass.sSharedPreferences;

public class HomeFragment extends Fragment {

    private ArrayList<HomeItem> mHomeItemList;
    private RecyclerView mRvHome;
    private LinearLayout mLinearServiceAdd;
    private HomeAdapter mHomeAdapter;
    private String mUrl;
    NestedScrollView mScrollHome;

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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

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
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", mHomeItemList.get(pos));
                settingIntent.putExtras(bundle);
                startActivityForResult(settingIntent, SETTING);
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
