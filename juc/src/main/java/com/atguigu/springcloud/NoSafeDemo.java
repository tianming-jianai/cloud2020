package com.atguigu.springcloud;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：请举例说明集合类是不安全的
 * 1. 故障现象
 * java.util.ConcurrentModificationException 并发修改异常
 * 2. 导致原因
 * <p>
 * 3. 解决方案
 * 3.1 Vector
 * 3.2 Collections.synchronizedList(new ArrayList<>());
 * 3.3 new CopyOnWriteArrayList(); 写时复制
 * <p>
 * 4. 优化建议(同样的错误，不出现2次)
 * <p>
 * Exception in thread "1" java.util.ConcurrentModificationException
 * <p>
 * vector() 线程安全，但是读取数据效率下降
 * ArrayList() 要求访问性能上升，但不在乎数据一致性
 * Collections.synchronizedList(new ArrayList<>()); 数据量小可以用，把线程不安全的转化为线程安全的
 * 避免了按下葫芦又起了瓢
 * <p>
 * 写时复制
 * CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器object[]进行copy,
 * 复制出一. 个新的容器bject[] newElements,然后新的容器0bject[] newELements里添加元素，添加完元素之后，
 * 再将原容器的引用指向新的容器setArray(newElements);. 这样做的好处是可以对Copyonwrite容器进行并发的读
 * 而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnwrite 容器也是一种读写分离的思想，读和写不同的容器
 *
 * @create 2020-07-25 23:38
 **/
public class NoSafeDemo {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
        List<String> list = new MyList();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        System.out.println(list);


    }
}


class MyList extends CopyOnWriteArrayList {
    private volatile Object[] list;
    private final Lock lock = new ReentrantLock();

    MyList() {
        list = new Object[0];
    }

    @Override
    public boolean add(Object o) {
        lock.lock();
        try {
            Object[] elements = this.list;
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = o;
            this.list = newElements;
        } finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public String toString() {
        return "MyList{" +
                "list=" + Arrays.toString(list) +
                '}';
    }
}
