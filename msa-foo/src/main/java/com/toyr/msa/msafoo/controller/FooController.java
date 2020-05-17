package com.toyr.msa.msafoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

/**
 * @author unisk1123
 * @Description
 * @create 2020-05-17 1:42 AM
 */
@RestController
public class FooController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/foo")
    public String foo() {

        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return restTemplate.getForObject("http://localhost:8083/bar", String.class);
    }
}
