package com.mshd.test.lamdba;

/**
 * Created by Pangaofeng on 2018/9/25
 */
public class LambdaTest {
    public static void main(String[] args) {
        LambdaTest LT = new LambdaTest();
        LT.runnableTest();
        //LT.comparatorTest();
    }
    public void runnableTest() {
        System.out.println("=== RunnableTest ===");
        // Anonymous Runnable
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world one!");
            }
        };
        // Lambda Runnable
        Runnable r2 = () -> {
            System.out.println("Hello world two!");
            System.out.println("Hello world three!");
        };
        // Run em!
        r1.run();
        r2.run();
    }
}
