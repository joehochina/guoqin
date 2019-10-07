package com.duoduo.study.thread;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockA+"\t尝试获取："+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){e.printStackTrace();}
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockB+"\t尝试获取："+lockA);
            }
        }

    }
}

/**
 * 死锁是指两个或两个以上的进程在执行的过程中
 * 因争夺资源而造成的一种互相等待的现象
 * 若无外力干涉那他们将无法推进下去
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        new Thread(new HoldLockThread("lockA","lockB"),"Thread AA").start();
        new Thread(new HoldLockThread("lockB","lockA"),"Thread BB").start();
    }

    /**
     * 查看进程，发现死锁线程
     * linux ps -ef
     * window jps -l 结合 jstack "进程编号"
     */
}
