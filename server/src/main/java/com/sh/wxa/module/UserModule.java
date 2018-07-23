package com.sh.wxa.module;

import com.sh.wxa.module.user.msg.UserInfoResponse;
import com.sh.wxa.onlinemanager.Session;

public interface UserModule {

    UserInfoResponse getUserInfo(Session session);
}
