package com.pan.test.classes;

/**
 * 学习类加载机制
 *
 * Created by Pangaofeng on 2018/9/25
 */
public class MainFather {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Useless u;
        // u = new Useless();
        System.out.println(Useless.s2);
        System.out.println("-----------------------------------------");
        Class z;
        z = Class.forName("com.pan.test.classes.Useless");

        System.out.println("-----------------------------------------");
        z = Class.forName("com.pan.test.classes.Useless", true, MainFather.class.getClassLoader());

        System.out.println("-----------------------------------------");
        z = Class.forName("com.pan.test.classes.Useless", false, MainFather.class.getClassLoader());

        System.out.println("-----------------------------------------");
        z = MainFather.class.getClassLoader().loadClass("com.pan.test.classes.Useless");

        System.out.println("-----------------------------------------");
        u = (Useless) z.newInstance();

        System.out.println("-----------------------------------------");
        u = new Useless();
    }
}
