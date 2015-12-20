package com.example.cfm.vedioview;

import android.graphics.PixelFormat;
import android.net.Uri;
import android.widget.MediaController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.net.URL;

public class VideoViewTest extends AppCompatActivity {
    VideoView videoView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        videoView = (VideoView)findViewById(R.id.video);

        mediaController = new MediaController(this);

//        File video = new File("/mnt/sdcard/netease/cloudmusic/MV/小幸运.mp4");

        //videoView.setVideoURI(Uri.parse("file:///storage/sdcard0/netease/cloudmusic/MV/xiao.mp4"));
        videoView.setVideoPath("/storage/sdcard0/netease/cloudmusic/MV/xiao.mp4");
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.requestFocus();

    }
}
