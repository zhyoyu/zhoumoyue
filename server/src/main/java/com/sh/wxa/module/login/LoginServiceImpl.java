package com.sh.wxa.module.login;

import com.sh.wxa.Services;
import com.sh.wxa.module.login.msg.LoginRequest;
import com.sh.wxa.module.user.entity.User;
import com.sh.wxa.module.user.mapper.UserMapper;
import com.sh.wxa.onlinemanager.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    public void login(Session session, LoginRequest request) {
        User user = userMapper.findById(request.getOpenId());
        if(user == null) {
            Services.getUserService().createUser(request);
        } else {
            Services.getUserService().updateUser(user, request);
        }
    }

}
