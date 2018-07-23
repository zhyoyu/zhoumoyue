package com.sh.wxa.module.activity.msg.po;

import com.sh.wxa.Message;
import lombok.Data;

/**
 * 活动信息
 */
@Data
public class ActivityInfoPo implements Message {
    /**
     * 活动id
     */
    protected int activityId;
    /**
     * 创建者id
     */
    protected String createUserId;
    /**
     * 创建者用户名
     */
    protected String createUserName;
    /**
     * 创建者icon
     */
    protected String createUserIcon;
    /**
     * 活动标题
     */
    protected String title;
    /**
     * 活动地址
     */
    protected String address;
    /**
     * 活动描述
     */
    protected String describe;
    /**
     * 活动时间
     */
    protected int activityTime;
    /**
     * 人数限制
     */
    protected int numLimit;
    /**
     * 已报名人数
     */
    protected int joinUsersNum;

}
