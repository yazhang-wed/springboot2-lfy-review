package com.lemon.practice.handler;

import com.lemon.practice.common.Result;
import com.lemon.practice.common.ResultCode;
import com.lemon.practice.common.ResultResponse;
import com.lemon.practice.exception.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    /**
     * 参数校验错误
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return ResultResponse.failure(ResultCode.INTERNAL_SERVER_ERROR, objectError.getDefaultMessage());
    }
}
