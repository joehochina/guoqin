package com.duoduo.study.thread;

import javax.jws.soap.SOAPBinding;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}
class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"======come in callable");
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){e.printStackTrace();}
        return 1024;
    }
}
/**
 * 多线程中，有三种获得多线程的方式
 */
public class CallableDemo {
    public static void main(String[] args) throws InterruptedException,ExecutionException {
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread2());
        new Thread(futureTask1,"AA").start();
        new Thread(futureTask2,"BB").start();

        System.out.println(Thread.currentThread().getName()+"=========");
        int result01=100;
        int result02=futureTask1.get();
        System.out.println("======result:"+(result01+result02));

    }
}
