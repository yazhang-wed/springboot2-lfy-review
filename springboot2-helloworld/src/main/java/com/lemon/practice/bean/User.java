package com.lemon.practice.bean;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @author LBK
 * @create 2021-12-04 14:38
 */
@Data
public class User {

    private Long id;

    @NotBlank
    @Length(min = 3, max = 10)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^((13[0-9])|(15[^4,\\D])|(18[0,3-9]))\\d{8}$", message = "手机号格式不正确")
    private String phone;

    @Min(value = 18)
    @Max(value = 200)
    private int age;

    @NotBlank
    @Length(min = 6, max = 12, message = "昵称长度为6到12位")
    private String nickname;
}
