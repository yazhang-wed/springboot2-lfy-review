package com.lemon.practice.annotation;

import java.lang.annotation.*;

/**
 * 统一包装接口返回的值 Result
 *
 * @author LBK
 * @create 2021-12-03 12:04
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {
}

