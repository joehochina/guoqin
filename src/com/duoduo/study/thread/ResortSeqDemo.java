package com.duoduo.study.thread;

import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

/**
 * 多线程环境中线程交替执行,由于编译器优化重排的存在,
 * 两个线程使用的变量能否保持一致性是无法确定的,结果无法预测
 * 使用Volatile可以禁止指令重排
 */
public class ResortSeqDemo {
    volatile int a=0;
    boolean flag = false;
    public void mehtod_01(){
        a =1;
        flag = true;
    }
    public void method_02(){
        if(flag){
            a = a+5;
            System.out.println("retValue"+a);
        }
    }

    public static void main(String[] args) {
        ResortSeqDemo seqDemo = new ResortSeqDemo();
        seqDemo.mehtod_01();
        seqDemo.method_02();
    }
}
