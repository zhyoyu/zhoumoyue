package com.sh.wxa;

import com.sh.wxa.module.activity.ActivityService;
import com.sh.wxa.module.activity.ActivityServiceImpl;
import com.sh.wxa.module.login.LoginService;
import com.sh.wxa.module.login.LoginServiceImpl;
import com.sh.wxa.module.user.UserService;
import com.sh.wxa.module.user.UserServiceImpl;

public class Services {

    public static UserService getUserService() {
        return getBeanByImplClass(UserServiceImpl.class, UserService.class);
    }

    public static LoginService getLoginService() {
        return getBeanByImplClass(LoginServiceImpl.class, LoginService.class);
    }

    public static ActivityService getActivityService() {
        return getBeanByImplClass(ActivityServiceImpl.class, ActivityService.class);
    }

    public static <T> T getBeanByImplClass(Class<? extends T> implClass, Class<T> interfaceClass) {
        return Server.getBean(implClass, interfaceClass);
    }

}
