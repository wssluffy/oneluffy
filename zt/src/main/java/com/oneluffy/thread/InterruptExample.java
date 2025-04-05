package com.oneluffy.thread;

public class InterruptExample {
    public static void main(String[] args) {
        // 创建并启动一个线程
        Thread thread = new Thread(() -> {
            try {
                // 模拟长时间运行的任务
                System.out.println("Task is running...");
                Thread.sleep(5000);
                System.out.println("Task is finished.");
            } catch (InterruptedException e) {
                // 当线程被中断时，Thread.sleep()方法将抛出InterruptedException
                System.out.println("Task is interrupted.");
            }
        });
        thread.start();

        // 主线程休眠1秒，然后中断thread线程
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
