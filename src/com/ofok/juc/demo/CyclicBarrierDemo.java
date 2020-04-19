package com.ofok.juc.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 代码源于生活，高于生活
 * 回环栅栏 计数器加法
 *
 * @author ofok
 * @date 2020-04-19 18:45:33
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        // 集齐7个龙珠召唤神龙 ++ 1//
        // 等待cyclicBarrier计数器满，就执行后面的Runnable，不满就阻塞
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, new Runnable() {
            @Override
            public void run() {
                System.out.println("神龙召唤成功！");
            }
        });
        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集了第" + temp + "颗龙珠");
                try {
                    cyclicBarrier.await();
                    // 等待 阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
