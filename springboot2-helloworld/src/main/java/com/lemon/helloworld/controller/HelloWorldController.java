package com.lemon.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LBK
 * @create 2021-12-02 21:58
 */
@RestController
@RequestMapping("/")
public class HelloWorldController {

    @GetMapping("/helloworld")
    public String getHelloWorld(){
        return "springboot2 helloWorld!";
    }
}
