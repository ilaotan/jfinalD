package com.ilaotan.framework.config;

import com.alibaba.fastjson.JSON;
import com.jfinal.plugin.activerecord.cache.ICache;
import com.jfinal.plugin.redis.Redis;

/**
 * redis Cache  用来给查询做缓存的
 * <p>
 * ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
 * //		arp.setCache(new MyRedisCache());
 */
public class MyRedisCache implements ICache {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String cacheName, Object key) {
        String str = Redis.use(cacheName).get(key);
        return (T) JSON.parseObject(str);
    }

    @Override
    public void put(String cacheName, Object key, Object value) {
        Redis.use(cacheName).set(key, JSON.toJSONString(value));
    }

    @Override
    public void remove(String cacheName, Object key) {
        Redis.use(cacheName).del(key);
    }

    @Override
    public void removeAll(String cacheName) {
        //todo 未实现
    }
}