package com.pan.interview;

/**
 * Created by Pangaofeng on 2018/9/27
 */
public class CompileTest extends Father{

    private String name = "test";

    public static void main(String[] args) {
        CompileTest compileTest = new CompileTest();
        System.out.println(compileTest.getName());
    }
}
