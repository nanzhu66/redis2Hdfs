package com.xzkj.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xzkj.utills.MyJedisUtills;

import java.util.Collection;

public class test {

    public static void main(String[] args) {

/*        String data = MyJedisUtills.getJedis().get("BaseData_444380_1560146150");

        System.out.println(JSON.parseObject(data).keySet());

        String key = (String)JSON.parseObject(data).keySet().toArray()[0];
        System.out.println(key);

        Collection<Object> values = JSON.parseObject(data).values();
        String s = values.stream().findFirst().toString();

        System.out.println(s);*/

String data = "{1:{z:ZZ,b:BB}}";

        System.out.println();

    }

}
