package com.oneluffy.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample1 {


    //    在这个示例中，我们创建了一个CyclicBarrier实例，计数器的初始值为3。
//    然后我们创建了3个工作线程，每个线程在完成它的任务后会调用await()方法。
//    当3个工作线程都到达屏障点后，它们将被释放，并继续执行任务。在这个例子中，每个线程在到达屏障点后都会进行汇总计算。
    public static void main(String[] args) {
        // 创建一个CyclicBarrier实例，计数器的初始值为3
        // 当3个线程都调用了await()方法后，屏障将被打破，所有线程将继续执行
        CyclicBarrier barrier = new CyclicBarrier(3);

        // 创建并启动3个线程
        for (int i = 0; i < 3; i++) {
            new Thread(new Worker(barrier)).start();
        }
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
                // 进行汇总计算
                System.out.println(Thread.currentThread().getName() + " is doing the final computation...");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " has finished the final computation.");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
