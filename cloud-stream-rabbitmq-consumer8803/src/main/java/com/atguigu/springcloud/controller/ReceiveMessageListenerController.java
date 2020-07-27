package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Stream消息驱动之消费者
 *
 * Queue studyExchange.anonymous.KnoQH8q8Ts28KRDVeJkcrA
 * Queue studyExchange.anonymous.k08coW_mTUG6ZGhg1HDHTA
 *
 * 故障现象：重复消费
 * 导致原因：默认分组group是不同的，组流水号不一样，被认为不同组，可以消费
 * 解决方法：自定义配置分为同一个组，解决重复消费问题
 *
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud.controller
 * @Author: 张世罡
 * @CreateTime: 2020-07-27 14:47
 * @Description:
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者2号，------>接收到的消息：" + message.getPayload() + "\t port:" + serverPort);
    }
}
