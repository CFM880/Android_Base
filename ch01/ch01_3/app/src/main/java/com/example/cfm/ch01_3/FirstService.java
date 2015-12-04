package com.example.cfm.ch01_3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class FirstService extends Service {
    private static final  String TAG = "第一服务";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Service is Created");
        Log.v(TAG, "服务已被创建");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("Service is Started");
        Log.v(TAG, "服务已启动");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service is Destroyed");
        Log.v(TAG, "服务已被销毁");
    }
}
