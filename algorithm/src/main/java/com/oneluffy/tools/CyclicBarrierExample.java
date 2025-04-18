package com.oneluffy.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        // 创建一个CyclicBarrier实例，计数器的初始值为2
        CyclicBarrier barrier = new CyclicBarrier(2);

        // 创建并启动两个线程
        new Thread(new Worker(barrier)).start();
        new Thread(new Worker(barrier)).start();
    }

    static class Worker implements Runnable {
        private final CyclicBarrier barrier;

        Worker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                // 模拟工作线程正在执行任务
                System.out.println(Thread.currentThread().getName() + " is working...");
                Thread.sleep(2000);

                // 工作线程到达屏障点，调用await()方法
                System.out.println(Thread.currentThread().getName() + " is waiting at the barrier.");
                barrier.await();

                // 所有线程都到达屏障点后，继续执行任务
                System.out.println(Thread.currentThread().getName() + " has crossed the barrier.");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
