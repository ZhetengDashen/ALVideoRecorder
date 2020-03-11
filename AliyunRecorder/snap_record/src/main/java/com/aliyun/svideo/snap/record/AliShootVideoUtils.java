package com.aliyun.svideo.snap.record;

import android.app.Activity;
import android.content.Context;

import com.aliyun.svideo.common.utils.FastClickUtil;
import com.aliyun.svideo.sdk.external.struct.common.VideoQuality;
import com.aliyun.svideo.sdk.external.struct.encoder.VideoCodecs;
import com.aliyun.svideo.sdk.external.struct.recorder.CameraType;
import com.aliyun.svideo.sdk.external.struct.recorder.FlashType;
import com.aliyun.svideo.sdk.external.struct.snap.AliyunSnapVideoParam;

/**
 * 作者：WangZhiQiang
 * 时间：2020/1/22
 * 邮箱：sos181@163.com
 * 描述：启动拍摄视频
 */
public class AliShootVideoUtils {
    public  static final  int REQUESTCODESHOOTVIDEO=2001;

    /**
     *
     * @param outPath  输出路径  *注意: 路径不包含文件名
     */
    public static void start(Context context, String outPath){
       start( context,new AliShotVideoConfig(),outPath);
    }

    public static void start(Context context,AliShotVideoConfig shotVideoConfig,String outPath){
        start(context,shotVideoConfig,outPath,null);
    }

    public static void start(Context context,AliShotVideoConfig shotVideoConfig,String outPath,ShootVideoOnFrame shootVideoOnFrame){

        VideoQuality videoQuality;
        switch (shotVideoConfig.getVideoQuality()){
            case AliShotVideoConfig.ConfigParameter.PROGRESS_LOW:
                videoQuality = VideoQuality.LD;
                break;
            case AliShotVideoConfig.ConfigParameter.PROGRESS_MEIDAN:
                videoQuality = VideoQuality.SD;
                break;
            case AliShotVideoConfig.ConfigParameter.PROGRESS_HIGH:
                videoQuality = VideoQuality.HD;
                break;
            case AliShotVideoConfig.ConfigParameter.PROGRESS_SUPER:
                videoQuality = VideoQuality.SSD;
                break;

            default:
                videoQuality = VideoQuality.HD;

        }

        AliyunSnapVideoParam recordParam = new AliyunSnapVideoParam.Builder()
                .setResolutionMode(shotVideoConfig.getEesolutionMode())//分辨率等级 360  480 540 720
                .setRatioMode(shotVideoConfig.eatioMode)//视频比例  3:4 1:1  16：9
                .setRecordMode(AliyunSnapVideoParam.RECORD_MODE_AUTO)//拍摄方式 自动、按下、 直接录制完
//                .setFilterList(effectDirs)//滤镜文件
//                .setBeautyLevel(80)//美颜等级
                .setBeautyStatus(false)//美容状态
                .setCameraType(CameraType.BACK)//前置摄像头
                .setFlashType(FlashType.ON)//闪光灯
                .setNeedClip(true)//分段录制
                .setMaxDuration(shotVideoConfig.getMaxTime()*1000)
                .setMinDuration(2000)//最小时长
                .setVideoQuality(videoQuality)//视频质量
//                .setGop(gop)//帧数
                .setVideoCodec(VideoCodecs.H264_HARDWARE)
                /**
                 * 裁剪参数
                 */
//            .setFrameRate(25)
//            .setCropMode(VideoDisplayMode.FILL)
                //显示分类SORT_MODE_VIDEO视频;SORT_MODE_PHOTO图片;SORT_MODE_MERGE图片和视频
                .setSortMode(AliyunSnapVideoParam.SORT_MODE_VIDEO)
                .build();



            if (!FastClickUtil.isFastClickActivity(AliyunVideoRecorder.class.getSimpleName())) {
                AliyunVideoRecorder.startRecordForResult((Activity) context, REQUESTCODESHOOTVIDEO, recordParam,outPath,shootVideoOnFrame);
            }

    }


}
