package com.example.cfm.ch01_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by cfm on 15-12-3.
 */
public class SecondActivity extends Activity {
    TextView textView = null;
    Button returnButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cecond);

        returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new SecondButtonListener());
        textView = (TextView) findViewById(R.id.showData);

        Intent intent = getIntent();

        String data = intent.getStringExtra("Number01");
        textView.setText(data);
    }
    class  SecondButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {//重写onClick方法
            // TODO Auto-generated method stub
            Intent intent = new Intent();//构建意图对象
            intent.setClass(SecondActivity.this, MainActivity.class);//设置意图切换的目标类
            startActivity(intent);//启动Activity
        }
    }

}
