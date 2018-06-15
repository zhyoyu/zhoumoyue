package com.sh.wxa.module.login;

import com.sh.wxa.module.login.msg.LoginRequest;
import com.sh.wxa.onlinemanager.Session;

public interface LoginService {

    void login(Session session, LoginRequest request);

}
