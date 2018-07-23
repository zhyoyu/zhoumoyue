package com.sh.wxa.module.activity;

import com.sh.wxa.Services;
import com.sh.wxa.module.activity.entity.Activity;
import com.sh.wxa.module.activity.mapper.ActivityMapper;
import com.sh.wxa.module.activity.msg.ActivityInfoResponse;
import com.sh.wxa.module.activity.msg.ActivityListResponse;
import com.sh.wxa.module.activity.msg.po.ActivityInfoPo;
import com.sh.wxa.module.activity.msg.po.ActivitySpecificInfoPo;
import com.sh.wxa.module.activity.msg.po.UserInfoPo;
import com.sh.wxa.module.user.constants.Sex;
import com.sh.wxa.module.user.entity.User;
import com.sh.wxa.onlinemanager.Session;
import com.sh.wxa.util.PageUtil;
import com.sh.wxa.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public ActivityListResponse getActivityList(Session session, int curPage) {
        final int index = PageUtil.getIndex(curPage, PageUtil.DEFAULT_PAGE_SIZE);
        List<Activity> activityList = activityMapper.findByCondition(index, PageUtil.DEFAULT_PAGE_SIZE);
        ActivityListResponse resp = new ActivityListResponse();
        List<ActivityInfoPo> activityInfoList = resp.getActivityInfoList();
        for (Activity activity : activityList) {
            ActivityInfoPo info = activity.toInfo();
            activityInfoList.add(info);
        }
        return resp;
    }

    @Override
    public void createActivity(Session session, ActivityInfoPo activityInfo) {
        Activity activity = Activity.createEntity(activityInfo);
        activityMapper.add(activity);
    }

    @Override
    public void deleteActivity(Session session, int activityId) {
        Activity activity = activityMapper.findById(activityId);
        if (activity == null) {
            return;
        }
        if (!activity.getCreateUserId().equals(session.getOpenId())) {
            return;
        }
        activityMapper.delete(activityId);
    }

    @Override
    public void joinActivity(Session session, int activityId) {
        final String openId = session.getOpenId();
        Activity activity = activityMapper.findById(activityId);
        if (activity == null) {
            return;
        }
        List<String> joinList = StringUtils.parseStringList(activity.getJoinUsers());

        if (joinList.size() >= activity.getNumLimit()) {
            return;
        }
        if (!joinList.contains(openId)) {
            joinList.add(openId);
            activity.setJoinUsers(StringUtils.toStringForList(joinList));
            activityMapper.update(activity);
        }
    }

    @Override
    public void cancelActivity(Session session, int activityId) {
        final String openId = session.getOpenId();
        Activity activity = activityMapper.findById(activityId);
        if (activity == null) {
            return;
        }
        List<String> joinList = StringUtils.parseStringList(activity.getJoinUsers());

        if (joinList.contains(openId)) {
            joinList.remove(openId);
            activity.setJoinUsers(StringUtils.toStringForList(joinList));
            activityMapper.update(activity);
        }
    }

    @Override
    public ActivityInfoResponse getActivityInfo(Session session, int activityId) {
        Activity activity = activityMapper.findById(activityId);
        ActivityInfoResponse resp = new ActivityInfoResponse();
        ActivitySpecificInfoPo info = new ActivitySpecificInfoPo();
        resp.setActivityInfo(info);
        if(activity == null) {
            return resp;
        }
        info.build(activity);
        List<String> joinUsers = StringUtils.parseStringList(activity.getJoinUsers());
        if(joinUsers.size() > 0) {
            List<User> userList = Services.getUserService().findUserByIds(joinUsers);
            List<UserInfoPo> userInfoList = info.getUserInfoList();
            int maleNum = 0;
            for(User joinUser : userList) {
                UserInfoPo userInfo = new UserInfoPo();
                userInfo.setOpenId(joinUser.getOpenId());
                userInfo.setIconUrl(joinUser.getIconUrl());
                userInfo.setUserName(joinUser.getNickName());
                userInfoList.add(userInfo);
                if(Sex.valueOf(joinUser.getSex()) == Sex.MALE) {
                    maleNum ++;
                }
            }
            info.setMaleNum(maleNum);
            info.setFemaleNum(userList.size() - maleNum);
        }
        return resp;
    }

}
