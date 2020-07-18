package com.atguigu.springcloud;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud
 * @Author: shiga
 * @CreateTime: 2020-07-18 21:17
 * @Description: 题目：三个售票员        卖出             30张票
 * 笔记：如何编写企业级多线程代码
 * 固定的编程模板套路+模板是什么？
 * 1.在高内聚的前提下，线程         操作           资源类
 * 2.别人已经写过的方法，不要自己再用synchronized封装，避免重复造轮子，说明你对API不了解
 * 3.写代码，要先完成，后完美，尽量使用新特性完成代码
 */
public class _01_SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

/**
 * 资源类：实例变量+实例方法
 */
class Ticket {
    private Integer number = 30;

    Lock l = new ReentrantLock();

    public void sale() {
        l.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出第" + number-- + "\t当前剩余票数:" + number);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            l.unlock();
        }
    }

}
