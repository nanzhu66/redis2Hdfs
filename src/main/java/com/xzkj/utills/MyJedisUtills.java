package com.xzkj.utills;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class MyJedisUtills {

    private static ResourceBundle bundle = ResourceBundle.getBundle("redis");
    private static JedisPool pool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();

        int maxTotal = Integer.parseInt(bundle.getString("redis.pool.maxTotal"));
        int maxIdle = Integer.parseInt(bundle.getString("redis.pool.maxIdle"));
        int maxWaitMillis = Integer.parseInt(bundle.getString("redis.pool.maxWaitMillis"));
        boolean testOnBorrow = Boolean.parseBoolean(bundle.getString("redis.pool.testOnBorrow"));
        boolean testOnReturn = Boolean.parseBoolean(bundle.getString("redis.pool.testOnReturn"));

        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

        String host = bundle.getString("redis.host");

        int port = Integer.parseInt(bundle.getString("redis.port"));
        int timeout = Integer.parseInt(bundle.getString("redis.timeout"));
        String password = bundle.getString("redis.password");
        int database = Integer.parseInt(bundle.getString("redis.database"));

        pool = new JedisPool(config, host, port, timeout, password, database);
    }

    /**
     * @return 返回redis客户端连接对象
     */
    public static Jedis getJedis() {
        return pool.getResource();
    }

}