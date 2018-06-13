package com.sh.wxa.module.activity.message.pojo;

import com.sh.wxa.Message;
import lombok.Data;

import java.util.List;

@Data
public class ActivityInfo implements Message {
    /**
     * 活动id
     */
    private int activityId;
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
    private int activityTime;
    /**
     * 人数限制
     */
    private int numLimit;
    /**
     * 已报名用户
     */
    private List<String> joinUsers;

}
