package com.atguigu.springcloud.pojo;

/**
 * @author Zhangshigang
 * @version 0.1.0
 * @Description
 * @create 2020-07-05 13:55
 * @since 0.1.0
 **/
public class Boy {
    private Girl girl;

    @Override
    public String toString() {
        return "Boy{" +
                "girl=" + girl +
                '}';
    }

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    public Boy(Girl girl) {
        this.girl = girl;
    }

    public Boy() {
    }
}
