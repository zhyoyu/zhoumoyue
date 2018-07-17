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
    private String nickName;
    /**
     * 头像url
     */
    private String iconUrl;
    /**
     * 城市
     */
    private String city;
    /**
     * 性别
     */
    private int sex;
    /**
     * 活跃值
     */
    private int activeValue;

}
