package com.pan.interview;

/**
 * Created by Pangaofeng on 2018/9/27
 */
public class Isnull {
    /**
     * &&和&的区别
     *      &&只要第一个满足则不会在判断第二个条件
     *      & 则会都判断
     * ||和|的区别
     *      ||只要第一个满足则不会在判断第二个条件
     *       |则会都判断
     * */
    public static void main(String[] args) {
        String s = null;
        if(s != null && s.length()>0){

        }

        if(s == null || s.length()>0){

        }


        if((23!=23)&&(100/0==0)){
            System.out.println("运算没有问题。");
        }else{
            System.out.println("没有报错");
        }

        if((23==23)||(100/0==0)){
            System.out.println("运算没有问题。");
        }else{
            System.out.println("没有报错");
        }


        if(3 > 2 && 3 > 4){
            System.out.println("结果...1");
        }else{
            System.out.println("结果...2");
        }

        if(3 > 2 & 3 > 4){
            System.out.println("结果...3");
        }else{
            System.out.println("结果...4");
        }

        if(3 > 2 || 3 > 4){
            System.out.println("结果...1");
        }else{
            System.out.println("结果...2");
        }

        if(3 > 4 | 3 > 2){
            System.out.println("结果...3");
        }else{
            System.out.println("结果...4");
        }
    }
}
