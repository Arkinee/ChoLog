package com.softsquared.Modu.src.schedule;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.softsquared.Modu.src.home.models.HomeItem;
import com.softsquared.Modu.src.widget.NewAppWidget;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.softsquared.Modu.src.ApplicationClass.DATE_FORMAT;
import static com.softsquared.Modu.src.ApplicationClass.sSharedPreferences;

public class DailyWorker extends Worker {
//    private static final String WORK_RESULT = "work_result";

    public DailyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

//        int result = getInputData().getInt(WORK_RESULT, 0);

        Log.d("로그", "doWork");
        Date nowTime = Calendar.getInstance().getTime();
//        String now_date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(nowTime);
        String last_date = sSharedPreferences.getString("lastTime", "");
        Log.d("로그", "last date:" + last_date);

        int difference = 0;

        try {
            Date lastTime;
            if(last_date.equals("")){
                lastTime = nowTime;
            }else {
                lastTime = DATE_FORMAT.parse(last_date);
            }
            long calDate = nowTime.getTime() - lastTime.getTime();
            difference = (int) (calDate / (24 * 60 * 60 * 1000));
            difference = Math.abs(difference);

            Log.d("로그", "difference: " + difference);
        } catch (ParseException e) {
            Log.d("로그", "parsing error");
        }

        if(difference != 0) {

            ArrayList<HomeItem> homeItemList = new ArrayList<>();
            String homeList = sSharedPreferences.getString("homeList", "");
            Type listType = new TypeToken<ArrayList<HomeItem>>() {
            }.getType();

            Gson gson = new GsonBuilder().create();
            if (gson.fromJson(homeList, listType) != null) {
                homeItemList = gson.fromJson(homeList, listType);
            }

            // 리스트 아이템들 D day 감소 및 초기화
            for (HomeItem item : homeItemList) {

                if (item.getmDDay() - difference <= -1) {
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
                } else {
                    item.setmDDay(item.getmDDay() - difference);
                }
            }

            // 리스트 저장 후 위젯 업데이트
            saveList(homeItemList);
            syncWidget();

        }  // difference가 0이 아닐때 행해질 작업 finish

        //작업이 끝나고 마지막 시간을 저장
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        Date lastTime = Calendar.getInstance().getTime();
        String last = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(lastTime);
        editor.putString("lastTime", last);
        editor.apply();

        return Result.success();
    }

    private void saveList(ArrayList<HomeItem> homeItemList){
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<HomeItem>>() {
        }.getType();
        String json = gson.toJson(homeItemList, listType);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString("homeList", json);
        editor.apply();
    }

    private void syncWidget(){
        Intent widgetIntent = new Intent(getApplicationContext(), NewAppWidget.class);
        widgetIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] ids = AppWidgetManager.getInstance(getApplicationContext()).getAppWidgetIds(new ComponentName(getApplicationContext(), NewAppWidget.class));
        NewAppWidget myWidget = new NewAppWidget();
        myWidget.onUpdate(getApplicationContext(), AppWidgetManager.getInstance(getApplicationContext()),ids);
    }

}
