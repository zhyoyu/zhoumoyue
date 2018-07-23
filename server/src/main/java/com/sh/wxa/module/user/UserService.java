package com.sh.wxa.module.user;

import com.sh.wxa.module.login.msg.LoginRequest;
import com.sh.wxa.module.user.entity.User;
import com.sh.wxa.onlinemanager.Session;

import java.util.List;

public interface UserService {

    Session createSession(String openId);

    void createUser(LoginRequest request);

    void updateUser(LoginRequest request);

    boolean userIsExist(String openId);

    User findUserById(String openId);

    List<User> findUserByIds(List<String> userIds);
}
