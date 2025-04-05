package com.oneluffy.executor;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        // 创建一个CopyOnWriteArrayList实例
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        
        // 添加元素
        list.add("1");
        list.add("2");
        list.add("3");
        
        System.out.println("Initial list: " + list);
        
        // 删除元素
        list.remove("2");
        System.out.println("List after removal: " + list);
        
        // 更新元素
        list.set(1, "4");
        System.out.println("List after update: " + list);
        
        // 遍历元素
        for (String item : list) {
            System.out.println("Item: " + item    )  ;}
    }
}
