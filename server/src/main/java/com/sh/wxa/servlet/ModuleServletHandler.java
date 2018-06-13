package com.sh.wxa.servlet;

import com.sh.wxa.JsonMessage;
import com.sh.wxa.Loggers;
import com.sh.wxa.Server;
import com.sh.wxa.Services;
import com.sh.wxa.module.login.message.pojo.UserBasicInfo;
import com.sh.wxa.onlinemanager.Session;
import com.sh.wxa.util.ErrorType;
import com.sh.wxa.util.ModuleException;
import com.sh.wxa.util.SCPrompt;
import com.sh.wxa.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModuleServletHandler {

    static final String CHARSET_UTF8 = "UTF-8";

    private static final String MODULE_KEY = "mod";

    private static final String BODY_KEY = "body";

    public static final String USER_BASE_INFO_KEY = "ubi";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding(CHARSET_UTF8);
        final String act = request.getParameter(MODULE_KEY);
        final String userBaseInfoJsonStr = request.getParameter(USER_BASE_INFO_KEY);
        final String paramJsonStr = request.getParameter(BODY_KEY);
        String responseStr = null;
        if (StringUtils.isEmpty(act) || StringUtils.isEmpty(userBaseInfoJsonStr)) {
            JsonMessage prompt = new SCPrompt("请求参数为空", null);
            responseStr = prompt.toJsonString();
        } else {
            responseStr = processRequest(request, userBaseInfoJsonStr, act, paramJsonStr).toJsonString();
        }
        response.setHeader("Cache-Control", "no-cache");
        response.getOutputStream().write(responseStr.getBytes(CHARSET_UTF8));
    }

    private JsonMessage processRequest(HttpServletRequest request, String userBaseInfoJsonStr, String module, String bodyJson) {
        if (!Server.isRunning()) {
            return SCPrompt.newError("服务器未启动");
        }

        UserBasicInfo userBaseInfo = JsonMessage.fromJsonString(userBaseInfoJsonStr, UserBasicInfo.class);
        final String openId = userBaseInfo.getOpenId();
        Session session = Server.getOnlinePlayerManager().getIfPresent(openId);
        if (session == null) {
            if(!Services.getUserService().userIsExist(openId)) {
                Services.getUserService().createUser(userBaseInfo);
            }
            session = Server.getOnlinePlayerManager().getByOpenId(openId);
        }

        JsonMessage result = null;
        try {
            MethodInvocation<JsonMessage> invocation = MethodInvocation.create(module, bodyJson, session);
            synchronized (session) {
                result = invocation.execute();
            }
        } catch (Exception e) {
            final String id = session != null ? session.getOpenId() : "-1";
            final String logMsg = String.format("Invocation error. user:%s act:%s, body:%s", id, module, bodyJson);
            if (e instanceof ModuleException) {
                ModuleException moduleException = (ModuleException) e;
                if (moduleException.getType() == ErrorType.SERVER) {
                    String errorOpenId = moduleException.getOpenId();
                    Loggers.COMMON.error("用户：" + errorOpenId + "  " + moduleException.getPrompt(), moduleException.getPromptArgs());
                } else {
                    return SCPrompt.newError(moduleException.getPrompt(), moduleException.getPromptArgs());
                }
            }
            Loggers.COMMON.error(logMsg, e);
        }
        return result;
    }

}
