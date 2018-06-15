package com.sh.wxa.module;

import com.sh.wxa.module.login.msg.LoginRequest;
import com.sh.wxa.onlinemanager.Session;
import com.sh.wxa.util.OkResponse;

public interface LoginModule {

    OkResponse login(LoginRequest request, Session session);

}
