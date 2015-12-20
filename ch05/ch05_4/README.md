使用MediaPlayer与SurfaceView播放视频
==================================
使用[VideoView][1]播放视频简单、方便，但是早期开发这更喜欢使用MediaPlayer播放视频。
但是由于[MediaPlayer][2]主要是用于播放音频，因此它没有提供图像输出界面，此时需要借助
[SurfaceView][3]显示MediaPlayer播放的图像输出。
[1]: http://developer.android.com/reference/android/widget/VideoView.html
[2]: http://developer.android.com/reference/android/media/MediaPlayer.html
[3]: http://developer.android.com/reference/android/view/SurfaceView.html
使用MediaPlayer播放视频的步骤如下
-------------------------------

- 1). 创建MediaPlayer对象，并让他加载指定的视频文件。
- 2). 在界面布局中定义SurfaceView组件，或在程序中创建SurfaceView组件。并为SurfaceView
的[SurfaceHolder][1]添加[CallBack][2]监听器
- 3). 调用MediaPlayer对象的[setDisplay(SurfaceHolder sh)][3]将所播放的视频图像输出到指定的
SurfaceView组件。
- 4). 调用MediaPlayer对象的start(), stop()和pause()方法控制视频播放

[1]: http://developer.android.com/reference/android/view/SurfaceHolder.html
[2]
[3]: http://developer.android.com/reference/android/media/MediaPlayer.html#setDisplay(android.view.SurfaceHolder)
