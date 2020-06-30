package com.atguigu.springcloud.lambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * java内置的4大核心函数式接口
 * <p>
 * 消费型接口 Consumer<T> void accept(T t)
 * 供给型接口 Supplier<T> T get()
 * 函数型接口 Function<T,R> R apply(T t)
 * 断定型接口 Predicate<T> boolean test(T t)
 *
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud.lambda
 * @Author: shiga
 * @CreateTime: 2020-06-30 14:15
 * @Description:
 */
public class LambdaTest2 {

    @Test
    public void test1() {
        happyTime(500D, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("天上人间一瓶水" + aDouble + "元");
            }
        });
        System.out.println("***************************");
        happyTime(400D, money -> System.out.println("天上人间一口水" + money + "元"));
    }

    public void happyTime(Double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    @Test
    public void test2() {
        List<String> list = Arrays.asList("北京", "南京", "天津", "东京", "洗净", "普京");
        List<String> filtered = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(filtered);

        System.out.println("**********************************");

        List<String> filtered2 = filterString(list, s -> s.contains("京"));
        System.out.println(filtered2);

    }

    /**
     * 根据给定的规则，过滤集合中的字符串，此规则由Predicate的方法决定
     *
     * @param list
     * @param predicate
     * @return
     */
    public List<String> filterString(List<String> list, Predicate<String> predicate) {
        List<String> filterList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                filterList.add(s);
            }
        }
        return filterList;
    }

}
