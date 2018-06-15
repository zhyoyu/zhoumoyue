package com.sh.wxa.module.activity;

import com.sh.wxa.module.activity.msg.ActivityListResponse;
import com.sh.wxa.module.activity.msg.po.ActivityInfo;
import com.sh.wxa.onlinemanager.Session;

public interface ActivityService {

    ActivityListResponse getActivityList(Session session, int index);

    void createActivity(Session session, ActivityInfo activityInfo);

    void joinActivity(Session session, int activityId);

    void cancelActivity(Session session, int activityId);

}
