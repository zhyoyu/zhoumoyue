package com.sh.wxa.module;

import com.sh.wxa.module.activity.message.ActivityListResponse;
import com.sh.wxa.module.activity.message.CreateActivityRequest;
import com.sh.wxa.onlinemanager.Session;
import com.sh.wxa.util.OkResponse;

public interface ActivityModule {

    ActivityListResponse getRandomActivityList(Session session);

    OkResponse createNewActivity(CreateActivityRequest request, Session session);

}
