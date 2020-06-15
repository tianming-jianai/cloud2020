package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author You
 * @version 0.1.0
 * @Description
 * @create 2020-06-13 12:47
 * @since 0.1.0
 **/
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
