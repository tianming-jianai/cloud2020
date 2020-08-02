package com.atguigu.springcloud;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * 拒绝策略：
 *      等待队列已经满了，再也塞不下新任务了，同时，线程池中的max也达到了，无法继续为新任务服务
 *
 *      AbortPolicy(默认):直接抛出RejectedExecutionException异常阻止 系统正常运行
 *      CallerRunsPolicy:“调用者运行“一 种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量。
 *      DiscardOldestPolicy:抛弃队列中等待最久的任务,然后把当前任务加人队列中尝试再次提交当前任务。
 *      DiscardPolicy:该策略默默地丢弃无法处理的任务,不予任何处理也不抛出异常。如果允许任务丢失，这是最好的一种策略。
 *
 *  线程池那个用的多：那个都不用，实际工作中都是自定义的
 *
 * 设置初始线程数：
 *      CPU密集型：CPU核心数+1
 *      IO密集型：
 * @author Zhangshigang
 * @create 2020-08-02 23:32
 **/
public class MyThreadPoolDemo3 {
    public static void main(String[] args) {
        System.out.println("CPU核心数："+Runtime.getRuntime().availableProcessors());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,
                5,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
//                new ThreadPoolExecutor.CallerRunsPolicy()
//                new ThreadPoolExecutor.DiscardOldestPolicy()
//                new ThreadPoolExecutor.DiscardPolicy()
        );
        try {
            for (int i = 0; i < 10; i++) {
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t" + "办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
