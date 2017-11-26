package com.mobile.videoplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mobile.androidplayer.VideoPlayer;
import com.mobile.androidplayer.VideoPlayerManager;
import com.mobile.videoplayer.adapter.VideoAdapter;
import com.mobile.videoplayer.bean.VideoInfo;

import java.util.ArrayList;
import java.util.List;

public class RecyclerVideoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<VideoInfo>listVideo=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_video);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i <20 ; i++) {
          listVideo.add(new VideoInfo("事例视频",0,
                  "http://a4.att.hudong.com/05/71/01300000057455120185716259013.jpg",
                  "http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4"))
          ;
        }
    }

    private void initView() {

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        VideoAdapter adapter=new VideoAdapter(this,listVideo);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                VideoPlayer  videoPlayer = holder.itemView.findViewById(R.id.video_player);
                if (videoPlayer == VideoPlayerManager.instance().getCurrentVideoPlayer()) {
                    VideoPlayerManager.instance().releaseVideoPlayer();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        VideoPlayerManager.instance().releaseVideoPlayer();
    }

    @Override
    public void onBackPressed() {
        if (VideoPlayerManager.instance().onBackPressd()) return;
        super.onBackPressed();
    }
}
