package com.xzkj.dal;

import com.xzkj.utills.MyJedisUtills;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

@Repository
public class RedisDal {

    private Jedis jedis = MyJedisUtills.getJedis();

    public String get(String key) {
        return jedis.get(key);
    }

    public Long del(String key) {
        return jedis.del(key);
    }

    public void close() {
        jedis.close();
    }

}
