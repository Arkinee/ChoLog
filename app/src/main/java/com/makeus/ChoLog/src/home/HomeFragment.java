package com.makeus.ChoLog.src.home;

import android.os.Bundle;
import android.os.Handler;
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

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<HomeItem> mHomeItemList;
    private RecyclerView mRvHome;
    private LinearLayout mLinearServiceAdd;
    private HomeAdapter mHomeAdapter;
    private String mUrl;
    NestedScrollView mScrollHome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mHomeItemList = new ArrayList<>();
        mRvHome = view.findViewById(R.id.rv_home);
        mLinearServiceAdd = view.findViewById(R.id.linear_home_service_add);
        mHomeAdapter = new HomeAdapter(getActivity(), mHomeItemList);
        mScrollHome = view.findViewById(R.id.scroll_home);

        mRvHome.setAdapter(mHomeAdapter);
        mRvHome.addItemDecoration(new RecyclerViewDecoration(50));

        mUrl = "https://52.79.123.156";
        mHomeItemList.add(new HomeItem("10월 3일", 2, "", "영상 스트리밍", "멜론 Hi-Fi스트리밍", 1, 12000, true, mUrl, mUrl));
        mHomeItemList.add(new HomeItem("10월 3일", 3, "", "영상 스트리밍", "멜론 Hi-Fi스트리밍", 1, 10000, true, mUrl, mUrl));
        mHomeItemList.add(new HomeItem("10월 3일", 5, "", "영상 스트리밍", "멜론 Hi-Fi스트리밍", 1, 13200, true, mUrl, mUrl));
        mHomeItemList.add(new HomeItem("10월 3일", 4, "", "영상 스트리밍", "멜론 Hi-Fi스트리밍", 1, 25100, true, mUrl, mUrl));

        return view;
    }

    public void scrollToTop() {
        Log.d("로그", "맨 위로");
        mScrollHome.fullScroll(ScrollView.FOCUS_UP);
    }
}
