package com.sh.wxa.module.user;

import com.sh.wxa.module.login.message.pojo.UserBasicInfo;
import com.sh.wxa.onlinemanager.Session;

public interface UserService {

    Session createSession(String openId);

    void createUser(UserBasicInfo userBaseInfo);

    boolean userIsExist(String openId);
}
