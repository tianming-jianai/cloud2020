package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud.controller
 * @Author: shiga
 * @CreateTime: 2020-06-22 17:20
 * @Description: 支付控制器
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/consul")
    public String paymentconsul() {
        return "Spring Cloud with consul : " + serverPort + "\t" + UUID.randomUUID();
    }
}
