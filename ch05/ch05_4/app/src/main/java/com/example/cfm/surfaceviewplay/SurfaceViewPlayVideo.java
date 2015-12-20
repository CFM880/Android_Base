package com.example.cfm.surfaceviewplay;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout.LayoutParams;

import java.io.IOException;


public class SurfaceViewPlayVideo extends AppCompatActivity implements View.OnClickListener{
    SurfaceView surfaceView;
    ImageButton play;
    ImageButton pause;
    ImageButton stop;
    MediaPlayer mPlayer;
    int postion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // 三个控件添加监听事件
        play = (ImageButton)findViewById(R.id.ib_play);
        //play.setOnClickListener(this);

        pause = (ImageButton) findViewById(R.id.ib_pause);
        pause.setOnClickListener(this);

        stop = (ImageButton)findViewById(R.id.ib_play);
        stop.setOnClickListener(this);


        mPlayer = new MediaPlayer();
        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        surfaceView.getHolder().setKeepScreenOn(true);
        surfaceView.getHolder().addCallback(new SurfaceListener());

    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()){
                case R.id.ib_play:

                    play();
                    break;
                case R.id.ib_stop:
                    if (mPlayer.isPlaying())
                    {
                        mPlayer.stop();
                    }
                    break;
                case R.id.ib_pause:
                    if (mPlayer.isPlaying()){
                        mPlayer.pause();
                    }
                    else {
                        mPlayer.start();
                    }
                    break;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void play() throws IOException{
        mPlayer.reset();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setDataSource("/storage/sdcard0/netease/cloudmusic/MV/xiao.mp4");
        mPlayer.setDisplay(surfaceView.getHolder());

        mPlayer.prepare();

        WindowManager wManager = getWindowManager();

        DisplayMetrics metrics = new DisplayMetrics();
        wManager.getDefaultDisplay().getMetrics(metrics);
        surfaceView.setLayoutParams(new LayoutParams(metrics.widthPixels,
                mPlayer.getVideoHeight() * metrics.widthPixels / mPlayer.getVideoWidth()));

        mPlayer.start();
    }

    private class SurfaceListener implements SurfaceHolder.Callback{
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (postion > 0){
                try {
                    play();
                    mPlayer.seekTo(postion);
                    postion = 0;
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

    }

    @Override
    protected void onPause() {
        if (mPlayer.isPlaying()){
            postion = mPlayer.getCurrentPosition();
            mPlayer.stop();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mPlayer.isPlaying()){
            mPlayer.stop();
        }
        mPlayer.release();
        super.onDestroy();
    }
}
