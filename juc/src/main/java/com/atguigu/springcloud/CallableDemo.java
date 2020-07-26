package com.atguigu.springcloud;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


class MyThread implements Runnable {

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("*******come in here");
        TimeUnit.SECONDS.sleep(4);
        return 1024;
    }
}

/**
 * 多线程中，第三种获取多线程的方法
 * <p>
 * 面试题： callable接口runnable接口的区别？
 * 答：  1. 是否有返回值(泛型)
 *      2. 是否抛出异常
 *      3. 落地方法不一样，一个是call()，一个是run()
 * <p>
 * get方法一般请放在最后一行
 * <p>
 * 新开一个线程，降低程序的堵塞
 * 不管几个线程，调用同一个对象，callable接口只执行一次，第二次是从缓存中获取数据的
 *
 *
 * <p>
 * 练武不练功，到老一场空
 * 我以为天下武功没有高低之分，只有习武之人强弱之别
 * <p>
 * 学怎用很简单，动原理很难，你值钱就是在这些原理上面
 * 大学生不值钱，工程师才值钱
 *
 * @create 2020-07-26 20:06
 **/
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyThread2());
        FutureTask futureTask2 = new FutureTask(new MyThread2());

        Instant startTime = Instant.now();
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();
        System.out.println(Thread.currentThread().getName() + "******计算完成");

        System.out.println(futureTask.get());
        Instant endTime = Instant.now();
        System.out.println(Duration.between(startTime, endTime).getSeconds());


//        long start = System.currentTimeMillis();
//        long end = System.currentTimeMillis();
//        System.out.println((end - start) / 1000);
    }
}


