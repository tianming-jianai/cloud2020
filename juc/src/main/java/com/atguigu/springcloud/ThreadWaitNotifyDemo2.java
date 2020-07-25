package com.atguigu.springcloud;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirCondictioner {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condiction = lock.newCondition();

    public void incr() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                condiction.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condiction.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decr() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                condiction.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condiction.signalAll();
        } finally {
            lock.unlock();
        }

    }
}

/**
 * 题目：现在两个线程，可以操作初始值为0的一个变量
 * 实现一个线程对该变量加1，一个线程对该变量减1
 * 实现交替，来10轮，变量初始值为0
 *
 * @create 2020-07-25 16:36
 **/
public class ThreadWaitNotifyDemo2 {
    public static void main(String[] args) {
        AirCondictioner airCondictioner = new AirCondictioner();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondictioner.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondictioner.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondictioner.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondictioner.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}


