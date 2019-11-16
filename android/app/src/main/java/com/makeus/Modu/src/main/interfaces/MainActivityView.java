package com.makeus.Modu.src.main.interfaces;

import com.makeus.Modu.src.lookAround.models.LookListItem;
import com.makeus.Modu.src.main.models.Items;

import java.util.List;

public interface MainActivityView {

    void getCurrencySuccess(double basePrice);
    void getCurrencyFailure(String msg);

    void getItemsSuccess(List<Items> result);
    void getItemsFailure(String msg);

    void getLookItemsSuccess(List<LookListItem> popular, List<LookListItem> recommend, List<LookListItem> online, List<LookListItem> offline);
    void getLookItemsFailure(String msg);

}
