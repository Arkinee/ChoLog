package com.makeus.Modu.src.lookAround.fragments;

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
import com.makeus.Modu.R;
import com.makeus.Modu.src.RecyclerViewDecoration;
import com.makeus.Modu.src.lookAround.adapters.LookAdapter;
import com.makeus.Modu.src.lookAround.models.LookItem;
import com.makeus.Modu.src.lookAround.models.LookListItem;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.makeus.Modu.src.ApplicationClass.sSharedPreferences;

public class LookOfflineFragment extends Fragment {

    private NestedScrollView mScrollLookOffline;
    private ArrayList<LookItem> mLookOfflineAllList;
    private RecyclerView mRvLookOfflineAll;
    private LookAdapter mOfflineAllAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_look_offline, container, false);

        mScrollLookOffline = view.findViewById(R.id.scroll_look_offline_all);
        mLookOfflineAllList = new ArrayList<>();
        mRvLookOfflineAll = view.findViewById(R.id.rv_look_offline_all);
        mOfflineAllAdapter = new LookAdapter(getActivity(), mLookOfflineAllList);

        //Adapter set
        mRvLookOfflineAll.setAdapter(mOfflineAllAdapter);
        //RecyclerView 간격 조정
        mRvLookOfflineAll.addItemDecoration(new RecyclerViewDecoration(10));


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ArrayList<LookListItem> items = new ArrayList<>();
        String itemList = sSharedPreferences.getString("offlineList", "");
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
                mLookOfflineAllList.add(new LookItem(item.getLogo(), item.getCompanyName(), item.getProductName(), item.getCategory(), (int) item.getPrice()));
            } else {
                //달러
                mLookOfflineAllList.add(new LookItem(item.getLogo(), item.getCompanyName(), item.getProductName(), item.getCategory(), (int) (item.getPrice() * krwToUsd)));
            }
        }
    }

    public void scrollToTop() {
        Log.d("로그", "맨 위로");
        mScrollLookOffline.fullScroll(ScrollView.FOCUS_UP);
    }

}
