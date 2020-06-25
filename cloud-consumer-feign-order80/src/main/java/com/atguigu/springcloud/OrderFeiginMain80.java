package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud
 * @Author: shiga
 * @CreateTime: 2020-06-24 19:47
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OrderFeiginMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeiginMain80.class, args);
    }
}
