package com.aliyun.svideo.snap.record;

/**
 * 作者：WangZhiQiang
 * 时间：2020/3/11
 * 邮箱：sos181@163.com
 * 描述：拍摄视频实时帧回到
 *
 * */
public interface ShootVideoOnFrame {
    void OnFrame(byte[] bytes, int width, int height);
}
