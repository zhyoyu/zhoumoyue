package com.sh.wxa.module.activity;

import com.sh.wxa.module.activity.msg.ActivityInfoResponse;
import com.sh.wxa.module.activity.msg.ActivityListResponse;
import com.sh.wxa.module.activity.msg.po.ActivityInfoPo;
import com.sh.wxa.onlinemanager.Session;

public interface ActivityService {

    ActivityListResponse getActivityList(Session session, int index);

    void createActivity(Session session, ActivityInfoPo activityInfo);

    void deleteActivity(Session session, int activityId);

    void joinActivity(Session session, int activityId);

    void cancelActivity(Session session, int activityId);

    ActivityInfoResponse getActivityInfo(Session session, int activityId);
}
