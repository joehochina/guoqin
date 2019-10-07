package com.duoduo.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 和 lock有什么区别，用新的lock有什么好处
 *
 * 1、原始构成
 *     synchronized是关键字属于JVM层面
 *     monitorenter（底层是通过monitor对象来完成，其实wait/notify等方法也依赖于monitor对象只有在同步
 *     块或方法中才能调用wait/notify等方法）
 *     monitorexit
 *   lock是具体类java.util.concurrent.Locks.Lock)是API层面的锁
 *
 * 2、 使用方法
 *     synchronized 不需要手动释放锁，synchronized执行完后系统会自动让线程释放对锁的占用
 *     lock需要手动释放，需要lock,unlock方法配合try() finally语句块完成
 *
 * 3、 等待是否可以中断
 *     synchronized不可中断，除非抛出异常或者正常运行完成
 *     ReentrantLock可中断，1.设置超时方法 tryLock(Long timeout,TimeUnit unit)
 *         2.lockInterruptibly()放代码块中，调用interrupt()方法可中断
 *
 * 4、 加锁是否公平
 *     synchronized非公平锁
 *     ReentrantLock两者都可以，默认非公平，构造方法可以传入boolean，true公平，false非公平
 *
 * 5、 锁绑定多个条件Condition
 *    synchronized没有
 *    ReentrantLock用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized要么随机唤醒
 *    一个线程要么唤醒全部线程
 */
class ShareResouce{
    private  int number =1;//A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            //1判断
            while (number!=1){
                c1.await();
            }
            //2干活
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+number);
            }
            //3通知
            number = 2;//二号线程
            c2.signal();//通知二号线程

        }catch (Exception e){e.printStackTrace();}
        finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            //1判断
            while (number!=2){
                c2.await();
            }
            //2干活
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+number);
            }
            //3通知
            number = 3;//三号线程
            c3.signal();//通知三号线程

        }catch (Exception e){e.printStackTrace();}
        finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            //1判断
            while (number!=3){
                c3.await();
            }
            //2干活
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+number);
            }
            //3通知
            number = 1;//一号线程
            c1.signal();//通知一号线程

        }catch (Exception e){e.printStackTrace();}
        finally {
            lock.unlock();
        }
    }

}
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {

        ShareResouce shareResouce= new ShareResouce();
        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                shareResouce.print5();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                shareResouce.print10();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                shareResouce.print15();
            }
        },"C").start();
    }
}
