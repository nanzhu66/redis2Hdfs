package com.xzkj.dal;

import com.xzkj.utills.MyRedisUtills;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

@Repository
public class RedisDalImpl implements RedisDal {

    private Jedis jedis = null;

    public RedisDalImpl() {
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
