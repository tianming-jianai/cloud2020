package com.atguigu.springcloud;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 写入数据" + key);
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            //暂停一会线程 毫秒
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 读取数据");
            //暂停一会线程 毫秒
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成" + o);
            return o;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

/**
 * 多个线程同时读一个资源类，没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是
 * 如果一个线程想去写共享资源，就不应该再有其他线程可以对该资源进行读或写
 * 小总结：
 *      读-读 能共存
 *      读-写 不能共存
 *      写-写 不能共存
 * <p>
 * 写操作独占，读操作共享，不加锁
 * 读的时候加锁，防止
 *
 * @author Zhangshigang
 * @version 0.1.0
 * @Description
 * @create 2020-07-26 23:31
 * @since 0.1.0
 **/
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache cache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                cache.put(tempInt+"",tempInt);
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                cache.get(tempInt+"");
            },String.valueOf(i)).start();
        }

    }
}
