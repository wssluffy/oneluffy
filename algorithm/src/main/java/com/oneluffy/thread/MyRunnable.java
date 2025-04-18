package com.oneluffy.thread;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("myRunnable:" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

        new Thread(() -> System.out.println("myRunnable:" + Thread.currentThread().getName()));
    }
}
