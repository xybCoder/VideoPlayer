# VideoPlayer
支持列表，完美切换全屏、小窗口的Android视频播放器

### Features

 * 用IjkPlayer/MediaPlayer + TextureView封装，可切换IjkPlayer、MediaPlayer.
 * 支持本地和网络视频播放.
 * 完美切换小窗口、全屏，可在RecyclerView中无缝全屏.
 * 手势滑动调节播放进度、亮度、声音.
 * 支持清晰度切换.
 * 可自定义控制界面.


**在对应视频界面所在的Activity的Manifest.xml中需要添加如下配置：**
```
android:configChanges="orientation|keyboardHidden|screenSize"
```

### Demo
![](https://github.com/xybCoder/VideoPlayer/blob/master/images/image1.png)
