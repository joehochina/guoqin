package com.duoduo.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData{
//    int number = 0;
    volatile int number = 0;
    public void addTo90(){
        this.number = 90;
    }

    //保持原子性的方式一
//    public synchronized void addPlus(){
//        this.number++;
//    }
    public  void addPlus(){
        this.number++;
    }
    //保持原子性的方式二
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}
/**
 * 验证 volatile的可见性
 * 1.加volatile光健字
 *
 * 验证volatile不保证原子性
 *  原子性的含义
 *  1、加synchronized关键字
 *  2、使用AtomicInteger定义
 */
public class VolatileDemo {
    public static void main(String[] args) {
//        seeOkByVolatile();
        MyData myData = new MyData();
        for (int i = 1; i <=20 ; i++) {
            new Thread(()->{
                for (int j = 1; j <=1000 ; j++) {
                    myData.addPlus();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }
        //需要等待20个线程全部执行完成后，再用main线程取得最终的更新结果
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t finally number value:"+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t atomicInteger finally number value:"+myData.atomicInteger );

    }

    //验证volatile保证可见性
    private static void seeOkByVolatile() {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t Come in");
            //等待三秒，将其值改为90
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            myData.addTo90();
            System.out.println(Thread.currentThread().getName()+"\t update data to "+myData.number);
        },"THREAD ONE").start();

        while (myData.number == 0){

        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over");



    }
}
