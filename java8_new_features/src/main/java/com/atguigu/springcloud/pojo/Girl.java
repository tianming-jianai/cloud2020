package com.atguigu.springcloud.pojo;

/**
 * @author Zhangshigang
 * @version 0.1.0
 * @Description
 * @create 2020-07-05 13:54
 * @since 0.1.0
 **/
public class Girl {
    private String name;

    @Override
    public String toString() {
        return "Girl{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Girl(String name) {
        this.name = name;
    }

    public Girl() {
    }
}
