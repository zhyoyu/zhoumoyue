package com.sh.wxa.module.user.msg.po;

import lombok.Data;

@Data
public class DynamicInfo {
    /**
     * 活动或话题id
     */
    private long id;
    /**
     * 变动者昵称
     */
    private String actionUserName;
    /**
     * 行动
     */
    private String actionDescribe;
}
