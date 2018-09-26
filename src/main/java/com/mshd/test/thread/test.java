package com.mshd.test.thread;

/**
 * Created by Pangaofeng on 2018/9/12
 */
public class test {

    public static void main(String[] args) {
        //测试extends
        /*MyThread T1 = new MyThread("A");
        MyThread T2 = new MyThread("B");
        T1.start();
        T2.start();*/

        //测试Runnable
        MyThread1 t1 = new MyThread1();
        new Thread(t1).start();//同一个t1，如果在Thread中就不行，会报错
        new Thread(t1).start();
        new Thread(t1).start();
    }
}

class MyThread extends Thread {
    private String name;
    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name+":"+i);
            try {
                sleep(1000); //休眠1秒，避免太快导致看不到同时执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class MyThread1 implements Runnable{
    private int ticket = 10;
    @Override
    //记得要资源公共，要在run方法之前加上synchronized关键字，要不然会出现抢资源的情况
    public synchronized  void  run() {
        for (int i = 0; i <10; i++) {
            if (this.ticket>0) {
                System.out.println("卖票：ticket"+this.ticket--);
            }
        }

    }

}
