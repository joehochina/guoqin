package com.duoduo.study.jvm;

/**
 * 虚拟机栈(栈帧中的局部变量区,也叫做局部变量表
 * 方法区中的类静态属性引用的对象。
 * 方法区中常量引用的对象
 * 本地方法栈中JNI( Native方法)引用的对象
 */
public class GCRootDemo {

    private byte[] byteArray = new byte[100*024*1024];

    public static void m1(){
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("completed the first GC");
    }

    public static void main(String[] args) {
        System.out.println("sa");
        m1();
    }
}
