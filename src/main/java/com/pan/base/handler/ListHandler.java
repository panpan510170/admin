package com.pan.base.handler;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * list工具处理类
 * @author pan
 * @date 2019/8/29 20:33
 */
public class ListHandler {
    /**
     * list 随机返回一个下标
     */
    private static Integer listRandomIndex(Integer index){
        int random = (int) (Math.random() * index);
        return random;
    }

    /**
     * json 转化list
     * @param data json字符串
     * @param clazz  类型
     * @param <T>  class
     * @return
     */
    public static <T> List<T> jsonString2List(String data, Class<T> clazz) {
        List<T> list = JSONObject.parseArray(data, clazz);
        return list;
    }
}
