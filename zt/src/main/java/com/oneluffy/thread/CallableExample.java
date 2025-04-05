package com.oneluffy.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个Callable任务
        Callable<Integer> task = () -> {
            System.out.println("Task is running...");
            Thread.sleep(2000);
            return 123;
        };

        // 创建一个线程池
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // 提交任务并获取Future对象
        Future<Integer> future = executor.submit(task);

        while (!future.isDone()) {
            System.out.println("Task is not done yet...");
            Thread.sleep(500);
        }

        try {
            // 获取任务的结果
            Integer result = future.get();
            System.out.println("Task result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 关闭线程池
        executor.shutdown();
    }
}
