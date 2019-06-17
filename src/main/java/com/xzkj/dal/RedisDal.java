package com.xzkj.dal;

import org.springframework.stereotype.Repository;

@Repository
public interface RedisDal {

    String get(String key);

    Long del(String key);

    void close();

}
