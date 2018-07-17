package com.example.dreamworks.videoplatform20180606.engine;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by DreamWorks on 2018-6-7.
 */

public class Client {
    public static final long TIME_OUT = 5000;

    public static OkHttpClient create() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置超时连接
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        // 设置发送超时连接
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        // 设置接收超时连接
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        return builder.build();
    }
}
