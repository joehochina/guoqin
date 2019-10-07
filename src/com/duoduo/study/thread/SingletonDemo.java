package com.duoduo.study.thread;

public class SingletonDemo {
    private static volatile SingletonDemo instance = null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法 SingletonDemo（）");
    }
    //在高并发模式下加 synchronized
   //DCL(double check lock 双端检锁机制)
    public static  SingletonDemo getInstance(){
        if(instance == null) {
            synchronized (SingletonDemo.class){
                if(instance==null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }
    public static void main(String[] args) {

        for (int i = 1; i <10 ; i++) {
            new Thread(()->{
                System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
            },String.valueOf(i)).start();
        }

    }
}
