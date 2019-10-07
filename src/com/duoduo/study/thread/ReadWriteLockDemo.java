package com.duoduo.study.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//资源类
class  MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    //写
    public void put(String key,Object value){
        lock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在写入"+key);
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            }catch (InterruptedException e){e.printStackTrace();}
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成"+key);
        }catch (Exception e){e.printStackTrace();}
        finally {
            lock.writeLock().unlock();
        }
    }

    //读
    public void get(String key){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取"+key);
            //暂停一会儿线程
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            }catch (InterruptedException e){e.printStackTrace();}
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成"+result);
        }catch (Exception e){e.printStackTrace();}
        finally {
            lock.readLock().unlock();
        }
    }

    public void clearMap(){
        map.clear();
    }
}
/**
 * 多个线程同时读取一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源，就不应该自由其他线程可以对该资源进行读或者写
 *
 * 总结
 *    读<--> 读能共存
 *    读<--> 写不能共存
 *    写<--> 写不能共存
 *
 * 写操作  独占#原子,中间不许被分割
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCaChe = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCaChe.put(temp + "", temp);
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myCaChe.get(finalI + "");
            }, String.valueOf(i)).start();
        }
    }
}
