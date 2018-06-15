package com.sh.wxa.module.user;

import com.sh.wxa.module.login.msg.LoginRequest;
import com.sh.wxa.onlinemanager.Session;

public interface UserService {

    Session createSession(String openId);

    void createUser(LoginRequest request);

    boolean userIsExist(String openId);
}
