package com.sh.wxa.module.user.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String openId;

    private String userName;

    private int sex;

    private String city;

    private int activeValue;
}
