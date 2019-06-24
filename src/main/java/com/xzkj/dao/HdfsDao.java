package com.xzkj.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface HdfsDao {

    void write(String hdfs_path, String data);

    void close();
}
