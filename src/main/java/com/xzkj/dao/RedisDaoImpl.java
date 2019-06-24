package com.xzkj.dao;

import com.xzkj.utills.MyRedisUtills;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

@Repository
public class RedisDaoImpl implements RedisDao {

    private Jedis jedis = null;

    public RedisDaoImpl() {
        this.jedis = MyRedisUtills.getJedis();
    }

    @Override
    public String get(String key) {
        return jedis.get(key);
    }

    @Override
    public Long del(String key) {
        return jedis.del(key);
    }

    @Override
    public void close() {
        jedis.close();
    }

}
