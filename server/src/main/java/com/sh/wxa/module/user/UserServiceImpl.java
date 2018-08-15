package com.sh.wxa.module.user;

import com.google.common.collect.Lists;
import com.sh.wxa.constants.Symbol;
import com.sh.wxa.module.activity.entity.Activity;
import com.sh.wxa.module.login.msg.LoginRequest;
import com.sh.wxa.module.user.constants.ModifyType;
import com.sh.wxa.module.user.entity.User;
import com.sh.wxa.module.user.entity.UserInfo;
import com.sh.wxa.module.user.mapper.UserInfoMapper;
import com.sh.wxa.module.user.mapper.UserMapper;
import com.sh.wxa.module.user.msg.UserInfoResponse;
import com.sh.wxa.module.user.msg.po.DynamicInfo;
import com.sh.wxa.onlinemanager.Session;
import com.sh.wxa.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    public Session createSession(String openId) {
        User user = userMapper.findById(openId);
        if(user != null) {
            Session session = new Session();
            session.setOpenId(openId);
            session.setCity(user.getCity());
            session.setNickName(user.getNickName());
            session.setSex(user.getSex());
            session.setIconUrl(user.getIconUrl());
            session.setActiveValue(user.getActiveValue());
            return session;
        }
        return null;
    }

    @Override
    public void updateUser(User user, LoginRequest request) {
        user.setIconUrl(request.getIconUrl());
        user.setNickName(request.getNickName());
        user.setCity(request.getCity());
        user.setSex(request.getSex());
        user.setLastLoginTime(new Date());
        userMapper.update(user);
    }

    @Override
    public List<User> findUserByIds(List<String> userList) {
        List<User> result = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(userList)) {
            String userIds = StringUtils.toQueryStringForList(userList);
            result.addAll(userMapper.findUserByIds(userIds));
        }
        return result;
    }

    @Override
    public UserInfoResponse getUserInfo(Session session) {
        final String openId = session.getOpenId();
        UserInfoResponse resp = new UserInfoResponse();
        UserInfo userInfo = userInfoMapper.findById(openId);
        if(userInfo != null) {
            resp.setConcernUsers(StringUtils.parseStringList(userInfo.getConcernUsers()));
            resp.setFans(StringUtils.parseStringList(userInfo.getFans()));
            resp.setMyActivity(StringUtils.parseLongList(userInfo.getMyActivity()));
            resp.setMyTopic(StringUtils.parseLongList(userInfo.getMyTopic()));
            resp.setActivityDynamic(getDynamicList(userInfo.getActivityDynamic()));
            resp.setTopicDynamic(getDynamicList(userInfo.getTopicDynamic()));
        }
        return resp;
    }

    @Override
    public void modifyUserActivityInfo(Session session, final Long activityId, Activity activity, ModifyType type) {
        final String openId = session.getOpenId();
        UserInfo userInfo = userInfoMapper.findById(openId);
        boolean update = true;
        if(userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setOpenId(openId);
            update = false;
        }
        //更新userInfo
        updateUserInfo(activityId, type, userInfo, update);
        //推送动态
        addDynamicNotice(session, activityId, activity, type);
    }

    private void addDynamicNotice(Session session, Long activityId, Activity activity, ModifyType type) {
        final String dynamicNotice = type.getDynamicNotice(session, activityId, activity);
        List<String> noticeUser = type.getNoticeUser(session, activity);
        for(String userId : noticeUser) {
            modifyUserInfoDynamic(userId, dynamicNotice);
        }
    }

    private void updateUserInfo(Long activityId, ModifyType type, UserInfo userInfo, boolean update) {
        String myActivity = userInfo.getMyActivity();
        List<Long> list = StringUtils.parseLongList(myActivity);

        switch (type) {
            case JOIN_ACTIVITY:
            case CREATE_ACTIVITY:
                list.add(activityId);
                userInfo.setMyActivity(StringUtils.toStringForList(list));
                break;
            case CANCEL_ACTIVITY:
            case DELETE_ACTIVITY:
                list.remove(activityId);
                userInfo.setMyActivity(StringUtils.toStringForList(list));
                break;
        }
        if(update) {
            userInfoMapper.update(userInfo);
        } else {
            userInfoMapper.add(userInfo);
        }
    }

    private void modifyUserInfoDynamic(String openId, String dynamicNotice) {
        UserInfo userInfo = userInfoMapper.findById(openId);
        boolean update = true;
        if(userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setOpenId(openId);
            update = false;
        }
        List<String> dynamicList = StringUtils.parseStringList(userInfo.getActivityDynamic());
        dynamicList.add(dynamicNotice);
        userInfo.setActivityDynamic(StringUtils.toStringForList(dynamicList));
        if(update) {
            userInfoMapper.update(userInfo);
        } else {
            userInfoMapper.add(userInfo);
        }
    }

    public void createUser(LoginRequest request) {
        User user = new User();
        user.setOpenId(request.getOpenId());
        user.setSex(request.getSex());
        user.setNickName(request.getNickName());
        user.setCity(request.getCity());
        user.setIconUrl(request.getIconUrl());
        user.setActiveValue(0);
        Date now = new Date();
        user.setRegisterTime(now);
        user.setLastLoginTime(now);
        userMapper.add(user);
    }

    private List<DynamicInfo> getDynamicList(String dynamicStr) {
        List<String> list = StringUtils.parseStringList(dynamicStr);
        List<DynamicInfo> resultList = Lists.newArrayList();
        for(String dynamic : list) {
            DynamicInfo info = new DynamicInfo();
            String[] split = dynamic.split(Symbol.JIN_HAO);
            info.setId(Long.parseLong(split[1]));
            info.setActionUserName(split[2]);
            ModifyType modifyType = ModifyType.valueOf(Integer.parseInt(split[0]));
            info.setActionDescribe(modifyType.getActionDescribe());
            resultList.add(info);
        }
        return resultList;
    }
}
