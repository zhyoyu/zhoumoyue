package com.sh.wxa.module.login;

import com.sh.wxa.Services;
import com.sh.wxa.module.login.msg.LoginRequest;
import com.sh.wxa.onlinemanager.Session;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    public void login(Session session, LoginRequest request) {
        if(!Services.getUserService().userIsExist(request.getOpenId())) {
            Services.getUserService().createUser(request);
        } else {
            Services.getUserService().updateUser(request);
        }
    }

}
