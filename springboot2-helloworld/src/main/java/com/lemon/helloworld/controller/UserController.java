package com.lemon.helloworld.controller;

import com.lemon.helloworld.annotation.NotResponseWrap;
import com.lemon.helloworld.annotation.ResponseResult;
import com.lemon.helloworld.bean.User;
import com.lemon.helloworld.common.Result;
import com.lemon.helloworld.common.ResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author LBK
 * @create 2021-12-04 14:41
 */
@RestController
@ResponseResult
public class UserController {

    @NotResponseWrap
    @GetMapping("/users")
    public Result register(@Valid @RequestBody User user) {
        return ResultResponse.success(user);
    }
}
