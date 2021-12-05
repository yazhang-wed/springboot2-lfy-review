package com.lemon.practice.common;

/**
 * 响应结果返回封装
 *
 * @author LBK
 * @create 2021-12-03 11:57
 */
public class ResultResponse {

    private ResultResponse(){}

    /**
     * 成功只返回状态
     * @return
     */
    public static Result success() {
        return new Result().setResult(ResultCode.SUCCESS);
    }

    /**
     * 成功
     * @param data 返回数据
     * @return
     */
    public static Result success(Object data) {
        return new Result().setResult(ResultCode.SUCCESS, data);
    }

    /**
     * 失败
     * @param resultCode 失败状态码
     * @return
     */
    public static Result failure(ResultCode resultCode) {
        return new Result().setResult(resultCode);
    }

    /**
     *
     * @param resultCode 失败状态码
     * @param data 数据
     * @return
     */
    public static Result failure(ResultCode resultCode, Object data) {
        return new Result().setResult(resultCode, data);
    }
}
