package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud
 * @Author: shiga
 * @CreateTime: 2020-06-29 15:03
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Java8Main {
    public static void main(String[] args) {
        SpringApplication.run(Java8Main.class, args);
    }
}
