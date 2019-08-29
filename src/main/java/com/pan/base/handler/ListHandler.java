package com.pan.base.handler;

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
}
