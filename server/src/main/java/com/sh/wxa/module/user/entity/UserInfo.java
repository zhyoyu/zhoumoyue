package com.sh.wxa.module.user.entity;

import lombok.Data;

@Data
public class UserInfo {

    private String openId;

    private String activityDynamic;

    private String myActivity;

    private String topicDynamic;

    private String myTopic;

    private String concernUsers;

    private String fans;
}
