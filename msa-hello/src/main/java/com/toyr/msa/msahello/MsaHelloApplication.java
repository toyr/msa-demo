package com.toyr.msa.msahello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@SpringBootApplication(scanBasePackages = "com.toyr.msa")
public class MsaHelloApplication {

    @GetMapping("/hello")
//    @RequestMapping（"/hello")
    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public String hello() {
        return "Hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(MsaHelloApplication.class, args);
    }

}
