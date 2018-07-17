package com.sh.wxa.module.activity.entity;

import com.sh.wxa.module.activity.msg.po.ActivityPo;
import com.sh.wxa.util.TimeUtil;
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

    public ActivityPo toInfo() {
        ActivityPo info = new ActivityPo();
        info.setActivityId(this.getId());
        info.setCreateUser(this.getCreateUser());
        info.setActivityTime(TimeUtil.toSeconds(this.getActivityTime()));
        return info;
    }

    public static Activity createEntity(ActivityPo info) {
        Activity activity = new Activity();
        activity.setActivityTime(TimeUtil.secondsToDate(info.getActivityTime()));
        activity.setAddress(info.getAddress());
        activity.setCreateUser(info.getCreateUser());
        activity.setDate(new Date());
        activity.setDescribe(info.getDescribe());
        activity.setNumLimit(info.getNumLimit());
        activity.setTitle(info.getTitle());
        return activity;
    }

}
