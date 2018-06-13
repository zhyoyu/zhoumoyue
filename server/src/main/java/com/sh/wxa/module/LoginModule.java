package com.sh.wxa.module;

import com.sh.wxa.module.login.message.LoginRequest;
import com.sh.wxa.module.login.message.LoginResponse;
import com.sh.wxa.onlinemanager.Session;

public interface LoginModule {

    LoginResponse login(LoginRequest request, Session session);

}
