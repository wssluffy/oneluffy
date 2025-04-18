package com.oneluffy.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);

        Future<String> submit = pool.submit(() -> Thread.currentThread().getName());

        String s = submit.get();
        System.out.println(s);

        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable callable = new MyCallable(i + "");
            Future submit1 = pool.submit(callable);
            futures.add(submit1);
        }

        pool.shutdown();

        for (Future future : futures) {
            System.out.println(future.get());
        }

    }
}
