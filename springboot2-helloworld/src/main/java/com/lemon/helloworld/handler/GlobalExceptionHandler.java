package com.lemon.helloworld.handler;

import com.lemon.helloworld.common.Result;
import com.lemon.helloworld.common.ResultCode;
import com.lemon.helloworld.common.ResultResponse;
import com.lemon.helloworld.exception.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常统一处理
 * @author LBK
 * @create 2021-12-03 15:07
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 认证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    public Result UnNoException(AuthenticationException e) {
        return ResultResponse.failure(ResultCode.UNAUTHORIZED, e.getMessage());
    }

    /**
     * @param e 未知异常捕获
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result UnNoException(Exception e) {
        return ResultResponse.failure(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
