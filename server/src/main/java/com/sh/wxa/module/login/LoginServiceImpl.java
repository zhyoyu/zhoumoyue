package com.sh.wxa.module.login;

import com.sh.wxa.module.login.message.LoginResponse;
import com.sh.wxa.onlinemanager.Session;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    public LoginResponse login(Session session, String memo) {
        LoginResponse resp = new LoginResponse();
        resp.setUserName(session.getUserName());
        resp.setCity(session.getCity());
        resp.setMemo(memo);
        return resp;
    }

}
