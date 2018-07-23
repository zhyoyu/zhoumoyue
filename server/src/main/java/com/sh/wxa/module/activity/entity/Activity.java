package com.sh.wxa.module.activity.entity;

import com.sh.wxa.module.activity.msg.po.ActivityInfoPo;
import com.sh.wxa.util.StringUtils;
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
    private String createUserId;
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

    public ActivityInfoPo toInfo() {
        ActivityInfoPo info = new ActivityInfoPo();
        info.setActivityId(this.id);
        info.setCreateUserId(this.createUserId);
        info.setActivityTime(TimeUtil.toSeconds(this.activityTime));
        info.setAddress(this.address);
        info.setDescribe(this.describe);
        info.setNumLimit(this.numLimit);
        info.setTitle(this.title);
        info.setJoinUsersNum(StringUtils.parseStringList(this.joinUsers).size());
        return info;
    }

    public static Activity createEntity(ActivityInfoPo info) {
        Activity activity = new Activity();
        activity.setActivityTime(TimeUtil.secondsToDate(info.getActivityTime()));
        activity.setAddress(info.getAddress());
        activity.setCreateUserId(info.getCreateUserId());
        activity.setDate(new Date());
        activity.setDescribe(info.getDescribe());
        activity.setNumLimit(info.getNumLimit());
        activity.setTitle(info.getTitle());
        return activity;
    }

}
