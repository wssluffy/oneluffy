package com.oneluffy.thread;

public class ThreadSafe extends Thread {
    public void run() {
        while (!isInterrupted()) {
            //非阻塞过程中通过判断中断标志来退出
            try {
                Thread.sleep(5 * 1000);
                // 阻塞过程捕获中断异常来退出
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
                //捕获到异常之后，执行 break跳出循环
            }
        }
    }

    public static void main(String[] args) {
        ThreadSafe threadSafe = new ThreadSafe();
        threadSafe.start();
    }
}

