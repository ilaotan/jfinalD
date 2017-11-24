package com.ilaotan.framework.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.io.Serializable;

public class ShiroCache {

    private static CacheManager cacheManager;

    /**
     * 清除用户的授权信息
     *
     * @param username
     */
    public static void clearAuthorizationInfo(String username) {
        Cache<Object, Object> cache = cacheManager.getCache("myRealm.authorizationCache");
        cache.remove(username);

    }

    public static void clearAuthorizationInfoAll() {
        Cache<Object, Object> cache = cacheManager.getCache("myRealm.authorizationCache");
        cache.clear();

    }

    /**
     * 清除session(认证信息)
     *
     * @param jsessionid
     */
    public static void clearSession(Serializable jsessionid) {
        Cache<Object, Object> cache = cacheManager.getCache("shiro-activeSessionCache");
        cache.remove(jsessionid);
    }

    public static CacheManager getCacheManager() {
        return cacheManager;
    }

    public static void setCacheManager(CacheManager cacheManager) {
        ShiroCache.cacheManager = cacheManager;
    }

}
