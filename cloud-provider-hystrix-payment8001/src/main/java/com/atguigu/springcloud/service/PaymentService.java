package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud.service
 * @Author: shiga
 * @CreateTime: 2020-06-25 21:53
 * @Description:
 */
@Service
public class PaymentService {

    /**
     * 正常访问，肯定OK
     *
     * @param id
     * @return
     */
    public String paymentInfoOk(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "\t paymentInfo_Ok\t id:" + id + "\t O(∩_∩)O哈哈~";
    }

    public String paymentInfoTimeOut(Integer id) {
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "\t paymentInfo_TimeOut\t id:" + id + "\t 耗时：" + timeNumber + "\t O(∩_∩)O哈哈~";
    }

}
