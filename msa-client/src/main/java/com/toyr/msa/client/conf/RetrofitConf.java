package com.toyr.msa.client.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author unisk1123
 * @Description
 * @create 2020-05-17 2:22 PM
 */
@Configuration
public class RetrofitConf {
    @Value("${client.root-uri}")
    private String rootUri;

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(rootUri)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

    }
}
