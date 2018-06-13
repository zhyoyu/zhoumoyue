package com.sh.wxa.module.activity;

import com.sh.wxa.module.activity.message.ActivityListResponse;
import com.sh.wxa.module.activity.message.pojo.ActivityInfo;
import com.sh.wxa.onlinemanager.Session;

public interface ActivityService {

    ActivityListResponse getRandomActivityList(Session session);

    void createNewActivity(Session session, ActivityInfo activityInfo);

}
