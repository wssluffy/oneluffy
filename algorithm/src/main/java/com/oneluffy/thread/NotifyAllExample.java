package com.oneluffy.thread;

public class NotifyAllExample {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        // 创建并启动两个线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                synchronized (lock) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " is waiting...");
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + " is running...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        // 主线程休眠1秒，然后唤醒所有等待的线程
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
