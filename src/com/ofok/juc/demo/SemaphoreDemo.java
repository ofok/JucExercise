package com.ofok.juc.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 代码源于生活，高于生活
 * 信号量（信号灯） 抢车位
 *
 * @author ofok
 * @date 2020-04-19 18:45:33
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 模拟6个车，只有3个车位
        // 3个位置
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                // 得到车位
                try {
                    semaphore.acquire(); // 得到
                    System.out.println(Thread.currentThread().getName() + "抢到了车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放位置
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
