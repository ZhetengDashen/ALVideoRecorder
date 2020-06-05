package com.aliyun.alivcsolution;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.aliyun.svideo.paly.VideoPlayTools;
import com.aliyun.svideo.snap.record.AliShootVideoUtils;
import com.aliyun.svideo.snap.record.AliShotVideoConfig;
import com.aliyun.svideo.snap.record.AliyunVideoRecorder;
import com.aliyun.svideo.snap.record.ShootVideoOnFrame;

public class VideoSetingActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_seting);
        initView();

    }

    private void initView() {
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                VideoPlayTools.play(this, "/storage/emulated/0/DCIM/Camera/1587095994187.mp4");


                AliShotVideoConfig aliShotVideoConfig=new AliShotVideoConfig();
                aliShotVideoConfig.setMaxTime(10);
                AliShootVideoUtils.start(this, aliShotVideoConfig, "", new ShootVideoOnFrame() {
                    @Override
                    public void OnFrame(byte[] bytes, int width, int height, Camera.CameraInfo info) {
                        Log.e("------------","------------");
                    }
                });

                break;
        }
    }
    //回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&& data != null){
            if (requestCode == AliShootVideoUtils.REQUESTCODESHOOTVIDEO) {
                int type = data.getIntExtra(AliyunVideoRecorder.RESULT_TYPE, 0);
                if (type == AliyunVideoRecorder.RESULT_TYPE_RECORD) {
                    Log.e("KK",data.getStringExtra(AliyunVideoRecorder.OUTPUT_PATH));
                    String videoPath=    data.getStringExtra(AliyunVideoRecorder.OUTPUT_PATH);
                }
            }
        }

    }
}
