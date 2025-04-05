package com.oneluffy.tools;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个CountDownLatch实例，计数器的初始值为2
        CountDownLatch latch = new CountDownLatch(2);

        // 创建并启动两个线程
        new Thread(new Worker(latch)).start();
        new Thread(new Worker(latch)).start();

        // 主线程等待，直到计数器的值变为0
        latch.await();

        System.out.println("All workers have finished their tasks.");
    }

    static class Worker implements Runnable {
        private final CountDownLatch latch;

        Worker(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                // 模拟工作线程正在执行任务
                System.out.println(Thread.currentThread().getName() + " is working...");
                Thread.sleep(2000);

                // 工作线程完成了它的任务，调用countDown()方法
                System.out.println(Thread.currentThread().getName() + " has finished its task.");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
