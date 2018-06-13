package com.sh.wxa.module.user;

import com.sh.wxa.module.login.message.pojo.UserBasicInfo;
import com.sh.wxa.module.user.entity.User;
import com.sh.wxa.module.user.mapper.UserMapper;
import com.sh.wxa.onlinemanager.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public Session createSession(String openId) {
        Session session = new Session();
        User user = userMapper.findUserById(openId);
        session.setOpenId(openId);
        session.setCity(user.getCity());
        session.setUserName(user.getUserName());
        session.setSex(user.getSex());
        session.setActiveValue(user.getActiveValue());
        return session;
    }

    public void createUser(UserBasicInfo userBaseInfo) {
        User user = new User();
        user.setOpenId(userBaseInfo.getOpenId());
        user.setUserName(userBaseInfo.getUserName());
        user.setSex(userBaseInfo.getSex());
        user.setCity(userBaseInfo.getCity());
        user.setActiveValue(0);
        userMapper.addUser(user);
    }

    public boolean userIsExist(String openId) {
        return userMapper.countUserById(openId) > 0;
    }

}
