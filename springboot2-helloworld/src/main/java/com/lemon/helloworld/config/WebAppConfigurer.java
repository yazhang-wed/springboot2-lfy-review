package com.lemon.helloworld.config;

import com.lemon.helloworld.handler.ResponseResultInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 配置类
 * @author LBK
 * @create 2021-12-03 14:36
 */
@Configuration(proxyBeanMethods = false)
public class WebAppConfigurer {
    @Bean
    public WebMvcConfigurer createWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                /*
                 * addInterceptor()：添加拦截器对象
                 * addPathPatterns()：添加拦截的路径
                 * excludePathPatterns()：添加放行的路径
                 * */
                registry.addInterceptor(new ResponseResultInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**");
            }
        };
    }
}
