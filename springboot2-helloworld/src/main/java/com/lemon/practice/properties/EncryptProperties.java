package com.lemon.practice.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author LBK
 * @create 2021-12-05 12:49
 */
@Component
@ConfigurationProperties(prefix = "lemon.encrypt")
public class EncryptProperties {

    private final static String DEFAULT_KEY = "www.1900.link";
    private String key = DEFAULT_KEY;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
