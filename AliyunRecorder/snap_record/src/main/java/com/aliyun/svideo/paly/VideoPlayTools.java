package com.aliyun.svideo.paly;

import android.app.Activity;
import android.content.Intent;



/**
 * 作者：WangZhiQiang
 * 时间：2019/12/27
 * 邮箱：sos181@163.com
 * 描述：播放视频
 */
public class VideoPlayTools {
    public static void play(Activity activity, String  videoPath){
//        PictureSelector.create(activity).externalPictureVideo(videoPath);


        Intent intent =new Intent(activity, VideoPlayActivity.class);
        intent.putExtra(VideoPlayActivity.KEY_FILE_PATH, videoPath);
        activity.startActivity(intent);
   }
}
