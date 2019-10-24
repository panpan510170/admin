package com.pan.base.handler;

import com.alibaba.fastjson.JSONObject;

/**
 * 数据处理工具
 * @author pan
 * @date 2019/6/20 18:29
 */
public class BeanHandler {
    /**
     * ---------------------------------javaBean---------------------------------------------------------------
     *
     *

     //将对象转换成为字符串

     String str = JSON.toJSONString(infoDo);

     //将字符串转换成为对象

     InfoDo infoDo = JSON.parseObject(strInfoDo, InfoDo.class);

     //将对象集合转换成为字符串

     String users = JSON.toJSONString(users);

     //将字符串转换成为对象集合

     List<User> userList = JSON.parseArray(userStr, User.class);
     *
     * */
    /**
     * javaBean转jsonString
     * @param bean
     * @return
     */
    public static String bean2JsonString(Object bean){
        return JSONObject.toJSONString(bean);
    }

    /**
     * javaBean转jsonObject
     * @param bean
     * @return
     */
    public static JSONObject bean2JsonObject(Object bean){
        String jsonString = JSONObject.toJSONString(bean);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        return jsonObject;
    }

    /**
     * JsonString转javaBean 支持复杂json
     * @param json
     * @param clazz bean.class
     * @return
     */
    public static <T> T jsonString2Bean(String json,Class<T> clazz){
        T bean = JSONObject.parseObject(json, clazz);
        return bean;
    }

    /**
     * javaBean转javaBean
     * @param bean1
     * @param clazz bean.class
     * @return
     */
    public static <T> T beanConver(Object bean1, Class<T> clazz){
        String jsonString = JSONObject.toJSONString(bean1);
        T bean = JSONObject.parseObject(jsonString, clazz);
        return bean;
    }
}
