package com.lemon.practice.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.practice.annotation.NotResponseWrap;
import com.lemon.practice.annotation.ResponseWrap;
import com.lemon.practice.common.Result;
import com.lemon.practice.common.ResultResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * 是否包装拦截器拦截器
 * @author LBK
 * @create 2021-12-03 12:07
 */
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 是否请求包含了包装注解 标记，没有直接返回不需要重写返回体
     *
     * @param returnType
     * @param converterType
     * @return 此处如果返回false, 则不执行当前Advice的业务
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 判断请求接口上是否标注不包装返回参数注解
        if (returnType.hasMethodAnnotation(NotResponseWrap.class)) return false;

        // 判断请求接口，请求的类上是否标注包装返回参数注解
        if (returnType.hasMethodAnnotation(ResponseWrap.class) || returnType.getDeclaringClass().isAnnotationPresent(ResponseWrap.class)) return true;

        return false;
    }

    /**
     * 处理响应的具体业务方法
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @SneakyThrows(JsonProcessingException.class)
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Result) {
            return body;
        }
        if (body instanceof String) {
            return objectMapper.writeValueAsString(ResultResponse.success(body));
        }
        return ResultResponse.success(body);
    }
}
