package com.example.cfm.ch01_6;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button mConnect, mDisConnect, mClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mConnect = (Button)findViewById(R.id.btn_connect);
        mDisConnect = (Button)findViewById(R.id.btn_disconnect);
        mClear = (Button)findViewById(R.id.btn_clear);

        mConnect.setOnClickListener(mOnClickListener);
        mDisConnect.setOnClickListener(mOnClickListener);
        mClear.setOnClickListener(mOnClickListener);
        addText(getResources().getString(R.string.updateTime) + getCurrentTime() + "\r\n");
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_connect:
                    startService(new Intent(MainActivity.this, HeartService.class));
                    addText(getResources().getString(R.string.connect));
                    break;
                case R.id.btn_disconnect:
                    startService(new Intent(MainActivity.this, HeartService.class));
                    addText(getResources().getString(R.string.disconnect));
                    break;
                case R.id.btn_clear:
                    clearText();
                    break;
                default:
                    break;

            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void addText(final String str){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                TextView tv_content = (TextView)findViewById(R.id.tv_content);
                tv_content.setText(String.format("%s\r\n%s",tv_content.getText().toString(),str));
            }
        });
    }
    private void clearText(){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                TextView tv_content = (TextView)findViewById(R.id.tv_content);
                tv_content.setText("");
            }
        });
    }

    private String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }
}
