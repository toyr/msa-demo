package com.toyr.msa.msahello.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author unisk1123
 * @Description
 * @create 2020-05-17 12:44 PM
 */
@RestController
public class SayController {

    @GetMapping("/say1")
    public String say1(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("/say2/{name}")
    public String say2(@PathVariable("name") String name) {
        return "hello " + name;
    }

    @GetMapping("/say3")
    public String say3(@RequestHeader("name") String name) {
        return "hello " + name;
    }
}
