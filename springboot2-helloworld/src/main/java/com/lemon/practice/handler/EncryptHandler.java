package com.lemon.practice.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.practice.annotation.Encrypt;
import com.lemon.practice.common.Result;
import com.lemon.practice.properties.EncryptProperties;
import com.lemon.practice.utils.AESUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * 加密解密
 * @author LBK
 * @create 2021-12-05 13:00
 */
@EnableConfigurationProperties(EncryptProperties.class)
@ControllerAdvice
public class EncryptHandler implements ResponseBodyAdvice<Result> {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EncryptProperties encryptProperties;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    @SneakyThrows
    @Override
    public Result beforeBodyWrite(Result body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        byte[] keyBytes = encryptProperties.getKey().getBytes();
        if (Objects.nonNull(body.getData())){
            body.setMessage(AESUtils.encrypt(body.getMessage().getBytes(),keyBytes));
            body.setData(AESUtils.encrypt(objectMapper.writeValueAsBytes(body.getData()), keyBytes));
        }
        return body;
    }
}
