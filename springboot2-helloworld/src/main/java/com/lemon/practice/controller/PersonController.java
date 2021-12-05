package com.lemon.practice.controller;

import com.lemon.practice.annotation.ResponseResult;
import com.lemon.practice.bean.Person;
import com.lemon.practice.common.Result;
import com.lemon.practice.common.ResultResponse;
import com.lemon.practice.exception.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


/**
 * @author LBK
 * @create 2021-12-02 21:58
 */
@RestController
@RequestMapping("/")
public class PersonController {

    /**
     * javabean参数自动封装模板
     * @return
     */
    @ResponseResult
    @GetMapping("/person")
    public Person getHelloWorld() {
        Person person = new Person();
        person.setName("张三");
        person.setSex(1);
        person.setAge(18);
        return person;
    }

    /**
     * 测试放回字符串参数返回
     * @return
     */
    @ResponseResult
    @GetMapping("/ok")
    public String getOK(){
        return "OK";
    }

    /**
     * 测试模板参数
     * @return
     */
    @GetMapping("/result")
    public Result result(HttpServletResponse response){
        return ResultResponse.success();
    }

    /**
     * 空指针错误测试
     * @return
     */
    @GetMapping("/null")
    public String getNull(){
        String str = null;
        str.length();
        return str;
    }

    /**
     * 自定义错误测试
     * @return
     * @throws AuthenticationException
     */
    @GetMapping("/my_null")
    public String getNull1() throws AuthenticationException{
        throw new AuthenticationException("自定义错误测试");
    }
}
