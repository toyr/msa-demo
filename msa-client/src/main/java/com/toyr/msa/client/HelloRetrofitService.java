package com.toyr.msa.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author unisk1123
 * @Description
 * @create 2020-05-17 2:33 PM
 */
public interface HelloRetrofitService {

    @GET("/say1")
    Call<String> say1(@Query("name") String name);

    @GET("/say2/{name}")
    Call<String> say2(@Path("name") String name);

    @GET("/say3")
    Call<String> say3(@Header("name") String name);
}
