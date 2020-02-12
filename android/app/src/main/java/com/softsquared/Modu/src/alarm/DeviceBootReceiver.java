package com.softsquared.Modu.src.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.softsquared.Modu.src.schedule.ScheduleReceiver;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static com.softsquared.Modu.src.ApplicationClass.sSharedPreferences;

public class DeviceBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {

            Log.d("로그", "device reboot receiver");
            //처음 앱 설치시 실행되는 시케쥴이 완료된 적이 없다면
            boolean scheduleComplete = sSharedPreferences.getBoolean("scheduleComplete", false);

            if (!scheduleComplete) {

                Log.d("로그", "first schedule not completed");

                AlarmManager dayoff = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                Calendar now = Calendar.getInstance();
                now.setTimeInMillis(System.currentTimeMillis());
                Calendar tomorrow = now;
                tomorrow.add(Calendar.DAY_OF_YEAR, 1);
                tomorrow.set(Calendar.HOUR_OF_DAY, 0);
                tomorrow.set(Calendar.MINUTE, 0);
                tomorrow.set(Calendar.SECOND, 0);
                final Intent scheduleIntent = new Intent(context, ScheduleReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1000, scheduleIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                dayoff.set(AlarmManager.RTC_WAKEUP, tomorrow.getTimeInMillis(), pendingIntent);

                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.putBoolean("schedule", true);
                editor.apply();
            }

        }
    }
}