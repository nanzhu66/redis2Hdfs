package com.xzkj.utills;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ResourceBundle;

public class MyHdfsUtills {

    private static org.slf4j.Logger logger_RollingError = LoggerFactory.getLogger("RollingError");
    private static Configuration conf = null;

    static {
        // 创建配置对象，设置配置
        conf = new Configuration();
        ResourceBundle bundle = ResourceBundle.getBundle("hdfs");
        String fs_defaultFS = bundle.getString("fs.defaultFS");
        String dfs_blocksize = bundle.getString("dfs.blocksize");
        String dfs_replication = bundle.getString("dfs.replication");
        conf.set("fs.defaultFS", fs_defaultFS);
        conf.set("dfs.blocksize", dfs_blocksize);
        conf.set("dfs.replication", dfs_replication);
        // 构造时FileSystem，会在jvm环境中取HADOOP_USER_NAME的值作为当前客户端的用户身份
        System.setProperty("HADOOP_USER_NAME", "root");
    }

    /**
     * @return 返回FileSystem客户端连接对象
     */
    public FileSystem getFileSystem() {
        FileSystem fs = null;
        // 获取一个文件系统访问对象
        try {
            fs = FileSystem.get(conf);// FileSystem类是一个抽象，可以根据fs.defaultFS参数值不同而获取到访问不同文件系统的对象
        } catch (IOException e) {
            logger_RollingError.error("{}",e);
        }
        return fs;
    }

}