package com.pan.serivce;

/**
 * Created by Pangaofeng on 2018/9/8
 */
public interface TestService {

    Integer testThread();

    Integer testThrow(int a);

    void testRedisTime(String s);
}
