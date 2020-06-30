package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud.service
 * @Author: shiga
 * @CreateTime: 2020-06-26 15:35
 * @Description:
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfoOk(Integer id) {
        return "------------- PaymentFallbackService fall paymentInfoOk o(╥﹏╥)o";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "------------- PaymentFallbackService fall paymentInfoTimeout o(╥﹏╥)o";
    }
}
