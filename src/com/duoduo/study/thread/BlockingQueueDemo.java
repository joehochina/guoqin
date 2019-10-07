package com.duoduo.study.thread;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue是一个基于数组结构的有界阻塞队列，此队列FIFO原则对元素进行排序
 * LinkedBockingQueue一个基于链表结构的阻塞队列，队列FIFO原则对元素进行排序，吞吐量高于ArrayBlockingQueue
 * SynichronousQueue一个不存储元素的阻塞队列，每个插入操作必须等到宁一个线程调用移除操作，否则插入操作一直处于阻塞队列，吞吐量
 * 要高于LinkedBockingQueue
 *
 * 1,队列
 *
 * 2、阻塞队列
 *   2.1  有没有好的一面
 *
 *   2.2 不得不阻塞，你如何管理
 *
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception{
//        blockingQueueSample();
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(()->{
            try {
                try {
                    TimeUnit.SECONDS.sleep(5);
                }catch (InterruptedException e){e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());

                try {
                    TimeUnit.SECONDS.sleep(5);
                }catch (InterruptedException e){e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());

                try {
                    TimeUnit.SECONDS.sleep(5);
                }catch (InterruptedException e){e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();
    }

    private static void blockingQueueSample() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println("====================");
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));

        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println("====================");
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        System.out.println("====================");
//        blockingQueue.put("b");
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
    }
}
