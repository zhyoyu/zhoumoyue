package com.sh.wxa.onlinemanager;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.sh.wxa.server.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class OnlineUserManager {

    private static final Logger LOGGER = LoggerFactory.getLogger("login");

    /**
     * key: playerId
     */
    private final LoadingCache<Long, Session> sessionCache;

    /**
     * < sessionId, playerId >
     */
    private final ConcurrentHashMap<String, Long> indexer;

    public OnlineUserManager() {
        this.indexer = new ConcurrentHashMap<String, Long>();
        this.sessionCache = CacheBuilder.newBuilder().maximumSize(100000)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .concurrencyLevel(16)
                .removalListener(new RemovalListener<Long, Session>() {
                    public void onRemoval(RemovalNotification<Long, Session> notification) {
                        Session session = notification.getValue();
                        if (session != null) {
                            if (session.getSessionId() != null) {
                                indexer.remove(session.getSessionId());
                            }
                        }
                    }
                }).build(new CacheLoader<Long, Session>() {
                    @Override
                    public Session load(Long playerId) throws Exception {
//                        Session session = Services.getPlayerService().createGameSession(playerId);
                        Session session = new Session();
                        return session;
                    }
                });
    }

}
