package com.sh.wxa.module.activity.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Activity {
    /**
     * 活动id
     */
    private int id;
    /**
     * 创建者id
     */
    private String createUser;
    /**
     * 活动标题
     */
    private String title;
    /**
     * 活动地址
     */
    private String address;
    /**
     * 活动描述
     */
    private String describe;
    /**
     * 活动时间
     */
    private Date activityTime;
    /**
     * 人数限制
     */
    private int numLimit;
    /**
     * 已报名用户
     */
    private String joinUsers;
    /**
     * 创建时间
     */
    private Date date;
}
