package com.atguigu.springcloud.lambda;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud.lambda
 * @Author: shiga
 * @CreateTime: 2020-06-30 10:27
 * @Description:
 */
public class LambdaTest {
    @Test
    public void test1() {
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
    public void test2() {
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare = com1.compare(2, 1);
        System.out.println(compare);

        Comparator<Integer> com2 = Integer::compare;
        int compare1 = com2.compare(1, 2);
        System.out.println(compare1);
    }

    /**
     * 默认方法： andThen(Consumer<? super T> after)，先消费然后在消费，
     * 先执行调用andThen接口的accept方法，然后在执行andThen方法参数after中的accept方法。
     */
    @Test
    public void test3() {
        Consumer<String> consumer1 = s -> System.out.print("车名：" + s.split(",")[0]);
        Consumer<String> consumer2 = s -> System.out.println("-->颜色：" + s.split(",")[1]);

        String[] strings = {"保时捷,白色", "法拉利,红色"};
        for (String string : strings) {
            consumer1.andThen(consumer2).accept(string);
        }
    }

    /**
     * 默认方法：compose(Function<? super V, ? extends T> before)，
     * 先执行compose方法参数before中的apply方法，然后将执行结果传递给调用compose函数中的apply方法在执行。
     */
    @Test
    public void test4() {
        Function<Integer, Integer> function1 = e -> e * 2;
        Function<Integer, Integer> function2 = e -> e * e;

        Integer apply2 = function1.compose(function2).apply(3);
        System.out.println(apply2);
    }

    /**
     * ndThen(Function<? super R, ? extends V> after)，
     * 先执行调用andThen函数的apply方法，然后在将执行结果传递给andThen方法after参数中的apply方法在执行。它和compose方法整好是相反的执行顺序。
     */
    @Test
    public void test5() {
        Function<Integer, Integer> function1 = e -> e * 2;
        Function<Integer, Integer> function2 = e -> e * e;

        Integer apply3 = function1.andThen(function2).apply(3);
        System.out.println(apply3);
    }

    /**
     * 默认方法：
     * and(Predicate<? super T> other)，相当于逻辑运算符中的&&，当两个Predicate函数的返回结果都为true时才返回true。
     */
    @Test
    public void test6() {
        Predicate<String> predicate1 = s -> s.length() > 0;
        Predicate<String> predicate2 = Objects::nonNull;
        boolean test = predicate1.and(predicate2).test("&&测试");
        System.out.println(test);
    }

    /**
     * or(Predicate<? super T> other) ,
     * 相当于逻辑运算符中的||，当两个Predicate函数的返回结果有一个为true则返回true，否则返回false。
     */
    @Test
    public void test7() {
        Predicate<String> predicate1 = s -> false;
        Predicate<String> predicate2 = Objects::nonNull;
        boolean test = predicate1.and(predicate2).test("||测试");
        System.out.println(test);
    }

    /**
     * negate()，这个方法的意思就是取反。
     */
    @Test
    public void test8() {
        Predicate<String> predicate = s -> s.length() > 0;
        boolean result = predicate.negate().test("取反");
        System.out.println(result);
    }

    /**
     * isEqual(Object targetRef)，对当前操作进行"="操作,即取等操作,可以理解为 A == B。
     */
    @Test
    public void test9() {
        boolean test1 = Predicate.isEqual("test").test("test");
        boolean test2 = Predicate.isEqual("test").test("equal");
        System.out.println(test1);   //true
        System.out.println(test2);   //false
    }
}
