package com.example.cfm.ch02_03;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    MyDatabaseHelper dbHelper;
    Button insert = null;
    Button search = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建数据库
        dbHelper = new MyDatabaseHelper(this, "myDict.db3", 1);
        insert = (Button) findViewById(R.id.insert);
        search = (Button) findViewById(R.id.search);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = ((EditText) findViewById(R.id.word)).getText().toString();
                String detail = ((EditText) findViewById(R.id.detail)).getText().toString();

                insertDate(dbHelper.getReadableDatabase(), word, detail);

                Toast.makeText(MainActivity.this, "添加单词成功", Toast.LENGTH_LONG).show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 从编辑框获取数据
                String key = ((EditText) findViewById(R.id.key)).getText()
                        .toString();
                // ִ查询数据
                Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                        "select * from dict where word like ? or detail like ?",
                        new String[] { "%" + key + "%", "%" + key + "%" });

                //构造绑定对象
                Bundle data = new Bundle();
                data.putSerializable("data", converCursorToList(cursor));
                // 构造意图对象并传递数据
                Intent intent = new Intent(MainActivity.this
                        , ResultActivity.class);
                intent.putExtras(data);
                //启动活动
                startActivity(intent);
            }
        });

    }

    protected ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
        ArrayList<Map<String, String>> result = new  ArrayList<Map<String, String>>();
        while (cursor.moveToFirst()){
            Map<String, String> map = new HashMap<String, String>();

            map.put("word", cursor.getString(1));
            map.put("detail", cursor.getString(2));
            result.add(map);
        }

        return result;
    }

    private void insertDate(SQLiteDatabase db, String word, String detail){
        db.execSQL("insert into dict values(null, ?, ?)", new String[]{word, detail});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dbHelper != null){
            dbHelper.close();
        }
    }
}