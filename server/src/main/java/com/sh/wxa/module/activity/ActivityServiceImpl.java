package com.sh.wxa.module.activity;

import com.sh.wxa.module.activity.entity.Activity;
import com.sh.wxa.module.activity.mapper.ActivityMapper;
import com.sh.wxa.module.activity.msg.ActivityListResponse;
import com.sh.wxa.module.activity.msg.po.ActivityInfo;
import com.sh.wxa.onlinemanager.Session;
import com.sh.wxa.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public ActivityListResponse getActivityList(Session session, int curPage) {
        List<Activity> activityList = activityMapper.findActivityList(curPage, PageUtil.DEFAULT_PAGE_SIZE);
        ActivityListResponse resp = new ActivityListResponse();
        List<ActivityInfo> activityInfoList = resp.getActivityInfoList();
        for(Activity activity : activityList) {
            activityInfoList.add(activity.toInfo());
        }
        return resp;
    }

    @Override
    public void createActivity(Session session, ActivityInfo activityInfo) {
        Activity activity = Activity.createEntity(activityInfo);
        activityMapper.addActivity(activity);
    }

    @Override
    public void joinActivity(Session session, int activityId) {

    }

    @Override
    public void cancelActivity(Session session, int activityId) {

    }

}
