package com.lemon.practice.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一 API 响应结果格式封装
 *
 * @author LBK
 * @create 2021-12-03 11:54
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 6308315887056661996L;

    private Integer code;
    private String message;
    private T data;

    public Result setResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        return this;
    }

    public  Result setResult(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.setData(data);
        return this;
    }
}
