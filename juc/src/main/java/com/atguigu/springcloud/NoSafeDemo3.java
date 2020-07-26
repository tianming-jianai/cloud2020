package com.atguigu.springcloud;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HashMap必须掌握，别 给你机会，你不中用啊
 * HashMap 构造方法：初始值16，负载因子0.75
 * HashMap组成：Node,单向链表，红黑树
 * <p>
 * <p>
 * 传授经验：电商高并发不会用HashMap();
 * 这个表中查出的数据单月不会超出1000条，可以初始值就设置为1000，一次性给到位，就省去了扩容变化的时间
 * Map<String,String> map2 = new HashMap<>(1000);
 * <p>
 * 每周做一次学习周报总结
 * 周一到周六都学了啥，逐条理理，自己做脑图试试，有一条过一条，形成自己的周报总结
 * 重点是什么，（别人家问你了：学没学，学了，学了什么，不知道）
 * 理工男数据说话，一切都要有时间进度计划表，要可量化
 * 红色：不懂，黄色：半懂，绿色：懂了
 * <p>
 * 脑子想一想，自己说一说，回了就涂绿色，卡壳的要做标记
 * 用Excel做总结,标注这周工作完成度75%
 * <p>
 * 养成良好的工作习惯，落实什么工作，有理有据，
 * <p>
 * 人生如程序，不是选择就是循环
 * <p>
 * 面试项目经理的头5问要扛得住。  笔试，技术，HR
 *
 * @create 2020-07-26 19:20
 **/
public class NoSafeDemo3 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        Map<String, String> map2 = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t" + UUID.randomUUID().toString().substring(0, 8));
            }, String.valueOf(i));
        }
    }
}
