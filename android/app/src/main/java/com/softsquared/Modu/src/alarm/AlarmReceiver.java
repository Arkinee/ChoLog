package com.softsquared.Modu.src.alarm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.softsquared.Modu.R;
import com.softsquared.Modu.src.main.MainActivity;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int index = intent.getIntExtra("index", 0);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(context, MainActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Modu");

        builder.setSmallIcon(R.mipmap.ic_modu)
//                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_modu))
                .setContentTitle(intent.getStringExtra("title"))
                .setContentText("정기 구독 결제 전 알림이 왔습니다!")
                .setAutoCancel(false)
                .setContentIntent(pendingIntent);   // 푸쉬 클릭 시 메인으로

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel("Modu", "알람 채널", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);

        }

        notificationManager.notify(intent.getIntExtra("index", 0),builder.build());

    }


}
