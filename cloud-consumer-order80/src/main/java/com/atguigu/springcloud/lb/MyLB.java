package com.atguigu.springcloud.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: cloud2020
 * @BelongsPackage: com.atguigu.springcloud.lb
 * @Author: shiga
 * @CreateTime: 2020-06-24 18:20
 * @Description:
 */
@Component
@Slf4j
public class MyLB implements LoadBalance {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current > 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        log.info("******* 第几次访问，次数： " + next);
        return next;
    }

    /**
     * 负债均衡算法：rest接口第几册请求数 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务重启后rest接口技术从1开始
     * @param serviceInstances
     * @return
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
