package com.xzkj.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface RedisDao {

    String get(String key);

    Long del(String key);

    void close();

}
