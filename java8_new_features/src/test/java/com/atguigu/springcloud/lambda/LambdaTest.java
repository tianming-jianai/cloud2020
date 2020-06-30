package com.atguigu.springcloud.lambda;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

/**
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud.lambda
 * @Author: shiga
 * @CreateTime: 2020-06-30 10:27
 * @Description:
 */
public class LambdaTest {
    @Test
    public void test1(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };
        r.run();

        System.out.println("*************************");

        Runnable r2 = () -> System.out.println("我爱北京故宫");
        r2.run();
    }

    @Test
    public void test2(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        int compare = com1.compare(2, 1);
        System.out.println(compare);

        Comparator<Integer> com2 = Integer::compare;
        int compare1 = com2.compare(1, 2);
        System.out.println(compare1);
    }
}
