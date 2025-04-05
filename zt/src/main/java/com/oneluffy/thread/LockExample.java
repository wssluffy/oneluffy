package com.oneluffy.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private static int counter = 0;
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        // 创建并启动两个线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                // 使用Lock接口实现悲观锁
                lock.lock();
                try {
                    for (int j = 0; j < 10000; j++) {
                        counter++;
                    }
                } finally {
                    lock.unlock();
                }
            }).start();
        }

        // 主线程休眠1秒，然后打印最终的值
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final value: " + counter);
    }
}
