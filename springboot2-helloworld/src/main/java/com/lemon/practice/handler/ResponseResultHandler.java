package com.lemon.practice.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.practice.annotation.NotResponseWrap;
import com.lemon.practice.annotation.ResponseResult;
import com.lemon.practice.common.Result;
import com.lemon.practice.common.ResultResponse;
import com.lemon.practice.utils.HttpContextUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.Objects;

/**
 * @author LBK
 * @create 2021-12-03 12:07
 */
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    public final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    // ObjectMapper 对象转换器
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
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();

        // 增加一个不包装的条件，配置了@NotResponseWrap注解就跳过包装。
        if (Objects.equals(returnType.getParameterType(),Response.class) || returnType.hasMethodAnnotation(NotResponseWrap.class)) return false;

        //判断请求是否有包装标志
        ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);
        if (Objects.nonNull(responseResultAnn)) return true;

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
