package com.example.cfm.ch02_03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;



public class ResultActivity extends Activity//从活动基类派生子类
{
    @Override
    public void onCreate(Bundle savedInstanceState)//重写onCreate方法
    {
        super.onCreate(savedInstanceState);//调用基类onCreate方法
        setContentView(R.layout.popup);//加载活动界面布局
        ListView listView = (ListView) findViewById(R.id.show);//在布局文件中查找ListView控件
        Intent intent = getIntent();//活动意图对象
        Bundle data = intent.getExtras();//从意图对象中获得绑定的数据
        @SuppressWarnings("unchecked")
        List<Map<String, String>> list = (List<Map<String, String>>)
                data.getSerializable("data");
        //构建简单适配器对象，做为数据和列表控件的桥梁
        SimpleAdapter adapter = new SimpleAdapter(ResultActivity.this
                , list,
                R.layout.line, new String[] { "word", "detail" }
                , new int[] {R.id.word, R.id.detail });
        // 设置适配器
        listView.setAdapter(adapter);
    }
}
