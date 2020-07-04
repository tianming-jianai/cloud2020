package com.atguigu.springcloud.lambda;

import com.atguigu.springcloud.features.methodreference.Employee;
import com.atguigu.springcloud.features.methodreference.EmployeeData;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 测试Stream的终止操作
 *
 * @author Zhangshigang
 * @version 0.1.0
 * @Description
 * @create 2020-07-04 21:31
 * @since 0.1.0
 **/
public class StreamAPITest2 {
    /**
     * 1-匹配与查找
     */
    @Test
    public void test() {
        List<Employee> employees = EmployeeData.getEmployees();

        //allMatch(Precidate p) 检查是否匹配所有元素
        //ps:是否所有员工都大于18岁
        boolean b = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(b);

        //anyMatch(Predicate p)检查是否至少匹配一个元素
        boolean b1 = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(b1);

        // noneMatch(Precidate p) 检查是否没有匹配的元素
        boolean b2 = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(b1);

        //findFirst 返回第一个元素
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first.get());

        //findAny 返回当前流中的任意元素
        Optional<Employee> any = employees.stream().findAny();
        System.out.println(any.get());
    }

    @Test
    public void test2() {
        List<Employee> employees = EmployeeData.getEmployees();
        //count返回流中元素的个数
        long count = employees.stream().filter(e -> e.getSalary() > 8000).count();
        System.out.println(count);

        //max(Comparator c)返回流中最大值

        //min(Comparator c)返回流中最小值

        //forEach(Consumer c) 内部迭代

        //使用集合的遍历操作(使用Collection接口需要用户去做迭代，成为外部迭代)
        employees.forEach(System.out::println);
    }

    /**
     * 2-归约
     */
    @Test
    public void test3() {
        //reduce(T identity,BinaryOperator b)  可以将流中元素反复结合起来，得到一个值，返回T
        //ps:计算1-10的和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce);

        //reduce(BinaryOperator b)可以将流中元素反复结合起来，得到一个值，返回Optional<T>
        //ps:计算所有员工工资总和
        List<Employee> employees = EmployeeData.getEmployees();
        //损失精度
        Optional<BigDecimal> reduce1 = employees.stream().map(Employee::getSalary).map(BigDecimal::new).reduce(BigDecimal::add);
        //方法一：将结果转换成String类型，再使用BigDecimal（String s）构造方法
        Optional<BigDecimal> reduce2 = employees.stream().map(Employee::getSalary).map(String::valueOf).map(BigDecimal::new).reduce(BigDecimal::add);
        //使用BigDecimal.valueOf()方法
        Optional<BigDecimal> reduce3 = employees.stream().map(Employee::getSalary).map(BigDecimal::valueOf).reduce(BigDecimal::add);
        System.out.println(reduce3);
        //自定义Reduce
        Optional<Double> reduce4 = employees.stream().map(Employee::getSalary).reduce((s1, s2) -> s1 + s2);
        System.out.println(reduce4);

    }

    /**
     * 3-收集
     */
    @Test
    public void test4() {
        //collect(Collector c) 将流转换为其他形式，接收一个Collector接口的实现，用于给
        //ps:查找工资大于6000的员工，结果返回一个List或Set
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("**************");
        employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toSet()).forEach(System.out::println);
    }
}
