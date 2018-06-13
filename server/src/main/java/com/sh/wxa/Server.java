package com.sh.wxa;

import com.sh.wxa.constants.AppConstants;
import com.sh.wxa.onlinemanager.OnlineUserManager;
import com.sh.wxa.servlet.InvocationTargetFactory;
import com.sh.wxa.servlet.MethodInvocation;
import com.sh.wxa.util.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ClassUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.beans.Introspector;
import java.io.IOException;
import java.util.Properties;

public class Server {

    private static volatile OnlineUserManager onlineUserManager;

    private static volatile State state = State.STOPPED;

    private static volatile ApplicationContext context;

    private static Properties config = null;

    public static class ContextListener implements ServletContextListener {

        public void contextInitialized(ServletContextEvent servletContextEvent) {
            Loggers.COMMON.info("Servlet context initialized.");
            try {
                Server.init();
                Loggers.COMMON.info("Server start success");
            } catch (Exception e) {
                Loggers.COMMON.info("Server start error .", e);
            }
        }

        public void contextDestroyed(ServletContextEvent servletContextEvent) {
            Loggers.COMMON.info("Servlet context destroyed.");
            Server.stop();
        }
    }
    //服务器初始化
    private static void init() throws IOException {
        state = State.INITIALISING;
        onlineUserManager = new OnlineUserManager();
        context = new ClassPathXmlApplicationContext("applicationContext.xml");

        initServerConfig();
        initMethodInvocation();

        state = State.RUNNING;
    }

    private static void initServerConfig() throws IOException {
            config = PropertiesLoaderUtils.loadAllProperties(AppConstants.SERVER_CONFIG_FILE_NAME);
    }

    private static void initMethodInvocation() {
        MethodInvocation.setTargetFactory(new InvocationTargetFactory() {

            public Object create(String targetName) {
                if (StringUtils.isEmpty(targetName)) {
                    return null;
                }
                String beanName = Introspector.decapitalize(targetName) + AppConstants.MODULE_IMPL_CLASS_SUFFIX;
                return getBean(beanName);
            }
        });
    }

    //停服
    public static void stop() {
        state = State.STOPPING;
        state = State.STOPPED;
    }

    public static boolean isRunning() {
        return state == State.RUNNING;
    }

    public static boolean isStopped() {
        return state == State.STOPPED;
    }

    public static OnlineUserManager getOnlinePlayerManager() {
        return onlineUserManager;
    }

    static <T> T getBean(Class<? extends T> implClass, Class<T> interfaceClass) {
        return context.getBean(buildDefaultBeanName(implClass), interfaceClass);
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }

    private static String buildDefaultBeanName(Class<?> beanType) {
        String shortClassName = ClassUtils.getShortName(beanType.getName());
        return Introspector.decapitalize(shortClassName);
    }

    public enum State {
        STOPPED,
        INITIALISING,
        RUNNING,
        STOPPING,
    }

}
