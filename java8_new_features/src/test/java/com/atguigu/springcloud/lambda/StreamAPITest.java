package com.atguigu.springcloud.lambda;

import com.atguigu.springcloud.features.methodreference.Employee;
import com.atguigu.springcloud.features.methodreference.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1.Steam关注的是对数据的运算，与CPU打交道
 * 集合关注的是数据的存储，与内存打交道
 * <p>
 * 2. Stream
 * 自己不会存储元素
 * 不会改变源对象，相反，他们会返回一个有结果的新Stream
 * 操作是延迟的，这意味着他们会等到需要结果的时候才执行
 * <p>
 * 3.Stream执行流程
 * Stream实例化
 * 一系列的中间操作
 * 终止操作
 * <p>
 * 4.说明
 * 一个中间操作链，对数据源进行处理
 * 一旦执行终止操作，就执行中间操作链，并产生结果，之后不会再使用
 *
 * @author Zhangshigang
 * @version 0.1.0
 * @Description
 * @create 2020-07-04 10:41
 * @since 0.1.0
 **/
public class StreamAPITest {

    /**
     * 创建Stream方式一：通过集合
     */
    @Test
    public void test() {
        List<Employee> employeeList = EmployeeData.getEmployees();

        // default Stream<E> stream()  返回一个顺序流
        Stream<Employee> stream = employeeList.stream();

        // default Stream<E> parallelStream();返回一个并行流
        Stream<Employee> parallelStream = employeeList.parallelStream();
    }

    /**
     * 创建Stream方式一：通过数组
     */
    @Test
    public void test2() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        //调用Arrays类的static<T> Stream<T> stream(T[] array)返回一个流
        IntStream stream = Arrays.stream(arr);

        Employee e1 = new Employee(1001, "Tom");
        Employee e2 = new Employee(1002, "Jerry");
        Employee[] arr2 = new Employee[]{e1, e2};
        Stream<Employee> stream1 = Arrays.stream(arr2);

    }

    /**
     * 创建Stream方式一：通过Stream的of()
     */
    @Test
    public void test3() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
    }

    /**
     * 创建Stream方式一：通过创建无限流
     */
    @Test
    public void test4() {
        //迭代
        // public static<T> Stream<T> iterate(final T seed, final UnarryOperator<T> f)
        //遍历前十个偶数
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

        //生成
        // public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
