package com.xzkj.service;

import com.xzkj.dao.HdfsDao;
import com.xzkj.dao.HdfsDaoImpl;
import com.xzkj.dao.RedisDao;
import com.xzkj.dao.RedisDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

@Service
public class MyService {

    private static Logger dataLogger = LoggerFactory.getLogger("RollingData");
    private static ResourceBundle bundle = ResourceBundle.getBundle("logPathInHdfs");

    //开启异步
    @Async
    public void dataFromRedis2Hdfs(String key, String type) {
        HdfsDao hdfsDao = new HdfsDaoImpl();
        RedisDao redisDao = new RedisDaoImpl();
        // 根据key，获得redis中的json数据
        String fullKey = type + "_" + key;
        String redisData = redisDao.get(fullKey);
        // 将获得的数据打日志到本地磁盘
        dataLogger.info("{} - {}", type, redisData);
        // 获得指定格式的当前时间
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(now);
        // 根据type和时间，将json数据保存到相应hdfs路径
        String path = bundle.getString("path") + "/ods_" + type.toLowerCase() + "_log/" + date + "/" + type.toLowerCase() + ".log";
        hdfsDao.write(path, redisData + "\n");
        // 关闭资源
        redisDao.close();
        hdfsDao.close();
    }

}