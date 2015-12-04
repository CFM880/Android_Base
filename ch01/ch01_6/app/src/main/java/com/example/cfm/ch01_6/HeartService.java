package com.example.cfm.ch01_6;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class HeartService extends Service {
    private static final String TAG = "HeartService";
    private static final long HEARTBAET_INTERVAL = 6*1000L;
    private AlarmManager mAlarmManager;
    private PendingIntent mPendingIntent;
    public HeartService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        mPendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(Const.ACTION_HEARTDEAT),
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        Intent startIntent = new Intent(Const.ACTION_START_HEART);
        sendBroadcast(startIntent);
        long triggleAtTime = SystemClock.elapsedRealtime() + HEARTBAET_INTERVAL;
        mAlarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, triggleAtTime, HEARTBAET_INTERVAL,
                mPendingIntent);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Intent startIntent = new Intent(Const.ACTION_STOP_HEART);
        sendBroadcast(startIntent);
        mAlarmManager.cancel(mPendingIntent);
        super.onDestroy();
    }
}
