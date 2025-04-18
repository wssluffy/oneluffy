package com.oneluffy.thread;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class MyCallable implements Callable<String> {

    String s;

    public MyCallable(String s) {
        this.s = s;
    }

    @Override
    public String call() {
        return "hello callable:" + s;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable("world");
        String call = myCallable.call();
        System.out.println(call);
    }

}
