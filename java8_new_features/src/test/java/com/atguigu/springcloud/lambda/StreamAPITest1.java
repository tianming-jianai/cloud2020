package com.atguigu.springcloud.lambda;

import com.atguigu.springcloud.features.methodreference.Employee;
import com.atguigu.springcloud.features.methodreference.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 测试Stream的中间操作
 *
 * @author Zhangshigang
 * @version 0.1.0
 * @Description
 * @create 2020-07-04 14:55
 * @since 0.1.0
 **/
public class StreamAPITest1 {

    /**
     * 筛选与切片
     */
    @Test
    public void test() {
        List<Employee> list = EmployeeData.getEmployees();
        //filter(Precidate p)  接收lambda从流中排出某些元素
        Stream<Employee> stream = list.stream();
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);
        System.out.println("********************");

        //limit(n) 截断流，使其元素不超过给定数量
        list.stream().limit(3).forEach(System.out::println);
        System.out.println("********************");

        //skip(n) 跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流
        list.stream().skip(3).forEach(System.out::println);
        System.out.println("********************");

        //distinct 筛选 通过流所生成元素的hashCode()和equals()去除重复元素
        list.add(new Employee(1001, "马化腾", 34, 6000.38));
        list.stream().distinct().forEach(System.out::println);

    }

    /**
     * 映射
     * map(Function f) 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     */
    @Test
    public void test2() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff");
        list.stream().map(e -> e.toUpperCase()).forEach(System.out::println);

        //ps:获取员工姓名长达大于3的员工的姓名
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().map(Employee::getName).filter(name -> name.length() > 3).forEach(System.out::println);

        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::stringToStream);
//        streamStream.forEach(s->s.forEach(System.out::println));
        //flatMap(Function f)  接收一个函数作为参数，将流中的每个值都换成一个流，然后把所有流连城一个流。
        list.stream().flatMap(StreamAPITest1::stringToStream).forEach(System.out::println);
    }

    public static Stream<Character> stringToStream(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    /**
     *
     */
    @Test
    public void test3() {
        List list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);
        list1.add(list2);
        System.out.println(list1);
        list2.addAll(list1);
        System.out.println(list2);

    }

    /**
     * 排序 sorted 自然排序
     */
    @Test
    public void test4() {
        //sorted()自然排序
        List<Integer> list = Arrays.asList(12, 43, 65, 34, 87, 0, -98, 3);
        list.stream().sorted().forEach(System.out::println);

        // Employee cannot be cast to class java.lang.Comparable
        //List<Employee> employees = EmployeeData.getEmployees();
        //employees.stream().sorted().forEach(System.out::println);

        //sorted(Comparator com) 定制排序
        List<Employee> employees = EmployeeData.getEmployees();
        //employees.stream().sorted((e1, e2) -> -Integer.compare(e1.getAge(), e2.getAge())).forEach(System.out::println);
        employees.stream().sorted((e1, e2) -> {
            int ageValue = Integer.compare(e1.getAge(), e2.getAge());
            if (ageValue != 0) {
                return ageValue;
            } else {
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
        }).forEach(System.out::println);

    }


}
