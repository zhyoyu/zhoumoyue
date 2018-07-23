package com.sh.wxa.module.user;

import com.sh.wxa.Services;
import com.sh.wxa.annotation.Module;
import com.sh.wxa.module.UserModule;
import com.sh.wxa.module.user.msg.UserInfoResponse;
import com.sh.wxa.onlinemanager.Session;

@Module
public class UserModuleImpl implements UserModule {
    @Override
    public UserInfoResponse getUserInfo(Session session) {
        return Services.getUserService().getUserInfo(session.getOpenId());
    }
}
