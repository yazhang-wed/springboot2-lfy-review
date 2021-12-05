package com.lemon.practice.controller;

import com.lemon.practice.annotation.Decrypt;
import com.lemon.practice.annotation.Encrypt;
import com.lemon.practice.annotation.ResponseWrap;
import com.lemon.practice.bean.User;
import com.lemon.practice.common.Result;
import com.lemon.practice.common.ResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author LBK
 * @create 2021-12-04 14:41
 */
@RestController
@ResponseWrap
public class UserController {

    @Encrypt
    @GetMapping("/users")
    public Result getRegister(@Valid @RequestBody User user) {
        System.out.println(user);
        return ResultResponse.success(user);
    }

    @Decrypt
    @PostMapping("/users")
    public Result postRegister(@Valid @RequestBody User user) {
        System.out.println(user);
        return ResultResponse.success(user);
    }
}
