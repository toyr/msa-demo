package com.toyr.msa.client.conf;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author unisk1123
 * @Description
 * @create 2020-05-17 2:09 PM
 */
@Configuration
public class OkHttpClientConf {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
