package com.sh.wxa.module.user.msg;

import com.sh.wxa.JsonMessage;
import com.sh.wxa.module.user.msg.po.DynamicInfo;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponse extends JsonMessage {
    /**
     * 关注
     */
    private List<String> concernUsers;
    /**
     * 粉丝
     */
    private List<String> fans;
    /**
     * 我的活动
     */
    private List<Long> myActivity;
    /**
     * 我的话题
     */
    private List<Long> myTopic;
    /**
     * 活动动态
     */
    private List<DynamicInfo> activityDynamic;
    /**
     * 话题动态
     */
    private List<DynamicInfo> topicDynamic;
}
