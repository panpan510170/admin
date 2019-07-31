package com.pan.test.interview;

import java.util.Date;

/**
 * Created by Pangaofeng on 2018/9/27
 */
public class Test extends Date {
    public static void main(String[] args) {
        System.out.println(new Test().test());
    }

    static int test(){
        int x = 1;

        try {
            return x;
        }finally {
            ++x;
        }
    }

    /*public void test(){
        System.out.println(super.getClass().getName());
    }*/
}
