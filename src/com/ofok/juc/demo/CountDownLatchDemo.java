package com.ofok.juc.demo;

import java.util.concurrent.CountDownLatch;

/**
 * 代码源于生活，高于生活
 * 计数器减法
 * @author ofok
 * @date 2020-04-19 18:45:33
 */
public class CountDownLatchDemo {
    /**
     *  有些任务是不得不阻塞的， 减法计数器
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <= countDownLatch.getCount(); i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " start ");
                // 出去一个人计数器就减 -1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        // 阻塞等待计数器归零
        countDownLatch.await();
        // 阻塞的操作： 计数器 num++
        System.out.println(Thread.currentThread().getName() + " === END ");
    }

    /**
     *  结果诡异的嘛？ 达不到预期的 Main end 在最后一个
     */
    public static void test1() {
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" start ");
            }, String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName() + " End ");
    }
}