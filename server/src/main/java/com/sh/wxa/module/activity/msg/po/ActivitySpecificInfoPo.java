package com.sh.wxa.module.activity.msg.po;

import com.google.common.collect.Lists;
import com.sh.wxa.Message;
import com.sh.wxa.module.activity.entity.Activity;
import com.sh.wxa.util.TimeUtil;
import lombok.Data;

import java.util.List;

/**
 *  活动具体信息
 */
@Data
public class ActivitySpecificInfoPo extends ActivityInfoPo implements Message {
    /**
     * 已报名男生数
     */
    private int maleNum;
    /**
     * 已报名女生数
     */
    private int femaleNum;
    /**
     * 已报名用户
     */
    private List<UserInfoPo> userInfoList = Lists.newArrayList();

    public void build(Activity activity) {
        this.activityId = activity.getId();
        this.activityTime = TimeUtil.toSeconds(activity.getActivityTime());
        this.address = activity.getAddress();
        this.imageUrl = activity.getImageUrl();
        this.createUserId = activity.getCreateUserId();
        this.createUserName = activity.getCreateUserName();
        this.createUserIcon = activity.getCreateUserIcon();
        this.describe = activity.getDescribe();
        this.numLimit = activity.getNumLimit();
        this.title = activity.getTitle();
    }
}
