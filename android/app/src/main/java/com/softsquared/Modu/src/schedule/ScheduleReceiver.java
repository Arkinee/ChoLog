package com.softsquared.Modu.src.schedule;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.softsquared.Modu.src.home.models.HomeItem;
import com.softsquared.Modu.src.main.MainActivity;
import com.softsquared.Modu.src.widget.NewAppWidget;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.softsquared.Modu.src.ApplicationClass.DATE_FORMAT;
import static com.softsquared.Modu.src.ApplicationClass.sSharedPreferences;

public class ScheduleReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("로그", "scheduleReceiver OnReceive");

        // 처음에는 일단 D day를 하루씩 감소
        ArrayList<HomeItem> homeItemList = new ArrayList<>();
        String homeList = sSharedPreferences.getString("homeList", "");
        Type listType = new TypeToken<ArrayList<HomeItem>>() {
        }.getType();

        Gson gson = new GsonBuilder().create();
        if (gson.fromJson(homeList, listType) != null) {
            homeItemList = gson.fromJson(homeList, listType);
        }

        for(HomeItem item : homeItemList){

            if(item.getmDDay() == 0){
                try {
                    Calendar cal = Calendar.getInstance();
                    Calendar calNext = Calendar.getInstance();
                    SimpleDateFormat now_format = new SimpleDateFormat("yyyy-MM-dd");
                    Date todayDate = new Date();
                    String today = now_format.format(todayDate);
                    Date nextLast = DATE_FORMAT.parse(item.getmLast());
                    Date nextFee = DATE_FORMAT.parse(item.getmLast());
                    todayDate = DATE_FORMAT.parse(today);
                    cal.setTime(nextLast);
                    calNext.setTime(nextFee);

                    if (item.getmDurationPer() == 0) {
                        cal.add(Calendar.DAY_OF_MONTH, item.getmDuration());
                        calNext.add(Calendar.DAY_OF_MONTH, item.getmDuration());
                        calNext.add(Calendar.DAY_OF_MONTH, item.getmDuration());
                    } else if (item.getmDurationPer() == 1) {
                        cal.add(Calendar.WEEK_OF_MONTH, item.getmDuration());
                        calNext.add(Calendar.WEEK_OF_MONTH, item.getmDuration());
                        calNext.add(Calendar.WEEK_OF_MONTH, item.getmDuration());
                    } else if (item.getmDurationPer() == 2) {
                        cal.add(Calendar.MONTH, item.getmDuration());
                        calNext.add(Calendar.MONTH, item.getmDuration());
                        calNext.add(Calendar.MONTH, item.getmDuration());
                    } else if (item.getmDurationPer() == 3) {
                        cal.add(Calendar.YEAR, item.getmDuration());
                        calNext.add(Calendar.YEAR, item.getmDuration());
                        calNext.add(Calendar.YEAR, item.getmDuration());
                    }

                    //마지막 결제일 초기화
                    nextLast = new Date(cal.getTimeInMillis());
                    String reset = new SimpleDateFormat("yyyy-MM-dd").format(nextLast);
                    item.setmLast(reset);

                    int calDateDays = 0;
                    //다음 결제일 까지 남은 DDay계산
                    nextFee = new Date(calNext.getTimeInMillis());
                    long calDate = nextFee.getTime() - todayDate.getTime();
                    calDateDays = (int) (calDate / (24 * 60 * 60 * 1000));
                    calDateDays = Math.abs(calDateDays);
                    item.setmDDay(calDateDays);
                } catch (ParseException e) {
                    Log.d("로그", "날짜 파싱 에러");
                }
            }else {
                item.setmDDay(item.getmDDay() - 1);
            }

            Log.d("로그", "dday :" + item.getmDDay());
        }

        for(HomeItem item : homeItemList){
            Log.d("로그", "저장 전 dday :" + item.getmDDay());
        }

        String json = gson.toJson(homeItemList, listType);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString("homeList", json);
        editor.apply();

        //widget sync
        Intent widgetIntent = new Intent(context, NewAppWidget.class);
        widgetIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] ids = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, NewAppWidget.class));
        NewAppWidget myWidget = new NewAppWidget();
        myWidget.onUpdate(context, AppWidgetManager.getInstance(context),ids);

        // 24시간마다 반복되는 work schedule 걸기
//        WorkManager workManager = WorkManager.getInstance();
//        PeriodicWorkRequest request = PeriodicWorkRequest().Builder().build();
//
//        workManager.enqueue(request);

    }
}
