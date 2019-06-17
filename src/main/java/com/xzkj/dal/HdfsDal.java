package com.xzkj.dal;

import org.springframework.stereotype.Repository;

@Repository
public interface HdfsDal {

    void write(String hdfs_path, String data);

    void close();
}
