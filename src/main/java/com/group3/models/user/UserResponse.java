package com.group3.models.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserResponse {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String fullName;

    private Date birthday;

    private Date vip;
}
