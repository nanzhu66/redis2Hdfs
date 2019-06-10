package com.xzkj.service;

import com.xzkj.dal.HdfsDal;
import com.xzkj.dal.RedisDal;
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

    private static ResourceBundle bundle = ResourceBundle.getBundle("logPathInHdfs");

    public void dataFromRedis2Hdfs(String key, String type) {
        // 根据key，获得redis中的json数据
        String fullKey = type + "_" + key;
        String data = redisDal.get(fullKey) + "\n";

        //获得指定格式的当前时间
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(now);

        // 根据type和时间，将json数据保存到相应hdfs路径
        String path = bundle.getString("path") + "ods_" + type + "_log/" + date + "/" + type + ".log";
        hdfsDal.write(path, data);

        // 关闭资源
//        redisDal.close();
//        hdfsDal.close();
    }

}