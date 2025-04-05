package com.oneluffy.thread;

public class PessimisticLockExample {
    private static int counter = 0;

    public static void main(String[] args) {
        // 创建并启动两个线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                // 使用synchronized关键字实现悲观锁
                synchronized (PessimisticLockExample.class) {
                    for (int j = 0; j < 10000; j++) {
                        counter++;
                    }
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
