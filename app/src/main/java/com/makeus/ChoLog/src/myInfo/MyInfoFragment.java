package com.makeus.ChoLog.src.myInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.myInfo.adapter.MyInfoAdapter;
import com.makeus.ChoLog.src.myInfo.models.MyInfoItem;

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

        mMyInfoList.add(new MyInfoItem("영상 스트리밍", 12000, 100));
        mMyInfoList.add(new MyInfoItem("영상 스트리밍", 12000, 100));
        mMyInfoList.add(new MyInfoItem("영상 스트리밍", 12000, 100));
        mMyInfoList.add(new MyInfoItem("영상 스트리밍", 12000, 100));
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

}
