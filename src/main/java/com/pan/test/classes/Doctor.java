package com.pan.test.classes;

/**
 * Created by Pangaofeng on 2018/9/25
 */
public abstract class Doctor implements Qinshou{
    //多态，情况不定
     public void workInDay(){
         System.out.println("白天传授理论知识");
     }
}
