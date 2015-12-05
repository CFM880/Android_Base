package com.example.cfm.ch02_01;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;
    TextView tv_note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_note = (TextView)findViewById(R.id.tv_note);
        preferences = getSharedPreferences("count",MODE_WORLD_READABLE);

        int count = preferences.getInt("count",0);

        Toast.makeText(this, "程序已使用了" + count+ "次", Toast.LENGTH_LONG).show();
        tv_note.setText("程序已使用了" + count+ "次");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("count", ++ count);

        editor.commit();
    }
}
