package com.lemon.practice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数返回是否封装 权限高于 @ResponseResult
 * @author LBK
 * @create 2021-12-04 15:07
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface NotResponseWrap {
}
