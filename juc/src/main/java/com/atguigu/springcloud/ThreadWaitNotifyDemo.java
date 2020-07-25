package com.atguigu.springcloud;

/**
 * 题目：现在两个线程，可以操作初始值为0的一个变量
 * 实现一个线程对改变了加1，一个线程对改变了减1
 * <p>
 * 1. 高内聚低耦合的情况下  线程   操作  资源类
 * 2. 判断  干活  通知
 * 3. 多线程交互中，必须要防止多线程的虚假唤醒，也即(判断只用while，不能用if)
 *
 * @create 2020-07-25 15:09
 **/
class AirConditioner {
    private int num = 0;

    public synchronized void incr() throws InterruptedException {

        //判断 if判断会出现虚假唤醒
        /*if (num != 0) {
            this.wait();
        }*/
        while (num != 0) {
            this.wait();
        }
        //干活
        num++;
        //通知
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        this.notifyAll();

    }

    public synchronized void decr() throws InterruptedException {

        /*if (num == 0) {
            this.wait();
        }*/
        while (num == -0) {
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        this.notifyAll();

    }
}

public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    airConditioner.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    airConditioner.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    airConditioner.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    airConditioner.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}


