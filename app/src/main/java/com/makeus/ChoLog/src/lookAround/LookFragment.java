package com.makeus.ChoLog.src.lookAround;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.myInfo.adapter.MyInfoAdapter;

import java.util.ArrayList;

public class LookFragment extends Fragment {

    //    private ArrayList<LookItem> mMyInfoList;
    private RecyclerView mRvHome;
    private MyInfoAdapter mMyInfoAdapter;
    CoordinatorLayout.Behavior behavior;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_look, container, false);

//        mMyInfoList = new ArrayList<>();
        mRvHome = view.findViewById(R.id.rv_home);
//        mMyInfoAdapter = new MyInfoAdapter(getActivity(), mMyInfoList, this);
        //mRvHome.setAdapter(mMyInfoAdapter);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

//        if (behavior != null) return;
//
//        FrameLayout frame = getActivity().findViewById(R.id.frame_main);
//        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) frame.getLayoutParams();
//        behavior = params.getBehavior();
//        params.setBehavior(null);

    }

    @Override
    public void onDetach() {
        super.onDetach();
//        if (behavior == null) return;
//
//        FrameLayout frame = (FrameLayout) getActivity().findViewById(R.id.frame_main);
//        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) frame.getLayoutParams();
//        params.setBehavior(behavior);
//        frame.setLayoutParams(params);
//        behavior = null;
    }


}
