package com.toyr.msa.client.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author unisk1123
 * @Description
 * @create 2020-05-17 12:48 PM
 */
@Configuration
public class RestTemplateConf {

    @Value("${client.root-uri}")
    private String rootUri;

    @Value("${client.connect-timeout}")
    private int connectTimeout;

    @Value("${client.read-timeout}")
    private int readTimeout;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(rootUri)
                .setConnectTimeout(connectTimeout)
                .setReadTimeout(readTimeout)
                .build();
    }
}
