package com.sh.wxa.module.activity;

import com.sh.wxa.Services;
import com.sh.wxa.module.ActivityModule;
import com.sh.wxa.module.activity.message.ActivityListResponse;
import com.sh.wxa.module.activity.message.CreateActivityRequest;
import com.sh.wxa.onlinemanager.Session;
import com.sh.wxa.util.OkResponse;

public class ActivityModuleImpl implements ActivityModule {

    @Override
    public ActivityListResponse getRandomActivityList(Session session) {
        return Services.getActivityService().getRandomActivityList(session);
    }

    @Override
    public OkResponse createNewActivity(CreateActivityRequest request, Session session) {
        Services.getActivityService().createNewActivity(session, request.getActivityInfo());
        return OkResponse.OK_RESPONSE;
    }
}
