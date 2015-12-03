package com.example.cfm.ch01_1;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mainButton = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainButton = (Button) findViewById(R.id.mainButton);

        mainButton.setOnClickListener(new MainButtonListener());

    }

    class MainButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {//重写onClick方法
            // TODO Auto-generated method stub
          //  mainButton.setText("我被点击了");
            Intent intent = new Intent();//构建意图对象
            //显示方式声明Intent，直接启动SecondActivity
            intent.setClass(MainActivity.this, SecondActivity.class);//设置意图切换的目标类
            //注意putExtra（）键"Number01"与getStringExtra("Number01")保持一致
            intent.putExtra("Number01", "www.zig-cloud.com");//通过意图传递数据(键值对)
            //启动Activity
            startActivity(intent);
        }

    }
}
