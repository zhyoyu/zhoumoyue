package com.sh.wxa.module.user.constants;

import com.google.common.collect.Lists;
import com.sh.wxa.constants.Symbol;
import com.sh.wxa.module.activity.entity.Activity;
import com.sh.wxa.onlinemanager.Session;
import com.sh.wxa.util.EnumUtils;
import com.sh.wxa.util.IndexEnum;
import com.sh.wxa.util.StringUtils;

import java.util.List;

public enum ModifyType implements IndexEnum {
    CREATE_ACTIVITY(0, "创建了活动", "无需通知"),
    DELETE_ACTIVITY(1, "解散了活动", "通知参加人员") {
        @Override
        public String getDynamicNotice(Session session, Long activityId, Activity activity) {
            return this.getIndex() + Symbol.JIN_HAO + activityId + Symbol.JIN_HAO + activity.getCreateUserName();
        }

        @Override
        public List<String> getNoticeUser(Session session, Activity activity) {
            return StringUtils.parseStringList(activity.getJoinUsers());
        }
    },
    JOIN_ACTIVITY(2, "加入了活动", "通知参加及发起人") {
        @Override
        public String getDynamicNotice(Session session, Long activityId, Activity activity) {
            return this.getIndex() + Symbol.JIN_HAO + activityId + Symbol.JIN_HAO + session.getNickName();
        }

        @Override
        public List<String> getNoticeUser(Session session, Activity activity) {
            List<String> noticeUsers = StringUtils.parseStringList(activity.getJoinUsers());
            noticeUsers.add(activity.getCreateUserId());
            noticeUsers.remove(session.getOpenId());
            return noticeUsers;
        }
    },
    CANCEL_ACTIVITY(3, "退出了活动", "通知参加及发起人") {
        @Override
        public String getDynamicNotice(Session session, Long activityId, Activity activity) {
            return this.getIndex() + Symbol.JIN_HAO + activityId + Symbol.JIN_HAO + session.getNickName();

        }

        @Override
        public List<String> getNoticeUser(Session session, Activity activity) {
            List<String> noticeUsers = StringUtils.parseStringList(activity.getJoinUsers());
            noticeUsers.add(activity.getCreateUserId());
            return noticeUsers;
        }
    },
    JOIN_TOPIC(4, "", ""),
    CANCEL_TOPIC(5, "", ""),
    ;
    private int actionIndex;

    private String actionDescribe;

    private String memo;

    ModifyType(int actionIndex, String actionDescribe, String memo) {
        this.actionIndex = actionIndex;
        this.actionDescribe = actionDescribe;
        this.memo = memo;
    }

    @Override
    public int getIndex() {
        return this.actionIndex;
    }

    public static ModifyType valueOf(int actionIndex) {
        return EnumUtils.getByIndex(actionIndex, ModifyType.values());
    }

    public String getActionDescribe() {
        return actionDescribe;
    }

    public String getDynamicNotice(Session session, Long activityId, Activity activity) {
        return "";
    }

    public List<String> getNoticeUser(Session session, Activity activity) {
        return Lists.newArrayList();
    }
}
