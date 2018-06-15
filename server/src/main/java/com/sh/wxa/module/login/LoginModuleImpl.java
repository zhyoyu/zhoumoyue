package com.sh.wxa.module.login;

import com.sh.wxa.Services;
import com.sh.wxa.annotation.Module;
import com.sh.wxa.module.LoginModule;
import com.sh.wxa.module.login.msg.LoginRequest;
import com.sh.wxa.onlinemanager.Session;
import com.sh.wxa.util.OkResponse;

@Module
public class LoginModuleImpl implements LoginModule {

    @Override
    public OkResponse login(LoginRequest request, Session session) {
        Services.getLoginService().login(session, request);
        return OkResponse.OK_RESPONSE;
    }

}
