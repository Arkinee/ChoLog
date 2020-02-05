package com.softsquared.Modu.src.lookAround.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.softsquared.Modu.R;
import com.softsquared.Modu.src.RecyclerViewDecoration;
import com.softsquared.Modu.src.lookAround.adapters.LookAdapter;
import com.softsquared.Modu.src.lookAround.models.LookItem;
import com.softsquared.Modu.src.lookAround.models.LookListItem;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.softsquared.Modu.src.ApplicationClass.sSharedPreferences;

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

    private LookAdapter mPopularAdapter;
    private LookAdapter mRecommendAdapter;
    private LookAdapter mOnlineAdapter;
    private LookAdapter mOfflineAdapter;

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

        mPopularAdapter = new LookAdapter(getActivity(), mLookPopularList);
        mRecommendAdapter = new LookAdapter(getActivity(), mLookRecommendList);
        mOnlineAdapter = new LookAdapter(getActivity(), mLookOnlineList);
        mOfflineAdapter = new LookAdapter(getActivity(), mLookOfflineList);

        mRvLookPopular.setAdapter(mPopularAdapter);

        mRvLookRecommend.setAdapter(mRecommendAdapter);
        mRvLookOnline.setAdapter(mOnlineAdapter);
        mRvLookOffline.setAdapter(mOfflineAdapter);

        //RecyclerView 간격 조정
        mRvLookPopular.addItemDecoration(new RecyclerViewDecoration(10));
        mRvLookRecommend.addItemDecoration(new RecyclerViewDecoration(10));
        mRvLookOnline.addItemDecoration(new RecyclerViewDecoration(10));
        mRvLookOffline.addItemDecoration(new RecyclerViewDecoration(10));

        /*더미 데이터*/
        //LookItem adobe = new LookItem("https://wkdk.me/images/f/f1/Adobe_Creative_Cloud_%EC%95%84%EC%9D%B4%EC%BD%98.png", "어도비 클라우드", "저장 클라우드", 12000);

        this.setItems("popularList", mLookPopularList);
        this.setItems("recommendList", mLookRecommendList);
        this.setItems("onlineList", mLookOnlineList);
        this.setItems("offlineList", mLookOfflineList);

        return view;
    }

    private void setItems(String key, ArrayList<LookItem> array) {

        ArrayList<LookListItem> items = new ArrayList<>();
        String itemList = sSharedPreferences.getString(key, "");
        Type listType = new TypeToken<ArrayList<LookListItem>>() {
        }.getType();

        Gson gson = new GsonBuilder().create();
        if (gson.fromJson(itemList, listType) != null) {
            items = gson.fromJson(itemList, listType);
        }

        double krwToUsd = Double.parseDouble(sSharedPreferences.getString("KRWtoUSD", "0"));

        if(items.size() == 0) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            LookListItem item = items.get(i);
            if (item.getExchangeRate().equals("원")) {
                array.add(new LookItem(item.getLogo(), item.getCompanyName(), item.getProductName(), item.getCategory(), (int) item.getPrice()));
            } else {
                array.add(new LookItem(item.getLogo(), item.getCompanyName(), item.getProductName(), item.getCategory(), (int) (item.getPrice() * krwToUsd)));
            }
        }

    }

    public void scrollToTop() {
        mScrollLook.fullScroll(ScrollView.FOCUS_UP);
    }

}
