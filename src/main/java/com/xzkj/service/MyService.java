package com.xzkj.service;

import com.xzkj.dal.HdfsDal;
import com.xzkj.dal.RedisDal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

@Service
public class MyService {

    @Autowired
    HdfsDal hdfsDal;

    @Autowired
    RedisDal redisDal;

    private static Logger dataLogger = LoggerFactory.getLogger("RollingData");
    private static ResourceBundle bundle = ResourceBundle.getBundle("logPathInHdfs");

    public void dataFromRedis2Hdfs(String key, String type) {
        // 根据key，获得redis中的json数据
        String fullKey = type + "_" + key;
        String redisData = redisDal.getInit().get(fullKey);
        // 将获得的数据打日志到本地磁盘
        dataLogger.info("{} - {}", type, redisData);
        // 将内层的json解析出来，并加上换行符
        String data = redisData.substring(1, redisData.length() - 1).split(":", 2)[1].trim() + "\n";
        // 获得指定格式的当前时间
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(now);
        // 根据type和时间，将json数据保存到相应hdfs路径
        String path = bundle.getString("path") + "ods_" + type + "_log/" + date + "/" + type + ".log";
        hdfsDal.getInit().write(path, data);
        // 关闭资源
//        redisDal.close();
//        hdfsDal.close();
    }

}