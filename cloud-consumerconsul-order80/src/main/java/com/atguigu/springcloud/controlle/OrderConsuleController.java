package com.atguigu.springcloud.controlle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud.controlle
 * @Author: shiga
 * @CreateTime: 2020-06-23 16:42
 * @Description:
 */
public class OrderConsuleController {

    public static final String INVOKE_URL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return result;
    }

}
