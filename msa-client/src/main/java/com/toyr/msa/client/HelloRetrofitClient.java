package com.toyr.msa.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;

import java.io.IOException;

/**
 * @author unisk1123
 * @Description
 * @create 2020-05-17 2:36 PM
 */
@Component
public class HelloRetrofitClient {


    private HelloRetrofitService helloRetrofitService;

    @Autowired
    public HelloRetrofitClient(Retrofit retrofit) {
        helloRetrofitService = retrofit.create(HelloRetrofitService.class);
    }

    public String say1(String name) throws IOException {
        return helloRetrofitService.say1(name).execute().body();
    }

    public String say2(String name) throws IOException {
        return helloRetrofitService.say2(name).execute().body();
    }

    public String say3(String name) throws IOException {
        return helloRetrofitService.say2(name).execute().body();
    }
}
