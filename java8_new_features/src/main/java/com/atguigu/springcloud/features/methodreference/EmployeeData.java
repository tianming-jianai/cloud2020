package com.atguigu.springcloud.features.methodreference;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud.features.methodreference
 * @Author: shiga
 * @CreateTime: 2020-06-30 14:55
 * @Description: 用于测试的数据
 */
public class EmployeeData {

    public static List<Employee> getEmployees(){
        List<Employee> list = new ArrayList<>();

        list.add(new Employee(1001,"马化腾",34,6000.38));
        list.add(new Employee(1001,"马云",55,5000.38));
        list.add(new Employee(1001,"刘强东",56,8000.38));
        list.add(new Employee(1001,"雷军",45,6500.38));
        list.add(new Employee(1001,"李彦宏",65,10000.38));
        list.add(new Employee(1001,"比尔盖茨",43,7300.38));
        list.add(new Employee(1001,"任正非",26,8800.38));
        list.add(new Employee(1001,"扎克伯格",35,2300.38));

        return list;
    }

}
