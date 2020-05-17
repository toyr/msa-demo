package com.toyr.msa.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author unisk1123
 * @Description
 * @create 2020-05-17 12:55 PM
 */
@Component
public class HelloClient {

    @Autowired
    private RestTemplate restTemplate;

    public String say1(String name) {
        return restTemplate.getForObject("/say1?name={name}", String.class, name);
    }

    public String say2(String name) {
        return restTemplate.getForObject("/say2/{name}", String.class, name);
    }

    @Value("${client.root-uri}")
    private String rootUri;

    public String say3(String name) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("name", name);
        RequestEntity<Object> requestEntity = new RequestEntity<>(httpHeaders, HttpMethod.GET, URI.create(rootUri + "/say3"));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        return responseEntity.getBody();
    }
}
