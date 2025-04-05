package com.oneluffy.thread;

public class NotifyExample {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        // 创建并启动一个线程
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread is waiting...");
                    lock.wait();
                    System.out.println("Thread is running...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        // 主线程休眠1秒，然后唤醒thread线程
        try {
            Thread.sleep(1000);
            System.out.println("Thread is 主线程休眠1秒，然后唤醒thread线程...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock) {
            lock.notify();
        }
    }
}
