package com.toyr.msa.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author unisk1123
 * @Description
 * @create 2020-05-17 1:53 PM
 */
@SpringBootApplication
public class HelloClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HelloClientApplication.class, args).close();
    }

    @Autowired
    private HelloClient helloClient;

    @Autowired
    private HelloOkHttpClient okHttpClient;

    @Autowired
    private HelloRetrofitClient helloRetrofitClient;


    @Override
    public void run(String... args) throws Exception {
        System.out.println(helloClient.say1("toyr"));
        System.out.println(helloClient.say2("toyr"));
        System.out.println(helloClient.say3("toyr"));

        System.out.println(okHttpClient.say1("ok"));
        System.out.println(okHttpClient.say2("ok"));
        System.out.println(okHttpClient.say3("ok"));


        System.out.println(helloRetrofitClient.say1("retrofit"));
        System.out.println(helloRetrofitClient.say2("retrofit"));
        System.out.println(helloRetrofitClient.say3("retrofit"));
    }
}
