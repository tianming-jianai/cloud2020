package com.atguigu.springcloud;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    private int number = 30;
    private Lock lock = new ReentrantLock();

    /**
     * 加锁之后就会顺序执行方法
     */
    /*public synchronized void saleTicket() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出第" + number-- + "还剩下" + number);
        }
    }*/
    public void saleTicket() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第" + number-- + "还剩下" + number);
            }
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 并发：多个线程，同一时间点，争夺同一资源
 * 并行：
 * 多线程的企业级套路+模板
 * 1. 在高内聚的前提下，线程   操作(对外暴露的调用方法)   资源类
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.saleTicket();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.saleTicket();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.saleTicket();
        }, "C").start();
    }
}
