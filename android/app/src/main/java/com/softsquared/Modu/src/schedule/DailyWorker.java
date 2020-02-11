package com.softsquared.Modu.src.schedule;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Calendar;

public class DailyWorker extends Worker {
    private static final String WORK_RESULT = "work_result";

    public DailyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        String TAG = "Modu";
        SharedPreferences worker = getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);



        return Result.success();
    }
}
