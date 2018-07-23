package com.sh.wxa.module.user;

import com.google.common.collect.Lists;
import com.sh.wxa.module.login.msg.LoginRequest;
import com.sh.wxa.module.user.entity.User;
import com.sh.wxa.module.user.mapper.UserMapper;
import com.sh.wxa.onlinemanager.Session;
import com.sh.wxa.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public Session createSession(String openId) {
        User user = userMapper.findById(openId);
        if(user != null) {
            Session session = new Session();
            session.setOpenId(openId);
            session.setCity(user.getCity());
            session.setNickName(user.getNickName());
            session.setSex(user.getSex());
            session.setIconUrl(user.getIconUrl());
            session.setActiveValue(user.getActiveValue());
            return session;
        }
        return null;
    }

    public void createUser(LoginRequest request) {
        User user = new User();
        user.setOpenId(request.getOpenId());
        user.setSex(request.getSex());
        user.setNickName(request.getNickName());
        user.setCity(request.getCity());
        user.setIconUrl(request.getIconUrl());
        user.setActiveValue(0);
        Date now = new Date();
        user.setRegisterTime(now);
        user.setLastLoginTime(now);
        userMapper.add(user);
    }

    @Override
    public void updateUser(LoginRequest request) {
        User user = userMapper.findById(request.getOpenId());
        user.setIconUrl(request.getIconUrl());
        user.setNickName(request.getNickName());
        user.setCity(request.getCity());
        user.setSex(request.getSex());
        user.setLastLoginTime(new Date());
        userMapper.update(user);
    }

    public boolean userIsExist(String openId) {
        return userMapper.countById(openId) > 0;
    }

    @Override
    public User findUserById(String openId) {
        return userMapper.findById(openId);
    }

    @Override
    public List<User> findUserByIds(List<String> userList) {
        List<User> result = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(userList)) {
            String userIds = StringUtils.toStringForList(userList, StringUtils.SPLIT_FLAG_DOU_HAO);
            result.addAll(userMapper.findUserByIds(userIds));
        }
        return result;
    }
}
