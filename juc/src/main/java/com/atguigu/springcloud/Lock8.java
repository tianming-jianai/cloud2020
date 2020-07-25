package com.atguigu.springcloud;


class Phone {
    public synchronized void sendEmail() {
        System.out.println("------sendEmail");
    }

    public synchronized void sendSMS() {
        System.out.println("------sendSMS");
    }

    public void hello() {
        System.out.println("------hello");
    }
}

/**
 * 题目：多线程加锁
 * 1. 标准访问，请问先打印邮件，还是短信？
 * 2. 邮件方法暂停4秒钟，请问先打印邮件，还是短信？
 * 3. 新增一个普通方法hello()
 * 4. 2部手机，请问先打印邮件还是短信？
 * 5. 两个静态同步方法，1部手机，请问先打印邮件还是短信？
 * 6. 两个静态同步方法，2部手机，请问先打印邮件还是短信？
 * 7. 一个普通同步方法，一个静态同步方法，1部手机，请问先打印邮件还是短信？
 * 8. 一个普通同步方法，一个静态同步方法，2部手机，请问先打印邮件还是短信？
 *
 * 同一时间段，同一对象，只能有一个线程访问
 * synchronized修饰方法，锁的是当前对象
 * static 锁的是类 模板
 *
 * synchronized实现同步的基础: Java中的每一个对象 都可以作为锁。
 * 具体表现为以3种形式。
 * 对于普通同步方法，锁是当前实例对象。
 * 对于静态同步方法，锁是当前类的Class对象。
 * 对于同步方法块，锁是Synchonized括号里配置的对象。
 *
 * 当一个线程试图访问同步代码块时，它首先必须得到锁，退出或抛出异常时必须释放锁。
 *
 * 可是别的实例对象的普通同步方法因为跟该实例对象的普通同步方法用的是不同的锁，
 * 所以毋须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。
 *
 * 所有的静态同步方法用的也是同一把锁 - 类对象本身，
 * 这两把锁(this/Class) 是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
 *
 * 但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，
 * 而不管是同一个实例对象的静态同步方法之间，
 * 还是不同的实例对象的静态同步方法之间，只要它们同一个类的实例对象!
 *
 *
 * @create 2020-07-25 20:58
 **/
public class Lock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(4);//java8的新的睡觉时间类
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            phone.sendEmail();
    }, "A").start();

        //为了达到效果：A线程先启动 B线程后启动
        Thread.sleep(100);

        new Thread(() -> {
            phone.sendSMS();
//            phone.hello();
//            phone2.sendSMS();
        }, "B").start();
    }
}

/**
 * 懂了民族国家事，打到日本心更坚
 * 提笔墙上写大字，大家要知持久战
 */
