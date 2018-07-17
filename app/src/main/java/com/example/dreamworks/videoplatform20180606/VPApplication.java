package com.example.dreamworks.videoplatform20180606;

import android.app.Application;

import com.example.dreamworks.videoplatform20180606.engine.Client;
import com.example.dreamworks.videoplatform20180606.impl.DataImpl;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DreamWorks on 2018-6-7.
 */

public class VPApplication extends Application {
    private static final String BASE_URL1 = "http://IP地址:端口号/";// 网络地址
    public static DataImpl dataImpl;
    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit1 = new Retrofit
                .Builder()
                .baseUrl(BASE_URL1)
                .client(Client.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dataImpl = retrofit1.create(DataImpl.class);
    }
}
