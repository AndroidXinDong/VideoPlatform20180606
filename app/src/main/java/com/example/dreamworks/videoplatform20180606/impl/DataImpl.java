package com.example.dreamworks.videoplatform20180606.impl;

import com.example.dreamworks.videoplatform20180606.beans.VPBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by DreamWorks on 2018-6-7.
 */

public interface DataImpl {
    /**
     *  主列表信息
     * @return
     */
    @GET("VideoPlateform/MainServlet")
    Call<VPBean> getMainList();
}
