package com.atguigu.springcloud;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud
 * @Author: shiga
 * @CreateTime: 2020-07-19 00:07
 * @Description:
 */
public class _05_Lock8Demo {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();
        Thread.sleep(100);
        new Thread(()->{
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}

class Phone {
    public synchronized void sendSms() throws Exception{
        TimeUnit.SECONDS.sleep(4);
        System.out.println("******sendSms*******");
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println("******sendEmail*******");
    }
}
