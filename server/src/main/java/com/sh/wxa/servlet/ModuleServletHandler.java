package com.sh.wxa.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModuleServletHandler {

    static final String CHARSET_UTF8 = "UTF-8";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(CHARSET_UTF8);
        String responseStr = "你好，周末约么？";
        response.setHeader("Cache-Control", "no-cache");
        response.getOutputStream().write(responseStr.getBytes(CHARSET_UTF8));
    }

}
