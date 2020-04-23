package com.aliyun.svideo.paly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.aliyun.svideo.FileUtils;
import com.aliyun.svideo.snap.record.R;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

import static com.aliyun.svideo.snap.record.AliyunVideoRecorder.RESULT_TYPE;

/**
 * 作者：WangZhiQiang
 * 时间：2020/1/21
 * 邮箱：sos181@163.com
 * 描述：视频播放
 */
public class VideoPlayActivity extends Activity  {

    public static final String KEY_FILE_PATH = "videoPath";
    public static final String KEY_ACTION_TYPE = "actionType";
    String filePath = "";
    private JzvdStd jz_video;
    private ImageView back;
    public static final int ACTION_TYPE_PLAY = 1;//播放
    public static final int ACTION_TYPE_EDITOR = 2;// 确定/删除
    public static final String REQUEST_TYPE = "request_type";
    public static final int REQUEST_YES=1001;
    public static final int REQUEST_NO=1002;
    private int actionType = ACTION_TYPE_PLAY;
    private ConstraintLayout constraintLayout;
    private ImageView iv_no;
    private ImageView iv_yes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_play);

        back = (ImageView) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoPlayActivity.this.finish();
            }
        });
        constraintLayout = findViewById(R.id.cl);

        jz_video = (JzvdStd) findViewById(R.id.jz_video);
        filePath = getIntent().getStringExtra(KEY_FILE_PATH);
        jz_video.setUp(filePath
                , "", JzvdStd.NORMAL_ORIENTATION,JZMediaIjk.class);
        jz_video.startVideo();
        jz_video.fullscreenButton.setVisibility(View.GONE);
        jz_video.backButton.setVisibility(View.GONE);
        jz_video.fullscreenButton.setOnClickListener(null);
        jz_video.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        filePath = "/storage/emulated/0/com.baseeasy.baseframework/no_user/video/1579571585318.mp4";
        if (TextUtils.isEmpty(filePath)) {
            Toast.makeText(this, "视频路径错误", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        actionType = getIntent().getIntExtra(KEY_ACTION_TYPE, ACTION_TYPE_PLAY);
        if (actionType == ACTION_TYPE_PLAY) {
            constraintLayout.setVisibility(View.GONE);
        } else {
            constraintLayout.setVisibility(View.VISIBLE);
            iv_no = (ImageView) findViewById(R.id.iv_no);
            iv_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FileUtils.deleteFD(filePath);
                    Intent intent = new Intent();
                    intent.putExtra(REQUEST_TYPE, REQUEST_NO);
                    setResult(Activity.RESULT_OK, intent);
                    finish();

                }
            });
            iv_yes = (ImageView) findViewById(R.id.iv_yes);
            iv_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra(REQUEST_TYPE, REQUEST_YES);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            });
        }

//        jz_video.setMediaInterface(JZMediaIjk.class);


    }


    @Override
    protected void onDestroy() {
        Jzvd.releaseAllVideos();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



}
