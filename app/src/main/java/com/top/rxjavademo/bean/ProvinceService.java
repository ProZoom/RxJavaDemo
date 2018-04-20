package com.top.rxjavademo.bean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 作者：李阳
 * 时间：2018/4/20
 * 描述：
 */
public interface ProvinceService {

    @GET("province_data.json/{name}")
    Call<ProvinceBean> getCity();

}
