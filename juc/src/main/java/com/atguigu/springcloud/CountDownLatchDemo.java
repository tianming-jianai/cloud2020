package com.atguigu.springcloud;

import java.util.concurrent.CountDownLatch;

/**
 * 1. 业务逻辑：
 *  上自习课，其他人都走完了，班长才锁门走人
 * 2.解决方法
 *  CountDownLatch
 *
 *
 * 阳哥学JUC3遍，用了一年
 * JUC的源码时国外一个大神觉得sun公司的这个源码写的太烂了，处于爱好给他写的更好了
 *
 * @author Zhangshigang
 * @create 2020-07-26 22:16
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        int count = 6;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t"+"离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t" + "班长关门走人");
    }
}
