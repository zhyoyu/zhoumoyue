package com.sh.wxa.module.user;

import com.sh.wxa.module.activity.entity.Activity;
import com.sh.wxa.module.login.msg.LoginRequest;
import com.sh.wxa.module.user.constants.ModifyType;
import com.sh.wxa.module.user.entity.User;
import com.sh.wxa.module.user.msg.UserInfoResponse;
import com.sh.wxa.onlinemanager.Session;

import java.util.List;

public interface UserService {

    Session createSession(String openId);

    void createUser(LoginRequest request);

    void updateUser(User user, LoginRequest request);

    List<User> findUserByIds(List<String> userIds);

    UserInfoResponse getUserInfo(Session session);

    void modifyUserActivityInfo(Session session, Long activityId, Activity activity, ModifyType type);
}
