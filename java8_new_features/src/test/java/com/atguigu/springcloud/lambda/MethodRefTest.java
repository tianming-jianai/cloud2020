package com.atguigu.springcloud.lambda;

import com.atguigu.springcloud.features.methodreference.Employee;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 1.使用情景：当要传递给lambda体的操作，已经有实现的方法了，可以使用方法引用
 * <p>
 * 2.方法引用，本质上是Lambda表达式，而Lambda表达式作为函数式接口的实例。所以
 * 方法引用，也是函数式接口的实例。
 * <p>
 * 3.使用格式:  类(或对象) ::
 * <p>
 * 4.具体为如下三种形式：
 * 对象 :: 非静态方法
 * 类   :: 静态方法
 * 类   :: 非静态方法
 * <p>
 * 5.方法引用的使用要求：要求接口中的抽象方法的形参列表和返回值类型与方法引用的方法的形参列表和返回值类型相同！
 *
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud.lambda
 * @Author: shiga
 * @CreateTime: 2020-06-30 15:00
 * @Description: 方法引用的使用
 */
public class MethodRefTest {

    /**
     * 情况1   类 :: 非静态方法
     * Consumer 中的 void accept(T t)
     * PrintStream 中的 void println(T t)
     */
    @Test
    public void test1() {
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("北京");
        System.out.println("********************************");
        PrintStream ps = System.out;
        Consumer<String> ps2 = ps::println;
        ps2.accept("南京");
    }

    /**
     * 情况2   对象   :: 非静态方法
     * Supplier 中的 T get()
     * Employee 中的 String getName()
     */
    @Test
    public void test2() {
        Employee emp = new Employee(101, "Tom", 22, 5000);
        Supplier<String> su = () -> emp.getName();
        System.out.println(su.get());
        System.out.println("*******************");
        Supplier<String> su2 = emp::getName;
        System.out.println(su2.get());
    }

    /**
     * 情况3   类   :: 静态方法
     */
    @Test
    public void test3() {
        Comparator<Integer> com = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(com.compare(1, 2));
        System.out.println("**************");
        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(1, 2));

    }

    @Test
    public void test4() {
        Function<Double, Long> func = d -> Math.round(d);
        Long apply = func.apply(12.3);
        System.out.println(apply);
        System.out.println("***********");
        Function<Double, Long> func2 = Math::round;
        System.out.println(func2.apply(22.6));
    }

    /**
     * 情况三：类::实例方法(有难度)
     * Comparator中的int compare(T t1,T t2)
     * String中的int t1.compareTo(t2)
     * 参数t1作为方法的调用者
     */
    @Test
    public void test5() {
        Comparator<String> com = (s1, s2) -> s1.compareTo(s2);
        System.out.println(com.compare("c", "a"));
        System.out.println("******************");
        Comparator<String> com2 = String::compareTo;
        System.out.println(com2.compare("c", "a"));
    }

    /**
     * BiPredicate中的boolean test(T t1,T t2)
     * String 中的 boolean t1.equals(t2)
     */
    @Test
    public void test6() {

    }

    /**
     * Function中的R apply(T t)
     * Employee中的String getName()
     */
    @Test
    public void test7(){
        Employee employee = new Employee(101,"James",22,6999);
        Function<Employee,String> func = e -> e.getName();
        System.out.println(func.apply(employee));
        System.out.println("******************");
        Function<Employee,String> func2  = Employee::getName;
        System.out.println(func2.apply(employee));
    }

}
