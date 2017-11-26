package com.mobile.videoplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mobile.androidplayer.SimplePlayerController;
import com.mobile.androidplayer.VideoPlayer;
import com.mobile.videoplayer.R;
import com.mobile.videoplayer.bean.VideoInfo;


import java.util.List;

/**
 * Created by XiaoJianjun on 2017/5/21.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private Context mContext;
    private List<VideoInfo> mVideoList;

    public VideoAdapter(Context context, List<VideoInfo> videoList) {
        mContext = context;
        mVideoList = videoList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
        VideoViewHolder holder = new VideoViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        VideoInfo video = mVideoList.get(position);
        SimplePlayerController controller = new SimplePlayerController(mContext);
        holder.videoPlayer.setController(controller);
        holder.videoPlayer.setPlayerType(VideoPlayer.TYPE_NATIVE);
        controller.setTitle(video.getTitle());
        controller.setLenght(video.getLength());
        Glide.with(holder.itemView.getContext())
                .load(video.getImageUrl())
                .crossFade()
                .into(controller.imageView());
        holder.videoPlayer.setUp(video.getVideoUrl(), null);

    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{

        VideoPlayer videoPlayer;
        public VideoViewHolder(View itemView) {
            super(itemView);
            videoPlayer = (VideoPlayer) itemView.findViewById(R.id.video_player);
            // 将列表中的每个视频设置为默认16:9的比例
            ViewGroup.LayoutParams params = videoPlayer.getLayoutParams();
            params.width = itemView.getResources().getDisplayMetrics().widthPixels; // 宽度为屏幕宽度
            params.height = (int) (params.width * 9f / 16f);    // 高度为宽度的9/16
            videoPlayer.setLayoutParams(params);
        }
    }
}
