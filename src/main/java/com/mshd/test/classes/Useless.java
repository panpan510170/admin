package com.mshd.test.classes;

import java.io.Serializable;

/**
 * Created by Pangaofeng on 2018/9/25
 */
public class Useless extends UselessParent {

    public Serializable s1 = new Serializable() {
        {
            System.out.println("域变量");
        }
    };

    public static Serializable s2 = new Serializable() {
        {
            System.out.println("静态域变量");
        }
    };

    static { System.out.println("静态代码块"); }

    { System.out.println("代码块"); }


}
