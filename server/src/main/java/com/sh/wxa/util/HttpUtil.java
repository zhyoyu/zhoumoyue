package com.sh.wxa.util;

import com.sh.wxa.JsonMessage;
import com.sh.wxa.constants.AppConstants;
import com.sh.wxa.module.login.message.LoginRequest;
import com.sh.wxa.module.login.message.pojo.UserBaseInfo;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    public static String post(String url, Map<String, Object> params) {
        CloseableHttpClient client = null;
        CloseableHttpResponse ret = null;
        try {
            client = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).setConnectionRequestTimeout(10000).setRedirectsEnabled(false).build();
            HttpPost post = new HttpPost(url);
            post.setConfig(requestConfig);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();

            for (String key : params.keySet()) {
                Object value = params.get(key);
                if (value != null) {
                    formparams.add(new BasicNameValuePair(key, String.valueOf(value)));
                }
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
            post.setEntity(entity);
            ret = client.execute(post);
            HttpEntity response = ret.getEntity();
            String str = EntityUtils.toString(response, Consts.UTF_8);
            return str;
        } catch (Exception e) {
            LOGGER.error("请求失败,url:" + url + ",params:" + params, e);
            return null;
        } finally {

            if (ret != null) {
                try {
                    ret.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    public static String get(String url, Map<String, Object> params) {
        CloseableHttpClient client = null;
        CloseableHttpResponse ret = null;
        try {
            client = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000)
                    .setConnectTimeout(10000).setConnectionRequestTimeout(10000).setRedirectsEnabled(false).build();
            StringBuilder builder = new StringBuilder(url);
            builder.append("?");
            for (String key : params.keySet()) {
                Object value = params.get(key);
                if (value != null) {
                    builder.append(key);
                    builder.append("=");
                    builder.append(value);
                    builder.append("&");
                }
            }
            System.out.println(builder.toString());
            HttpGet get = new HttpGet(builder.toString());
            get.setConfig(requestConfig);
            ret = client.execute(get);
            HttpEntity response = ret.getEntity();
            String str = EntityUtils.toString(response, Consts.UTF_8);
            return str;
        } catch (Exception e) {
            LOGGER.error("请求失败,url:" + url + ",params:" + params, e);
            return null;
        } finally {


            if (ret != null) {
                try {
                    ret.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    @lombok.Getter
    @lombok.Setter
    public static class A {
        private String orderId;
        private int amount;
        private String currency;
        private String roleId;
        private String productId;
        private String serverId;
        private String orderExt;
    }

    public static void main(String[] args) throws Exception {
        final String urlPrefix = "http://localhost:8089/wxaserver";
        Map<String, Object> param = new HashMap<String, Object>();

        //构建param
        LoginRequest req = new LoginRequest();
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setOpenId("yc_1");
        userBaseInfo.setCity("杭州");
        userBaseInfo.setSex("男");
        userBaseInfo.setUserName("言辰");
        req.setMemo("hello");
        paramBuild(JsonMessage.toJsonString(userBaseInfo), "login", "login", req, param);

        String ret = HttpUtil.get(urlPrefix, param);
        System.out.println("---------------------------------------------------------");
        System.out.println("ret:" + ret);
    }

    public static void paramBuild(String userBaseInfo, String moduleName, String methodName, Object object, Map<String, Object> param) throws Exception {
        param.put("ubi", URLEncoder.encode(userBaseInfo, AppConstants.CHARSET_UTF8));
        param.put("mod", moduleName + "." + methodName);
        param.put("body", URLEncoder.encode(JsonMessage.toJsonString(object), AppConstants.CHARSET_UTF8));
    }

}
