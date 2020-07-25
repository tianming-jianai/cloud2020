package com.atguigu.springcloud;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    /**
     * 标志位：
     * 1：A 2：B 3：C
     */
    private int number = 1;


    public void print5() {
        lock.lock();
        try {
            //判断
            while (number != 1) {
                condition1.await();
            }
            //干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知 修改标志位
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            //判断
            while (number != 2) {
                condition2.await();
            }
            //干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知 修改标志位
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            //判断
            while (number != 3) {
                condition3.await();
            }
            //干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知 修改标志位
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 * <p>
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * ... 来10轮
 * <p>
 * 1. 高内聚低耦合前提下，线程  操作  资源类
 * 2. 判断  干活  通知
 * 3. 多线程中，必须要防止多线程的虚假唤醒，也即（判断只用while,不能用if）
 * 4. 标志位
 * <p>
 * <p>
 * 所以：Lock的特点，精准通知，精准唤醒
 *
 * @create 2020-07-25 16:57
 **/
public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) shareResource.print5();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) shareResource.print10();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) shareResource.print15();
        }, "C").start();
    }
}
