package com.sh.wxa.module.user.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User{

    private String openId;

    private String nickName;

    private String iconUrl;

    private int sex;

    private String city;

    private int activeValue;

    private Date registerTime;

    private Date lastLoginTime;
}
