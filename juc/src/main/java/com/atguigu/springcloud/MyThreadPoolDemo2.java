package com.atguigu.springcloud;

/**
 * ----------------------线程池7大参数-----------------------------------------
 * 1、corePoolSize: 线程池中的常驻核心线程数
 * 2、maximumPoolSize: 线程池中能够容纳同时执行的最大线程数，此值必须大于等于1
 * 3、keepAliveTime: 多余的空闲线程的存活时间当前池中线程数量超过corePoolSize时，当空闲时间达到keepAliveTime时，多余线程会被销毁直到只剩下corePoolSize个线程为止
 * 4、unit: keepAliveTime的单位
 * 5、workQueue: 任务队列，被提交但尚未被执行的任务
 * 6、threadFactory: 表示生成线程池中工作线程的线程工厂,用于创建线程，-般默认的即可
 * 7、handler: 拒绝策略,表示当队列满了，并且工作线程大于等于线程池的最大线程数(maximumPoolSize) 时如何来拒绝请求执行的runnable的策略
 * <p>
 * ----------------------线程池原理-----------------------------------------
 * 1、在创建了线程池后，开始等待请求。
 * 2、当调用execute ()方法添加一个请求任务时，线程池会做出如下判断:
 * 2.1如果正在运行的线程数量小于corePoolSize，那么马上创建线程运行这个任务;
 * 2.2如果正在运行的线程数量大于或等于corePoolSize，那么将这个任务放入队列;
 * 2.3如果这个时候队列满了且正在运行的线程数量还小于maximumPoolSize，那么还是要创建非核心线程立刻运行这个任务;
 * 2.4如果队列满了且正在运行的线程数量大于或等于maximumPoolSize,那么线程池会启动饱和拒绝策略来执行。
 * 3、当一个线程完成任务时，它会从队列中取下一个任务来执行。
 * 4、当一个线程无事可做超过一 定的时间(keepAliveTime) 时，线程会判断:
 *
 * @author Zhangshigang
 * @create 2020-08-02 22:50
 **/
public class MyThreadPoolDemo2 {

}
