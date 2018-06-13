package com.sh.wxa.module.login;

import com.sh.wxa.module.login.message.LoginResponse;
import com.sh.wxa.onlinemanager.Session;

public interface LoginService {

    LoginResponse login(Session session, String memo);

}
