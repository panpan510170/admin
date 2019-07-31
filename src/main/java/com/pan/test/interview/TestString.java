package com.pan.test.interview;

/**
 * Created by Pangaofeng on 2018/9/27
 */
public class TestString {
    public static String setValue(String str){
        return str = "123";
    }

    public static void main(String[] args) {
        String str = "abc";
        String s = setValue(str);
        System.out.println(str);
        System.out.println(s);
    }
}
