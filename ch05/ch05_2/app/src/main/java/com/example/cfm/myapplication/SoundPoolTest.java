package com.example.cfm.myapplication;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class SoundPoolTest extends AppCompatActivity implements View.OnClickListener{
    Button bomb, shot, arrow;
    SoundPool soundPool;
    HashMap<Integer, Integer> soundMap = new HashMap<Integer, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bomb = (Button)findViewById(R.id.bomb);
        shot = (Button)findViewById(R.id.shot);
        arrow = (Button)findViewById(R.id.arrow);

        // 设置最多可容纳10个音频流,音频的品质为5
        soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM,5);

        // load 方法加载指定的音频文件，并返回所加载的视频ID
        // 使用HashMap管理这些音频流
        soundMap.put(1, soundPool.load(this, R.raw.bomb,1));
        soundMap.put(2, soundPool.load(this, R.raw.shot,1));
        soundMap.put(3, soundPool.load(this, R.raw.arrow,1));

        bomb.setOnClickListener(this);
        shot.setOnClickListener(this);
        arrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bomb:
                soundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);
                break;
            case R.id.shot:
                soundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
                break;
            case R.id.arrow:
                soundPool.play(soundMap.get(3), 1, 1, 0, 0, 1);
                break;
        }
    }
}
