package com.sh.wxa.onlinemanager;

import lombok.Data;

@Data
public class Session {

    /**
     * 用户唯一标识
     */
    private volatile String openId;
    /**
     * 昵称
     */
    private String userName;
    /**
     * 城市
     */
    private String city;
    /**
     * 性别
     */
    private String sex;
    /**
     * 活跃值
     */
    private int activeValue;

}
