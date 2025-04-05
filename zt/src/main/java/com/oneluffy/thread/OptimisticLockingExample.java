package com.oneluffy.thread;

import lombok.Getter;
import lombok.Setter;

public class OptimisticLockingExample {
    public static void main(String[] args) {
        // 假设我们有一个User类，它有一个版本号字段
        User user = new User("Alice", 1);

        // 线程1读取用户数据
        new Thread(() -> {
            // 获取当前的数据和版本号
            String name = user.getName();
            int version = user.getVersion();

            // 模拟一些操作
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 尝试更新数据
            if (user.getVersion() == version) {
                user.setName(name + " updated by Thread 1");
                user.setVersion(version + 1);
                System.out.println("Thread 1 updated user: " + user);
            } else {
                System.out.println("Thread 1 failed to update user because the version has changed.");
            }
        }).start();

        // 线程2读取用户数据
        new Thread(() -> {
            // 获取当前的数据和版本号
            String name = user.getName();
            int version = user.getVersion();

            // 尝试更新数据
            if (user.getVersion() == version) {
                user.setName(name + " updated by Thread 2");
                user.setVersion(version + 1);
                System.out.println("Thread 2 updated user: " + user);
            } else {
                System.out.println("Thread 2 failed to update user because the version has changed.");
            }
        }).start();
    }
}

@Setter
@Getter
class User {
    private String name;
    private int version;

    public User(String name, int version) {
        this.name = name;
        this.version = version;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", version=" + version +
                '}';
    }
}
