package com.lemon.helloworld.controller;

import com.lemon.helloworld.annotation.ResponseResult;
import com.lemon.helloworld.bean.Person;
import com.lemon.helloworld.common.Result;
import com.lemon.helloworld.common.ResultResponse;
import com.lemon.helloworld.exception.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author LBK
 * @create 2021-12-02 21:58
 */
@RestController
@ResponseResult
@RequestMapping("/")
public class HelloWorldController {

    @GetMapping("/user")
    public Person getHelloWorld() {
        Person person = new Person();
        person.setName("张三");
        person.setSex(1);
        person.setAge(18);
        return person;
    }

    @GetMapping("/ok")
    public String getOK(){
        return "OK";
    }

    @GetMapping("/result")
    public Result result(){
        return ResultResponse.success();
    }

    @GetMapping("/null")
    public String getNull(){
        String str = null;
        str.length();
        return str;
    }

    @GetMapping("/null1")
    public String getNull1() throws AuthenticationException{
        throw new AuthenticationException("自定义错误测试");
    }
}
