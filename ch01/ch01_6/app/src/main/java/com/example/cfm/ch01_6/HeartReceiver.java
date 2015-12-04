package com.example.cfm.ch01_6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by cfm on 15-12-4.
 */

public class HeartReceiver extends BroadcastReceiver{
    private static final String TAG = "HeartReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, " action " + action);
        if (Const.ACTION_START_HEART.equals(action)){
            Log.d(TAG, "Start Heart");
        } else if (Const.ACTION_HEARTDEAT.equals(action)){
            intent.setClass(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Log.d(TAG, "Heartbeat");
        } else if (Const.ACTION_STOP_HEART.equals(action)){
            Log.d(TAG, "Stop Heart");
        }
    }
}
