package com.makeus.ChoLogBin.src.main;

import com.makeus.ChoLogBin.src.main.interfaces.MainActivityView;

class MainService {

    private MainActivityView mMainActivityView;
    private String mCode = "FRX.KRWUSD";

    //환율 생성자
    MainService(final MainActivityView mMainActivityView) {
        this.mMainActivityView = mMainActivityView;
    }


}
