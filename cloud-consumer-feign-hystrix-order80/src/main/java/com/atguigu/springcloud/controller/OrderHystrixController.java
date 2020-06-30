package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud.controller
 * @Author: shiga
 * @CreateTime: 2020-06-26 01:45
 * @Description:
 */
@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "payment_Global_Fallback_Method")
public class OrderHystrixController {

    @Resource
    PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfoOk(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
   /* @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })*/
//   @HystrixCommand
    public String paymentInfoTimeout(@PathVariable("id") Integer id) {
//        int i = 10 / 0;
        String result = paymentHystrixService.paymentInfoTimeout(id);
        return result;
    }

    public String paymentInfoTimeoutHandler(Integer id) {
        return "我是消费者80,对方支付系统繁忙，请10秒后再试，或者程序运行出错，请检查自己o(╥﹏╥)o";
    }

    /**
     * 全局Fallback
     * @param id
     * @return
     */
    public String payment_Global_Fallback_Method() {
        return "Global异常处理信息，请稍后再试，o(╥﹏╥)o";
    }

}
