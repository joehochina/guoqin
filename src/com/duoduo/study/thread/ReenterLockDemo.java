package com.duoduo.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{

    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getId()+"\t====invoked sendSMS");
        sendEmail();
    }
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getId()+"\t====invoked sendEmail");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run(){
        get();
    }

    public void get(){
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t====invoked get()");
            set();
        }catch (Exception e){e.printStackTrace();}
        finally {
            lock.unlock();
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t====invoked set()");

        }catch (Exception e){e.printStackTrace();}
        finally {
            lock.unlock();
        }
    }
}

/**
 * 可重入锁（也叫递归锁）
 *
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 * 也就是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块
 *
 * case one synchronized 就是一个典型的可重入锁
 * t1 invoked sendSMS()
 * t1 invoked sendEmail() t1进入内层方法的时候会自动获取锁
 * t2 invoked sendSMS()
 * t2 invoked sendEmail() t2进入内层方法的时候会自动获取锁
 *
 * case two
 * ReentrantLock
 *
 */
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendSMS();
        },"t1").start();
        new Thread(()->{
            phone.sendSMS();
        },"t2").start();
        System.out.println("***********************************");
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){e.printStackTrace();}
        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");
        t3.start();
        System.out.println("***********************************");
        t4.start();
    }
}
