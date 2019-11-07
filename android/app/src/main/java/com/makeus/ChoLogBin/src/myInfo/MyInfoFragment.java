package com.makeus.ChoLogBin.src.myInfo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.makeus.ChoLogBin.R;
import com.makeus.ChoLogBin.src.RecyclerViewDecoration;
import com.makeus.ChoLogBin.src.myInfo.adapter.MyInfoAdapter;
import com.makeus.ChoLogBin.src.myInfo.models.MyInfoItem;

import java.util.ArrayList;

public class MyInfoFragment extends Fragment {

    private ArrayList<MyInfoItem> mMyInfoList;
    private RecyclerView mRvMyInfo;
    private MyInfoAdapter mMyInfoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_info, container, false);

        mMyInfoList = new ArrayList<>();
        mRvMyInfo = view.findViewById(R.id.rv_my_info);
        mMyInfoAdapter = new MyInfoAdapter(getActivity(), mMyInfoList, this);

        mRvMyInfo.setAdapter(mMyInfoAdapter);
        mRvMyInfo.addItemDecoration(new RecyclerViewDecoration(10));

        mMyInfoList.add(new MyInfoItem("영상 스트리밍", 12000, 100));
        mMyInfoList.add(new MyInfoItem("영상 스트리밍", 12000, 100));
        mMyInfoList.add(new MyInfoItem("영상 스트리밍", 12000, 100));
        mMyInfoList.add(new MyInfoItem("영상 스트리밍", 12000, 100));
        mMyInfoList.add(new MyInfoItem("영상 스트리밍", 12000, 100));
        mMyInfoList.add(new MyInfoItem("영상 스트리밍", 12000, 90));
        mMyInfoList.add(new MyInfoItem("영상 스트리밍", 12000, 10));
        mMyInfoList.add(new MyInfoItem("영상 스트리밍", 12000, 5));
        mMyInfoList.add(new MyInfoItem("영상 스트리밍", 12000, 90));
        mMyInfoList.add(new MyInfoItem("영상 스트리밍", 12000, 60));
        mMyInfoList.add(new MyInfoItem("음악 스트리밍", 9000, 80));

        return view;
    }

    public void scrollToTop() {
        Log.d("로그", "맨 위로");
        mRvMyInfo.smoothScrollToPosition(0);
    }

}
