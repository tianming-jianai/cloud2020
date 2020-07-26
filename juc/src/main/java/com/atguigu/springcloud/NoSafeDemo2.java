package com.atguigu.springcloud;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Set 不安全
 * HashSet底层时HashMap
 * HashSet的add方法是调用HashMap的put方法，第二个参数是一个固定常量new Object()
 * HashMap底层是 Node，单方向的链表，红黑树
 *
 * @create 2020-07-26 01:57
 **/
public class NoSafeDemo2 {
    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                new HashSet<>();
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
