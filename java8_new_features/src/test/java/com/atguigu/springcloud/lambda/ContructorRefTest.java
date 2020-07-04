package com.atguigu.springcloud.lambda;

import com.atguigu.springcloud.pojo.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、构造器引用
 * 和方法的引用相似，函数式接口的抽象方法的形参列表和构造器的形参列表一致
 * 抽象方法的返回值类型即为构造器所属的类型
 * <p>
 * 二、数组引用
 * 数组引用可以看做是一个特殊的类，写法与构造器引用一致
 *
 * @author Zhangshigang
 * @version 0.1.0
 * @Description
 * @create 2020-07-04 09:58
 * @since 0.1.0
 **/
public class ContructorRefTest {

    /**
     * 构造器引用
     * Supplier中的T get()
     */
    @Test
    public void test() {
        Supplier<Employee> sup1 = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        System.out.println(sup1.get());

        Supplier<Employee> sup2 = () -> new Employee();
        System.out.println(sup2.get());

        Supplier<Employee> sup3 = Employee::new;
        System.out.println(sup3.get());
    }

    /**
     * Function中的R apply(T t)
     */
    @Test
    public void test2() {
        Function<Integer, Employee> func1 = id -> new Employee(id);
        Employee emp = func1.apply(1001);
        System.out.println(emp);

        Function<Integer, Employee> func2 = Employee::new;
        System.out.println(func2.apply(1002));
    }

    /**
     * BiFunction中的R apply(T t,U u)
     */
    @Test
    public void test3() {
        BiFunction<Integer, Integer, Employee> func1 = (id, age) -> new Employee(id, age);
        System.out.println(func1.apply(1001, 22));

        BiFunction<Integer, Integer, Employee> func2 = Employee::new;
        System.out.println(func2.apply(1002, 33));

    }

    /**
     * 数组引用
     * Function中的R apply(T t)
     */
    @Test
    public void test4() {
        Function<Integer, String[]> func1 = length -> new String[length];
        String[] arr1 = func1.apply(5);
        System.out.println(Arrays.toString(arr1));

        Function<Integer, String[]> func2 = String[]::new;
        System.out.println(Arrays.toString(func2.apply(10)));
    }
}
