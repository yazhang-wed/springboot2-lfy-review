package com.lemon.helloworld.handler;

import cn.hutool.core.bean.BeanUtil;
import com.lemon.helloworld.common.Result;
import com.lemon.helloworld.common.ResultCode;
import com.lemon.helloworld.common.ResultResponse;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author LBK
 * @create 2021-12-03 15:02
 */
@Component
public class GlobalErrorHandler extends DefaultErrorAttributes {
    /**
     * 自定义错误返回页面
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return super.resolveException(request, response, handler, ex);
    }

    /**
     * 自定义错误返回格式
     *
     * @param webRequest
     * @param options
     * @return
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        Result result = ResultResponse.failure(ResultCode.NOT_FOUND, errorAttributes.get("path"));
        return BeanUtil.beanToMap(result, true, true);
    }
}
