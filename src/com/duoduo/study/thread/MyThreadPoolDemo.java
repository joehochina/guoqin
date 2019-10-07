package com.duoduo.study.thread;

import java.util.concurrent.*;

/**
 * 第四种获得JAVA线程的方式
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
//        threadPoolBase();
        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                 TimeUnit.SECONDS,
                 new LinkedBlockingQueue<Runnable>(3),
                 Executors.defaultThreadFactory(),
//                 new ThreadPoolExecutor.AbortPolicy());//超出最大线程数会报错
//                 new ThreadPoolExecutor.CallerRunsPolicy()); //超出不会报错
                 new ThreadPoolExecutor.DiscardOldestPolicy()); //超出不会报错
        //10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i <=9 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 来办理业务");
                });
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            threadPool.shutdown();
        }

    }

    private static void threadPoolBase() {
        //        System.out.println(Runtime.getRuntime().availableProcessors());
        //一池五线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);//执行一个长期的任务,性能好很多
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//一个任务一个线程执行的任务场景
        ExecutorService threadPool = Executors.newCachedThreadPool();//适用:执行很多短期异步的小程序或者负载较轻的服务器

        //10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i <=10 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 来办理业务");
                });
//               try {
//                   TimeUnit.MICROSECONDS.sleep(200);
//               }catch (InterruptedException e){e.printStackTrace();}
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            threadPool.shutdown();
        }
    }
}
