package com.oneluffy.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class OptimisticLockExample {
    public static void main(String[] args) {
        // 创建一个AtomicInteger实例
        AtomicInteger atomicInteger = new AtomicInteger(0);

        // 创建并启动两个线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    // 使用compareAndSet方法实现乐观锁
                    atomicInteger.compareAndSet(atomicInteger.get(), atomicInteger.get() + 1);
                }
            }).start();
        }

        // 主线程休眠1秒，然后打印最终的值
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final value: " + atomicInteger.get());
    }
}
