package com.aliyun.alivcsolution;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.aliyun.svideo.snap.record.AliShootVideoUtils;
import com.aliyun.svideo.snap.record.AliShotVideoConfig;
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
}
