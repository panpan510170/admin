package com.pan.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pangaofeng on 2018/9/17
 */
public class ListUtils {

    /**
     * list 随机返回一个下标
     */
    private static Integer listRandomIndex(Integer index){
        int random = (int) (Math.random() * index);
        return random;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("123");
        list.add("123");
        list.add("123");
        list.add("123");
        list.add("123");

        int random = (int) (Math.random() * list.size());
        System.out.println(random);
    }
}
