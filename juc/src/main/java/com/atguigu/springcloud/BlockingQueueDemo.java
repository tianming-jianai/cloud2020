package com.atguigu.springcloud;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞：必须要阻塞/不得不阻塞
 * 当队列是空的，从队列中获取元素的操作将会被阻塞
 * 当队列是满的，从队列中添加元素的操作将会被阻塞
 * 试图从空的列中获取元素的线程将会被阻塞，直到其他线程往空的队列插入新的元素
 * 试图向已满的队列中添加新的线程将会被阻塞，直到其他线程从队列中移除一个或多个元素或者完全清空，使队列变得空闲起来并后续新增
 *
 * 为什么需要BlockingQueue
 * 好处是我们不需要高效什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切BlockingQueue都给你一手包办了
 *
 * -------------------------------------------------------------------------------------------------
 *
 * BlockingQueue核心方法
 * 方法类型     抛出异常        特殊值     阻塞      超时
 * 插入       add(e)          offer(e)    put(e)  offer(e,time,unit)
 * 移除       remove()        poll()      take()  pull(time,unit)
 * 检查       element()       peek()      不可用     不可用
 *
 * 抛出异常
 *      当阻塞队列满时，再往队列里add插入元素会抛legalStateException:Queue full
 *      当阻塞队列空时，再往队列里remove移除元素会抛NoSuchElementException
 * 特殊值
 *      插入方法，成功ture失败false
 *      移除方法，成功返回出队列的元素，队列里没有就返回null
 * 一直阻塞
 *      当阻塞队列满时，生产者线程继续往队列里put元素， 队列会一 直阻塞生产者线程直到put数据or响应中断退出
 *      当阻塞队列空时，消费者线程试图从队列里take元素，队列会一 直阻塞消费者线程直到队列可用
 * 超时退出
 *      当阻塞队列满时，队列会阻塞生产者线程一 定时间，超过限时后生产者线程会退出
 *
 * 程序要要高效的勤奋，牛逼的程序员均是懒惰的 少写就不要多写
 * @author Zhangshigang
 * @create 2020-07-27 21:06
 **/
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("d"));

//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        new Thread(()->{blockingQueue.add("d");},"D").start();
//        System.out.println(blockingQueue.take());

//        System.out.println(blockingQueue.element());

//        System.out.println(blockingQueue.offer("a"));
//        System.out.println(blockingQueue.offer("b"));
//        System.out.println(blockingQueue.offer("c"));
//        System.out.println(blockingQueue.offer("d"));

//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());

//        blockingQueue.put("a");
//        blockingQueue.put("b");
//        blockingQueue.put("c");
//        blockingQueue.put("d");

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a",3L, TimeUnit.SECONDS));


    }
}
