package com.mshd.test.thread;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by Pangaofeng on 2018/9/12
 */
public class testThreadReturn {
    public static void main(String[] args) throws Exception{
        /*RunThread T1 = new RunThread("A");
        RunThread T2 = new RunThread("B");
        T1.start();
        T2.start();
        T2.join();
        System.out.println("result==="+T1.getResult());
        System.out.println("result==="+T2.getResult());*/

        ExecutorService exs = Executors.newCachedThreadPool();
        ArrayList<Future<String>> al = new ArrayList<Future<String>>();
        al.add(exs.submit(new CallThread("A")));
        al.add(exs.submit(new CallThread("B")));
        for(Future<String> fs:al){
            try {
                String result = fs.get();
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

class RunThread extends Thread{
    private String name;

    private String result;

    {
        result = "";
    }

    public RunThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name+":"+i);
            this.result = this.result + name+":"+i;
            try {
                sleep(1000); //休眠1秒，避免太快导致看不到同时执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public String getResult() {
        return this.result;
    }
}


class CallThread implements Callable<String> {
    private String name;

    private String result;

    {
        result = "";
    }

    public CallThread(String name) {
        this.name = name;
    }
    @Override
    public String call() throws Exception {

        for (int i = 0; i < 5; i++) {
            //System.out.println(name+":"+i);
            result = result + name+":"+i;
        }
        return result;
    }
}