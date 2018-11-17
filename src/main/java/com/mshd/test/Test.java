package com.mshd.test;

import com.alibaba.druid.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pangaofeng on 2018/11/14
 */
public class Test {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        System.out.println(strings.size());
        if(null != strings && strings.size()>0){
            System.out.println("123123");
        }

      /*  Long currentValue = System.currentTimeMillis();
        System.out.println(currentValue);
        boolean empty = StringUtils.isEmpty(Long.toString(currentValue));
        System.out.println(empty);*/
    }
}
