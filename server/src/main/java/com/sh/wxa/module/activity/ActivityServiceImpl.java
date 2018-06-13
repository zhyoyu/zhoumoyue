package com.sh.wxa.module.activity;

import com.sh.wxa.module.activity.entity.Activity;
import com.sh.wxa.module.activity.mapper.ActivityMapper;
import com.sh.wxa.module.activity.message.ActivityListResponse;
import com.sh.wxa.module.activity.message.pojo.ActivityInfo;
import com.sh.wxa.onlinemanager.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivityServiceImpl implements ActivityService{

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public ActivityListResponse getRandomActivityList(Session session) {

        return null;
    }

    @Override
    public void createNewActivity(Session session, ActivityInfo activityInfo) {
        Activity activity = new Activity();
        activityMapper.addActivity(activity);
    }

}
