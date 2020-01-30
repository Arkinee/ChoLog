package com.softsquared.Modu.src.myInfo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.softsquared.Modu.R;
import com.softsquared.Modu.src.RecyclerViewDecoration;
import com.softsquared.Modu.src.home.models.HomeItem;
import com.softsquared.Modu.src.myInfo.adapter.MyInfoAdapter;
import com.softsquared.Modu.src.myInfo.models.MyInfoItem;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.softsquared.Modu.src.ApplicationClass.sSharedPreferences;

public class MyInfoFragment extends Fragment {

    private ArrayList<MyInfoItem> mMyInfoList;
    private RecyclerView mRvMyInfo;
    private MyInfoAdapter mMyInfoAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("로그", "myinfo oncreate");

        mMyInfoList = new ArrayList<>();
        String myInfoList = sSharedPreferences.getString("myInfoList", "");
        Type listType = new TypeToken<ArrayList<MyInfoItem>>() {
        }.getType();

        Gson gson = new GsonBuilder().create();
        if (gson.fromJson(myInfoList, listType) != null) {
            mMyInfoList = gson.fromJson(myInfoList, listType);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_info, container, false);
        Log.d("로그", "myinfo oncreateView");
        mRvMyInfo = view.findViewById(R.id.rv_my_info);
        mMyInfoAdapter = new MyInfoAdapter(getActivity(), mMyInfoList, this);

        mRvMyInfo.setAdapter(mMyInfoAdapter);
        mRvMyInfo.addItemDecoration(new RecyclerViewDecoration(10));

//        mMyInfoList.add(new MyInfoItem("영상 스트리밍", 12000, 100));

        return view;
    }

    public void scrollToTop() {
        Log.d("로그", "맨 위로");
        mRvMyInfo.smoothScrollToPosition(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("로그", "consumption resume");
        for (int i = 0; i < mMyInfoList.size(); i++) {
            Log.d("로그", "category: " + mMyInfoList.get(i).getmCategory());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<HomeItem>>() {
        }.getType();
        String json = gson.toJson(mMyInfoList, listType);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString("myInfoList", json);
        editor.apply();
    }


}
