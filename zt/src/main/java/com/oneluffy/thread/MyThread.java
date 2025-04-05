package com.oneluffy.thread;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread:" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        myThread = new MyThread();
        myThread.start();
    }
}
