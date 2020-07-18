package com.atguigu.springcloud;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 1.故障现象
 * java.util.ConcurrentModificationException
 * 2.导致原因
 * <p>
 * 3.解决方案
 * 3.1 new Vector<>();
 * 3.2 Collections.synchronizedList(new ArrayList<>());
 * 3.3 new CopyOnWriteArrayList<>();
 * <p>
 * 4.优化建议
 */
public class _03_NotSafe {
    public static void main(String[] args) {
//        Map<String,String> map = new HashMap();
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        //        Set<String> set = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        List<String> list = new ArrayList<>();

//        1.线程不安全的case,每次结果不一样
        /*for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }*/
        //高并发编程，就是把结果不确定的东西，变成确定的，（这就是为什么值钱的原因）

//        2.解决 不能用vector,他是线程安全的，与高并发的要求冲突
        List<String> vector = new Vector<>();
        /*for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                vector.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(vector);
            }).start();
        }*/

        List<String> collections = Collections.synchronizedList(new ArrayList<>());
        //线程安全还是不安全:安全
        /*for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                collections.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(collections);
            }).start();
        }*/

        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        //写时复制,读写分离思想的体现
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                copyOnWriteArrayList.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(copyOnWriteArrayList);
            }).start();
        }
    }
}
