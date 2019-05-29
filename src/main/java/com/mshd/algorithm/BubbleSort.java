package com.mshd.algorithm;

/**
 * Created by Pangaofeng on 2018/11/2
 *
 * 冒泡算法
 */
public class BubbleSort {
    public static void main(String[] args) {
        //声明 创建数组对象
        int[] a = {4,3,7,2,9,1,5,8,6};

        System.out.println("排序前数组为：");
        for (int num : a) {
            System.out.print(num + " ");
        }
        System.out.println("----------------");
        for(int i = 0; i < a.length-1; i++){
            for(int j = 0; j < a.length-1-i; j++){
                System.out.println("a[j]"+a[j]);
                System.out.println("a[j+1 ]"+a[j+1]);
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
            System.out.println("第"+i+"次的排序结果为");
            for(int num:a){
                System.out.print(num+" ");
            }
            System.out.println("----------------");
        }
    }

}
