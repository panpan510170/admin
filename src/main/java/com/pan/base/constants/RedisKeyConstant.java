package com.pan.base.constants;

/**
 * 缓存key常量类
 * @author pan
 * @date 2019/8/26 15:29
 */
public class RedisKeyConstant {


    /**
     * 后台管理验证码key
     * @param sessionId  sessionId
     * @return
     */
    public static String systemLogin(String sessionId) {
        return "System_Login_"+sessionId;
    }
}
