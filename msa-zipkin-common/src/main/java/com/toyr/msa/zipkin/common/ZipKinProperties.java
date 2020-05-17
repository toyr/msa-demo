package com.toyr.msa.zipkin.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author unisk1123
 * @Description
 * @create 2020-05-17 1:54 AM
 */
@Component
@ConfigurationProperties("zipkin")
public class ZipKinProperties {

    private String endpoint;
    private String service;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
