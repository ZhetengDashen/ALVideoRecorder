# ALVideoRecorder

###### 1.开启拍摄
```

 AliShootVideoUtils.start(Content,outPath);

  //回调
 @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&& data != null){
            if (requestCode == AliShootVideoUtils.REQUESTCODESHOOTVIDEO) {
                int type = data.getIntExtra(AliyunVideoRecorder.RESULT_TYPE, 0);
               if (type == AliyunVideoRecorder.RESULT_TYPE_RECORD) {
                   Log.e("KK",data.getStringExtra(AliyunVideoRecorder.OUTPUT_PATH));
                    videoCallback(data.getStringExtra(AliyunVideoRecorder.OUTPUT_PATH));
                }
            }
        }

    }
```
###### 2.播放视频
 ```
 VideoPlayTools.play(Activity activity, String  videoPath);
```