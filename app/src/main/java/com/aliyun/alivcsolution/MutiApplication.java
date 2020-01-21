/*
 * Copyright (C) 2010-2017 Alibaba Group Holding Limited.
 */

package com.aliyun.alivcsolution;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


import com.aliyun.common.httpfinal.QupaiHttpFinal;
import com.aliyun.sys.AlivcSdkCore;

/**
 * Created by Mulberry on 2018/2/24.
 */
public class MutiApplication extends Application {
    /**
     * 友盟数据统计key值
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();

    }


}
