
package com.oneluffy.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class CASExample {
    public static void main(String[] args) {
        // 创建一个AtomicInteger实例
        AtomicInteger atomicInteger = new AtomicInteger(0);

        // 创建并启动两个线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    int current;
                    int next;
                    do {
                        current = atomicInteger.get(); // 获取当前值
                        next = current + 1; // 计算新值
                    } while (!atomicInteger.compareAndSet(current, next)); // 使用CAS进行更新
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
