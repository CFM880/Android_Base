package com.example.cfm.ch02_4_1;

import android.content.ContentProvider;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 为content:// mms的数据改变注册监听器
        getContentResolver().registerContentObserver(
                Uri.parse("content://sms"),true,
                new SmsObserver(new Handler())
        );
    }
    private final class SmsObserver extends ContentObserver{
        // 提供自定义的ContentObserver监听器类
        public SmsObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            //查寻发送箱得短信（处于正在发送状态）
            Cursor cursor = getContentResolver().query(Uri.parse("content://sms/outbox"),
                    null,
                    null,
                    null,
                    null);
            while(cursor.moveToNext()){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("address = ").append(cursor.getString(cursor.getColumnIndex("address")));
                stringBuilder.append(";subject = ").append(cursor.getString(cursor.getColumnIndex("subject")));
                stringBuilder.append(";body = ").append(cursor.getString(cursor.getColumnIndex("body")));
                stringBuilder.append(";date = ").append(cursor.getString(cursor.getColumnIndex("date")));
                Log.d(TAG, "Has Send SMS:" +stringBuilder.toString());
            }
        }

    }
}
