package com.duoduo.study.thread;

import java.util.concurrent.CountDownLatch;

/**
 *让一些线程阻塞直到另外一些完成后才被唤醒
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception {
        sixCountry();
    }

    private static void sixCountry() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国，被灭");
                countDownLatch.countDown();
            },CountryEnum.forEach(i).getName()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 秦帝国，一统天下！");
    }
}
