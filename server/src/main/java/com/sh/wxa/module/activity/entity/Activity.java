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
     * 创建者昵称
     */
    private String createUserName;
    /**
     * 创建者头像
     */
    private String createUserIcon;
    /**
     * 活动标题
     */
    private String title;
    /**
     * 活动封面图片
     */
    private String imageUrl;
    /**
     * 活动地址
     */
    private String address;
    /**
     * 活动描述
     */
    private String memo;
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
    private Date createTime;

    public ActivityInfoPo toInfo() {
        ActivityInfoPo info = new ActivityInfoPo();
        info.setActivityId(this.id);
        info.setCreateUserId(this.createUserId);
        info.setCreateUserName(this.createUserName);
        info.setCreateUserIcon(this.createUserIcon);
        info.setActivityTime(TimeUtil.getYMDHMSTimeString(this.activityTime));
        info.setAddress(this.address);
        info.setImageUrl(this.imageUrl);
        info.setMemo(this.memo);
        info.setNumLimit(this.numLimit);
        info.setTitle(this.title);
        info.setJoinUsersNum(StringUtils.parseStringList(this.joinUsers).size());
        return info;
    }

    public static Activity createEntity(ActivityInfoPo info) {
        Activity activity = new Activity();
        String activityTime = info.getActivityTime();
        activity.setActivityTime(TimeUtil.formatStr(activityTime));
        activity.setAddress(info.getAddress());
        activity.setImageUrl(info.getImageUrl());
        activity.setCreateUserId(info.getCreateUserId());
        activity.setCreateUserName(info.getCreateUserName());
        activity.setCreateUserIcon(info.getCreateUserIcon());
        activity.setCreateTime(new Date());
        activity.setMemo(info.getMemo());
        activity.setNumLimit(info.getNumLimit());
        activity.setTitle(info.getTitle());
        return activity;
    }

}
