package com.atguigu.springcloud;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Zhangshigang
 * @version 0.1.0
 * @Description
 * @create 2020-07-23 21:08
 * @since 0.1.0
 **/
public class demo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
//            Thread.sleep(300);
            executorService.submit(()->{
                System.out.println(Thread.currentThread().getName()+" - 执行任务");
            });
        }
    }
}
