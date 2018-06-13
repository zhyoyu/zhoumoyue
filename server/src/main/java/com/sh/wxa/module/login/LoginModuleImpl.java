package com.sh.wxa.module.login;

import com.sh.wxa.Services;
import com.sh.wxa.annotation.Module;
import com.sh.wxa.module.LoginModule;
import com.sh.wxa.module.login.message.LoginRequest;
import com.sh.wxa.module.login.message.LoginResponse;
import com.sh.wxa.onlinemanager.Session;

@Module
public class LoginModuleImpl implements LoginModule {

    @Override
    public LoginResponse login(LoginRequest request, Session session) {
        return Services.getLoginService().login(session, request.getMemo());
    }

}
