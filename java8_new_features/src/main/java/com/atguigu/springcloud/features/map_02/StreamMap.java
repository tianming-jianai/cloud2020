package com.atguigu.springcloud.features.map_02;

import com.atguigu.springcloud.pojo.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud.features.map_02
 * @Author: shiga
 * @CreateTime: 2020-06-29 15:18
 * @Description:
 */
public class StreamMap {
    public static void main1(String[] args) {
        List<String> alpha = Arrays.asList("Monkey", "Lion", "Giraffe", "Lemur");

        //不使用Stream管道流
        List<String> alphaUpper = new ArrayList<>();
        for (String s : alpha) {
            alphaUpper.add(s.toUpperCase());
        }
        System.out.println(alphaUpper); /* [MONKEY, LION, GIRAFFE, LEMUR] */

        // 使用Stream管道流
        List<String> collect = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
        //上面使用了方法引用，和下面的lambda表达式语法效果是一样的
        //List<String> collect = alpha.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());

        System.out.println(collect); //[MONKEY, LION, GIRAFFE, LEMUR]

        // ---------------------------------------------------
        List<Integer> lengths = alpha.stream()
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println(lengths); //[6, 4, 7, 5]

        // ---------------------------------------------------

        Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .mapToInt(String::length)
                .forEach(System.out::println);
    }

    /**
     * 处理对象数据格式转换
     *
     * @param args
     */
    public static void main2(String[] args) {
        Employee e1 = new Employee(1, 23, "M", "Rick", "Beethovan");
        Employee e2 = new Employee(2, 13, "F", "Martina", "Hengis");
        Employee e3 = new Employee(3, 43, "M", "Ricky", "Martin");
        Employee e4 = new Employee(4, 26, "M", "Jon", "Lowman");
        Employee e5 = new Employee(5, 19, "F", "Cristine", "Maria");
        Employee e6 = new Employee(6, 15, "M", "David", "Feezor");
        Employee e7 = new Employee(7, 68, "F", "Melissa", "Roy");
        Employee e8 = new Employee(8, 79, "M", "Alex", "Gussin");
        Employee e9 = new Employee(9, 15, "F", "Neetu", "Singh");
        Employee e10 = new Employee(10, 45, "M", "Naveen", "Jain");

        List<Employee> employees = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);

    /*List<Employee> maped = employees.stream()
            .map(e -> {
                e.setAge(e.getAge() + 1);
                e.setGender(e.getGender().equals("M")?"male":"female");
                return e;
            }).collect(Collectors.toList());*/

        List<Employee> maped = employees.stream()
                .peek(e -> {
                    e.setAge(e.getAge() + 1);
                    e.setGender(e.getGender().equals("M") ? "male" : "female");
                }).collect(Collectors.toList());

        System.out.println(maped);

        /// ---------------------------------------------------

        List<String> words = Arrays.asList("hello", "word");
        words.stream()
                .map(w -> Arrays.stream(w.split("")))    //[[h,e,l,l,o],[w,o,r,l,d]]
                .forEach(System.out::println);

        words.stream()
                .flatMap(w -> Arrays.stream(w.split(""))) // [h,e,l,l,o,w,o,r,l,d]
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println(squaresList);

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 获取空字符串的数量
        long count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println(count);

        List<String> strings2 = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 获取空字符串的数量
        long count1 = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println(count1);

        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }
}
