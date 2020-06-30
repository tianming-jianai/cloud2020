package com.atguigu.springcloud.features.pridicate_01;

import com.atguigu.springcloud.pojo.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamFilterPredicate {

    public static void main1(String[] args) {
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

        List<Employee> filtered = employees.stream()
                .filter(e -> e.getAge() > 70 && e.getGender().equals("M"))
                .collect(Collectors.toList());
        System.out.println(filtered);

        List<Employee> filtered2 = employees.stream().filter(Employee.ageGreaterThan70.and(Employee.genderM)).collect(Collectors.toList());
        System.out.println(filtered2);

        List<Employee> filtered3 = employees.stream()
                .filter(Employee.ageGreaterThan70.or(Employee.genderM))
                .collect(Collectors.toList());
        System.out.println(filtered3);

        List<Employee> filtered4 = employees.stream()
                .filter(Employee.ageGreaterThan70.or(Employee.genderM).negate())
                .collect(Collectors.toList());
        System.out.println(filtered4);
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        /*System.out.println("输出所有数据:");
        eval(list, n -> true);*/

//        System.out.println("输出所有偶数:");
//        eval(list, n -> n % 2 == 0);

        System.out.println("输出大于 3 的所有数字:");
        eval(list, n-> n > 3 );

    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

}