package com.softsquared.Modu.src.schedule;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.softsquared.Modu.R;
import com.softsquared.Modu.src.main.MainActivity;

public class PayAlarmWorker extends Worker {
    private static final String WORK_RESULT = "work_result";

    public PayAlarmWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data taskData = getInputData();
        String taskDataString = taskData.getString(MainActivity.MESSAGE_STATUS);

        showNotification("WorkManager", taskDataString != null ? taskDataString : "Message has been Sent");

        Data outputData = new Data.Builder().putString(WORK_RESULT, "Jobs Finished").build();

        return Result.success(outputData);
    }

    private void showNotification(String task, String desc){

        NotificationManager manager = (NotificationManager)getApplicationContext().getSystemService((Context.NOTIFICATION_SERVICE));
        String channelId = "Modu";
        String channelName = "pay_alarm";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new
                    NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_modu);
        manager.notify(1, builder.build());
    }




}
