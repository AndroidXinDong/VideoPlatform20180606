package com.example.dreamworks.videoplatform20180606;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoActivity extends AppCompatActivity {
    private JZVideoPlayerStandard video;
    private TextView tv_title;
    private RelativeLayout main;
    private String url = null;
    private String title = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            //半透明头部状态栏，底部导航栏   布局在状态栏，导航栏下方
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        Intent intent = getIntent();
        url = intent.getStringExtra("video");
        title = intent.getStringExtra("title");
        initView();
    }

    private void initView() {
        video = findViewById(R.id.video);
        tv_title = findViewById(R.id.tv_title);
        main = findViewById(R.id.main);
        video.batteryLevel.setVisibility(View.VISIBLE);
        video.backButton.setVisibility(View.GONE);
        video.bottomProgressBar.setVisibility(View.VISIBLE);
        video.setUp(url,JZVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN ,"");
        tv_title.setText(title);
        main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        video.startVideo();

    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

}
