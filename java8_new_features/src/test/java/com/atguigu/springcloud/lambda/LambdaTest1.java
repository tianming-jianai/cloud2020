package com.atguigu.springcloud.lambda;

/**
 * Lambda表达式使用：针对的都是接口，接口只有一个抽象方法
 * 1.举例
 * 2.格式：
 *      -> lambda操作符 或 箭头操作符
 *      左边：lambda形参列表（其实就是接口中的抽象方法的形参列表）
 *      右边：lambda体（其实就是重写的抽象方法的方法体）
 * 3.6种情况介绍
 *      无参无返回值
 *      一参无返回值
 *      数据类型可以省略，因为可由编译器推断得出，称为“类型推断”
 *      只有一个参数，括号可省略
 *      两个或以上的参数，多条语句执行，并可以有返回值
 *      lambda体只有一条语句时，return与大括号若有，都可以省略
 *
 *
 * 4.lambda表达式的本质：作为函数式接口的实例  @FunctionalInterface
 *
 * 5.如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接口
 *  我们可以在一个接口上使用@FunctionalInterface注解，这样做可以检查它是否是一个函数式接口。
 *
 * 6.所以以前用匿名实现类表示的，现在都可以用lambda表达式来写。
 *
 *
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud.lambda
 * @Author: shiga
 * @CreateTime: 2020-06-30 10:42
 * @Description: Lambda表达式的使用
 */
public class LambdaTest1 {


}
