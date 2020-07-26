package com.atguigu.springcloud;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯
 * 1.业务逻辑
 * 争车位：现在只有四个车位，有7个车子要停车，
 * 车子停了一段时间之后会离开，其他车子继续争抢
 * 每一个车子最后都会得到停车的位置
 * 2.线程争抢：多对多
 * 7进4，随机的，资源被占用之后释放，可以重新抢
 * 3.原理
 * 在信号量上我们定义两种操作:
 * * acquire (获取) 当一个线程调用acquire操作时，它要么通过成功获取信号量(信号量减1)，
 * 要么一直等下去，直到有线程释放信号量，或超时。
 * *release(释放)实际上会将信号量的值加1，然后唤醒等待的线程。
 * *
 * *信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 * <p>
 * 应用场景：
 * 可以用于访问限流
 *
 * @author Zhangshigang
 * @create 2020-07-26 23:05
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(4);

        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到了车位");
                    //每个车停留一段时间
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "\t离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }, String.valueOf(i)).start();
        }
    }
}
