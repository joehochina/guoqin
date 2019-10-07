package com.duoduo.study.thread;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("------call dagon------");
        });
        for (int i = 1; i <=7 ; i++) {

            final int tempInt = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t收集到第"+tempInt+"颗龙珠");
                try {
                    cyclicBarrier.await();
                }catch (InterruptedException e){e.printStackTrace();}
                catch (BrokenBarrierException e){e.printStackTrace();}
            },String.valueOf(i)).start();
        }
    }
}
