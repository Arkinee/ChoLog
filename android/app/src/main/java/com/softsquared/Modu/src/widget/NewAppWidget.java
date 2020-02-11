package com.softsquared.Modu.src.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.softsquared.Modu.R;
import com.softsquared.Modu.src.home.models.HomeItem;
import com.softsquared.Modu.src.splash.Splash;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.softsquared.Modu.src.ApplicationClass.myFormatter;
import static com.softsquared.Modu.src.ApplicationClass.sSharedPreferences;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_modu);

        String TAG = "Modu";
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);


        ArrayList<HomeItem> homeItemList = new ArrayList<>();
        String homeList = sharedPreferences.getString("homeList", "");
        Type listType = new TypeToken<ArrayList<HomeItem>>() {
        }.getType();

        Gson gson = new GsonBuilder().create();
        if (gson.fromJson(homeList, listType) != null) {
            homeItemList = gson.fromJson(homeList, listType);
        }

        if(homeItemList.size() == 0) return;

        int index = 0;
        int min_day = homeItemList.get(0).getmDDay();
        for(int i=0; i< homeItemList.size(); i++){
            HomeItem item = homeItemList.get(i);
            if(item.getmDDay() < min_day){
                min_day = item.getmDDay();
                index = i;
            }
        }

        views.setTextViewText(R.id.tv_widget_day, "D-".concat(String.valueOf(min_day)));
        views.setTextViewText(R.id.tv_widget_price, myFormatter.format(homeItemList.get(index).getmPrice()).concat(context.getResources().getString(R.string.tv_main_won)));

        Intent intent = new Intent(context, Splash.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        views.setOnClickPendingIntent(R.id.widget_box, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d("로그", "위젯 onUpdate");
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

