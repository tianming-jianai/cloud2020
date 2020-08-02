package com.atguigu.springcloud;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池优势：
 * 线程池做的工作只要是控制运行的线程数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务，如果线程数量超过了最大数量，超出数量的线程排队等候，等其他线程执行完毕，再从队列中取出任务来执行。
 * <p>
 * 主要特点：线程复用，控制最大并发数，管理线程。
 * <p>
 * 第一：降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
 * 第二：提高响应速度。当任务到达时，任务可以不需要等待线程创建就能立即执行
 * 第三：提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一分配，调优和监控。
 *
 * @author Zhangshigang
 * @version 0.1.0
 * @Description
 * @create 2020-08-02 21:33
 * @since 0.1.0
 **/
public class MyThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个工作线程，类似一个银行5个受理窗口
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1个工作线程，类似 一个银行有1个受理窗口
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个工作线程，类似银行有N个受理窗口
        //模拟10个用户来银行办理业务
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "\t" + "办理业务");
            });
            TimeUnit.SECONDS.sleep(1L);
        }

    }
}
