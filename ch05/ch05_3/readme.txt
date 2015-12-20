VideoView 播放视频基本步骤
1). 在界面布局中定义VideoView组件， 或者在程序中创建VideoView组件
2). 调用VideoView对象的start(), stop(), pause()方法控制视频播放
   *setVideoPath(String path):加载path文件所代表的视频
   *setVideoURL(Uri uri): 加载Url所对应的视频
3). 调用Video对象的start(), stop(), pause()方法控制视频播放

实际上和VideoView一起结合使用的还有MediaController类, 他的作用是提供一个友好的界面,通过控制界面来控制视频的播放
但是用setVideoURL(Uri.parse("file:///"))

中间有E/MediaPlayer-JNI: QCMediaPlayer mediaplayer NOT present可以不用管