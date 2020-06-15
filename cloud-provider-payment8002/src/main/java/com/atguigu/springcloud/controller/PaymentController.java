package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author You
 * @version 0.1.0
 * @Description
 * @create 2020-06-13 15:07
 * @since 0.1.0
 **/
@RestController
@Slf4j
public class PaymentController {

    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        log.info(payment.toString());
        int result = paymentService.create(payment);
        log.info("插入的结果是：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败,serverPort：" + serverPort, null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：" + payment.toString());
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort：" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录,id:" + id + ",serverPort：" + serverPort, null);
        }
    }
}
