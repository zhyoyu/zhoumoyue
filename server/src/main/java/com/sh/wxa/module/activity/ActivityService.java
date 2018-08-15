package com.sh.wxa.module.activity;

import com.sh.wxa.module.activity.msg.ActivityInfoResponse;
import com.sh.wxa.module.activity.msg.ActivityListRequest;
import com.sh.wxa.module.activity.msg.ActivityListResponse;
import com.sh.wxa.module.activity.msg.po.ActivityInfoPo;
import com.sh.wxa.onlinemanager.Session;

public interface ActivityService {

    ActivityListResponse findActivityList(Session session, ActivityListRequest request);

    void createActivity(Session session, ActivityInfoPo activityInfo);

    void deleteActivity(Session session, Long activityId);

    void joinActivity(Session session, Long activityId);

    void cancelActivity(Session session, Long activityId);

    ActivityInfoResponse getActivityInfo(Session session, Long activityId);
}
