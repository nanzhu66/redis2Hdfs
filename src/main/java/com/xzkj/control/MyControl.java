package com.xzkj.control;

import com.xzkj.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyControl {

    private static Logger rollingLogger = LoggerFactory.getLogger(MyControl.class);

    @Autowired
    MyService service;

    /**
     * @param key
     * @param type type 类型：
     *             BaseData     基础数据
     *             ResultData   结果数据
     *             FrontDeskData前台数据
     *             JXLData      聚信立数据
     * @return
     */
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String post_test(@RequestParam String key, @RequestParam String type) {
        rollingLogger.info("the key is [{}] , the type is [{}]", key, type);
        // 调用service业务逻辑
        service.dataFromRedis2Hdfs(key, type);
        // 返回给前台一个标记
        return "ok";
    }

}