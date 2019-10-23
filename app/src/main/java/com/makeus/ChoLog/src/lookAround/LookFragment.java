package com.makeus.ChoLog.src.lookAround;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.detail.DetailActivity;
import com.makeus.ChoLog.src.lookAround.adapters.LookOfflineAdapter;
import com.makeus.ChoLog.src.lookAround.adapters.LookOnlineAdapter;
import com.makeus.ChoLog.src.lookAround.adapters.LookPopularAdapter;
import com.makeus.ChoLog.src.lookAround.adapters.LookRecommendAdapter;
import com.makeus.ChoLog.src.lookAround.models.LookItem;
import com.makeus.ChoLog.src.myInfo.adapter.MyInfoAdapter;

import java.util.ArrayList;

public class LookFragment extends Fragment {

    private NestedScrollView mScrollLook;

    private ArrayList<LookItem> mLookPopularList;
    private ArrayList<LookItem> mLookRecommendList;
    private ArrayList<LookItem> mLookOnlineList;
    private ArrayList<LookItem> mLookOfflineList;

    private RecyclerView mRvLookPopular;
    private RecyclerView mRvLookRecommend;
    private RecyclerView mRvLookOnline;
    private RecyclerView mRvLookOffline;

    private LookPopularAdapter mPopularAdapter;
    private LookRecommendAdapter mRecommendAdapter;
    private LookOnlineAdapter mOnlineAdapter;
    private LookOfflineAdapter mOfflineAdapter;

    CoordinatorLayout.Behavior behavior;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_look, container, false);

        mScrollLook = view.findViewById(R.id.scroll_look);
        mLookPopularList = new ArrayList<>();
        mLookRecommendList = new ArrayList<>();
        mLookOnlineList = new ArrayList<>();
        mLookOfflineList = new ArrayList<>();

        mRvLookPopular = view.findViewById(R.id.rv_look_popular);
        mRvLookRecommend = view.findViewById(R.id.rv_look_recommend);
        mRvLookOnline = view.findViewById(R.id.rv_look_online);
        mRvLookOffline = view.findViewById(R.id.rv_look_offline);

        mPopularAdapter = new LookPopularAdapter(getActivity(), mLookPopularList);
        mRecommendAdapter = new LookRecommendAdapter(getActivity(), mLookRecommendList);
        mOnlineAdapter = new LookOnlineAdapter(getActivity(), mLookOnlineList);
        mOfflineAdapter = new LookOfflineAdapter(getActivity(), mLookOfflineList);

        //mRvHome.setAdapter(mMyInfoAdapter);
        mRvLookPopular.setAdapter(mPopularAdapter);
        mRvLookRecommend.setAdapter(mRecommendAdapter);
        mRvLookOnline.setAdapter(mOnlineAdapter);
        mRvLookOffline.setAdapter(mOfflineAdapter);


        /*더미 데이터들*/
        LookItem adobe = new LookItem("https://wkdk.me/images/f/f1/Adobe_Creative_Cloud_%EC%95%84%EC%9D%B4%EC%BD%98.png", "어도비 클라우드", "저장 클라우드", 12000, 30);
        LookItem netflix = new LookItem("https://lh3.googleusercontent.com/TBRwjS_qfJCSj1m7zZB93FnpJM5fSpMA_wUlFDLxWAb45T9RmwBvQd5cWR5viJJOhkI", "넷플릭스", "영상 스트리밍", 9900, 30);
        LookItem youtube = new LookItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6pLCpPaD76S5-yf5hK0n075HVuqJT2wPC2qpoQorESh9c3GPM", "유튜브 뮤직", "음악 스트리밍", 8950, 30);
        LookItem google = new LookItem("https://www.google.com/drive/static/images/drive/logo-drive.png", "구글 클라우드", "저장 클라우드", 15000, 90);

        mLookPopularList.add(adobe);
        mLookPopularList.add(netflix);
        mLookPopularList.add(youtube);

        mLookRecommendList.add(google);
        mLookRecommendList.add(google);
        mLookRecommendList.add(google);

        mLookOnlineList.add(youtube);
        mLookOnlineList.add(netflix);
        mLookOnlineList.add(adobe);

        mLookOfflineList.add(netflix);
        mLookOfflineList.add(adobe);
        mLookOfflineList.add(youtube);
        // *****************************************

        this.setListener();

        return view;
    }

    void setListener() {

        mPopularAdapter.setOnItemClickListener(new LookPopularAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View V, int pos) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                LookItem temp = mLookPopularList.get(pos);
                intent.putExtra("name", temp.getmBrand());
                intent.putExtra("category", temp.getmCategory());
                intent.putExtra("url", temp.getmImageUrl());
                intent.putExtra("price", temp.getmPrice());
                intent.putExtra("duration", temp.getmDuration());
                startActivity(intent);
            }
        });

    }

    public void scrollToTop() {
        Log.d("로그", "맨 위로");
        mScrollLook.fullScroll(ScrollView.FOCUS_UP);
    }

}
