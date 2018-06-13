package com.sh.wxa.onlinemanager;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sh.wxa.Services;

import java.util.concurrent.TimeUnit;

public class OnlineUserManager {

    /**
     * key: openId
     */
    private final LoadingCache<String, Session> sessionCache;

    public OnlineUserManager() {
        this.sessionCache = CacheBuilder.newBuilder().maximumSize(100000)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .concurrencyLevel(16)
                .build(new CacheLoader<String, Session>() {
                    @Override
                    public Session load(String openId) throws Exception {
                        return Services.getUserService().createSession(openId);
                    }
                });
    }

    /**
     * 按openId获取Session，如果在在线玩家中找不到，尝试从数据库中加载
     */
    public Session getByOpenId(String openId) {
        return this.sessionCache.getUnchecked(openId);
    }

    /**
     *   按openId获取Session,如不存在缓存中，返回null
     * @param openId
     * @return
     */
    public Session getIfPresent(String openId) {
        return this.sessionCache.getIfPresent(openId);
    }

}
