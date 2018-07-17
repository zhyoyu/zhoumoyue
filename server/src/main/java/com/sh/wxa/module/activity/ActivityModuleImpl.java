package com.sh.wxa.module.activity;

import com.sh.wxa.Services;
import com.sh.wxa.annotation.Module;
import com.sh.wxa.module.ActivityModule;
import com.sh.wxa.module.activity.msg.ActivityIdRequest;
import com.sh.wxa.module.activity.msg.ActivityInfoResponse;
import com.sh.wxa.module.activity.msg.ActivityListRequest;
import com.sh.wxa.module.activity.msg.ActivityListResponse;
import com.sh.wxa.module.activity.msg.CreateActivityRequest;
import com.sh.wxa.onlinemanager.Session;
import com.sh.wxa.util.OkResponse;

@Module
public class ActivityModuleImpl implements ActivityModule {

    @Override
    public ActivityListResponse getActivityList(ActivityListRequest request, Session session) {
        return Services.getActivityService().getActivityList(session, request.getCurPage());
    }

    @Override
    public OkResponse createActivity(CreateActivityRequest request, Session session) {
        Services.getActivityService().createActivity(session, request.getActivityInfo());
        return OkResponse.OK_RESPONSE;
    }

    @Override
    public OkResponse deleteActivity(ActivityIdRequest request, Session session) {
        return null;
    }

    @Override
    public OkResponse joinActivity(ActivityIdRequest request, Session session) {
        Services.getActivityService().joinActivity(session, request.getActivityId());
        return OkResponse.OK_RESPONSE;
    }

    @Override
    public OkResponse cancelActivity(ActivityIdRequest request, Session session) {
        Services.getActivityService().cancelActivity(session, request.getActivityId());
        return OkResponse.OK_RESPONSE;
    }

    @Override
    public ActivityInfoResponse getActivityInfo(ActivityIdRequest request, Session session) {

        return null;
    }

}
