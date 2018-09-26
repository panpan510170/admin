package com.mshd.test.classes;

/**
 * Created by Pangaofeng on 2018/9/25
 */
public class TestClazz {
    public static void main(String[] args) {
        new clazz().show();
        (new clazz()).new B().go();//外部类外访问内部类
    }
}
