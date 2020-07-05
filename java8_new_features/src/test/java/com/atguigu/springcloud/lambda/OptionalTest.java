package com.atguigu.springcloud.lambda;

import com.atguigu.springcloud.pojo.Boy;
import com.atguigu.springcloud.pojo.Girl;
import org.junit.Test;

import java.util.Optional;

/**
 * 到目前为止，臭名昭著的空指针异常是导致Java应用程序失败的最常见原因。
 * 以前，为了解决空指针异常，Google公司著名的Guava项目引入了Optional类，
 * Guava通过使用检查空值的方式来防止代码污染，它鼓励程序员写更干净的代
 * 码。受到Google Guava的启发，Optional类 已经成为Java 8类库的一部分。
 * <p>
 * Optional<T>类(java. util.Optional)是-一个容器类，它可以保存类型T的值，代表
 * 这个值存在。或者仅仅保存null,表示这个值不存在。原来用null表示一个值不
 * 存在，现在Optional可以更好的表达这个概念。并且可以避免空指针异常。
 * <p>
 * Optional类的Javadoc描述如下:这是一个可以为null的容器对象。如果值存在
 * 则isPresent()方法会返回true,调用get()方法 会返回该对象。
 *
 * @author Zhangshigang
 * @version 0.1.0
 * @Description
 * @create 2020-07-04 22:46
 * @since 0.1.0
 **/
public class OptionalTest {

//    创建Optional类对象的方法:
//        Optional.of(T t):创建一个Optional实例，t必须非空;
//        Optional.empty() :创建一个空 的Optional实例
//        Optional.ofNullable(T t): t可以为nul

    /**
     *
     */
    @Test
    public void test() {
        Girl girl = new Girl();
//        girl=null;
        Optional<Girl> optionalGirl = Optional.of(girl);
        System.out.println(optionalGirl);
    }

    /**
     *
     */
    @Test
    public void test2() {
        Girl girl = new Girl();
        girl = null;
        // ofNullable(T t) t可以为null
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);

        //orElse(T t1) 如果当前的Optional内部封装的t是非空的，则返回内部的t
        //如果内部的t是空的，则返回orElse()方法中的参数t1
        Girl girl1 = optionalGirl.orElse(new Girl("赵丽颖"));
        System.out.println(girl1);
    }

    public String getGirlName(Boy boy) {
        return boy.getGirl().getName();
    }

    //优化
    public String getGirlName1(Boy boy) {
        if (boy != null) {
            Girl girl = boy.getGirl();
            if (girl != null) {
                return girl.getName();
            }
        }
        return null;
    }

    /**
     *
     */
    @Test
    public void test3() {
        Boy boy = new Boy();
        String girlName = getGirlName1(boy);
        System.out.println(girlName);
    }

    /**
     * 使用OptionalOptional类的getGirlName()
     *
     * @param boy
     * @return
     */
    public String getGirlName2(Boy boy) {
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        //做完下面这一步，boy1一定非空
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("迪丽热巴")));

        Girl girl = boy1.getGirl();
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        Girl girl1 = girlOptional.orElse(new Girl("古力娜扎"));
        return girl1.getName();

    }

    /**
     *
     */
    @Test
    public void test4() {
        Boy boy = null;
        String grilName = getGirlName2(boy);
        System.out.println(grilName);

        Boy boy2 = new Boy();
        System.out.println(getGirlName2(boy2));

        Boy boy3 = new Boy(new Girl("苍老师"));
        System.out.println(getGirlName2(boy3));
    }

//    判断Optional容器中是否包含对象:
//        boolean isPresent() :判断是否包含对象
//        void ifPresent(Consumer<? super T> consumer): 如果有值，就执行Consumer接口的实现代码，并且该值会作为参数传给它。


//    获取Optional容器的对象:
//        T get():如果调用对象包含值，返回该值，否则抛异常
//        T orElse(T other):如果有值则将其返回，否则返回指定的other对象。
//        T orElseGet(Supplier<? extends T> other):如果有值则将其返回，否则返回由Supplier接口实现提供的对象。
//        T orElse Throw(Supplier<? extends X> exceptionSupplier):如果有值则将其返回，否则抛出由Supplier接口实现提供的异常。


}
