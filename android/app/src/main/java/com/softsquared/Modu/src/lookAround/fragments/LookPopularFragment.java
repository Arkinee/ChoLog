package com.softsquared.Modu.src.lookAround.fragments;

import android.os.Bundle;
import android.util.Log;
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

public class LookPopularFragment extends Fragment {

    private NestedScrollView mScrollLookPopular;
    private ArrayList<LookItem> mLookPopularAllList;
    private RecyclerView mRvLookPopularAll;
    private LookAdapter mPopularAllAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_look_popular, container, false);

        mScrollLookPopular = view.findViewById(R.id.scroll_look_popular_all);
        mLookPopularAllList = new ArrayList<>();
        mRvLookPopularAll = view.findViewById(R.id.rv_look_popular_all);
        mPopularAllAdapter = new LookAdapter(getActivity(), mLookPopularAllList);

        //Adapter set
        mRvLookPopularAll.setAdapter(mPopularAllAdapter);
        //RecyclerView 간격 조정
        mRvLookPopularAll.addItemDecoration(new RecyclerViewDecoration(10));

        /*더미 데이터들*/
//        LookItem adobe = new LookItem("https://wkdk.me/images/f/f1/Adobe_Creative_Cloud_%EC%95%84%EC%9D%B4%EC%BD%98.png", "어도비 클라우드", "저장 클라우드", 12000);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ArrayList<LookListItem> items = new ArrayList<>();
        String itemList = sSharedPreferences.getString("popularList", "");
        Type listType = new TypeToken<ArrayList<LookListItem>>() {
        }.getType();

        Gson gson = new GsonBuilder().create();
        if (gson.fromJson(itemList, listType) != null) {
            items = gson.fromJson(itemList, listType);
        }

        double krwToUsd = Double.parseDouble(sSharedPreferences.getString("KRWtoUSD", "0"));

        for (int i = 0; i < items.size(); i++) {
            LookListItem item = items.get(i);
            if (item.getExchangeRate().equals("원")) {
                mLookPopularAllList.add(new LookItem(item.getLogo(), item.getCompanyName(), item.getProductName(), item.getCategory(), (int) item.getPrice()));
            } else {
                //달러
                mLookPopularAllList.add(new LookItem(item.getLogo(), item.getCompanyName(), item.getProductName(), item.getCategory(), (int) (item.getPrice() * krwToUsd)));
            }
        }
    }

    public void scrollToTop() {
        Log.d("로그", "맨 위로");
        mScrollLookPopular.fullScroll(ScrollView.FOCUS_UP);
    }

}
