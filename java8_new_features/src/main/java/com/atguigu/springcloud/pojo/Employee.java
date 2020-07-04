package com.atguigu.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Predicate;

@Data
@AllArgsConstructor
public class Employee {

    private Integer id;
    /* 年龄 */
    private Integer age;
    /* 性别 */
    private String gender;
    private String firstName;
    private String lastName;

    public Employee() {
    }

    public Employee(Integer id) {
        this.id = id;
    }

    public Employee(Integer id, Integer age) {
        this.id = id;
        this.age = age;
    }

    public static Predicate<Employee> ageGreaterThan70 = x -> x.getAge() > 70;


    public static Predicate<Employee> genderM = x -> x.getGender().equals("M");
}