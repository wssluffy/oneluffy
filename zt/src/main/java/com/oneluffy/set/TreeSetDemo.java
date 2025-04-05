package com.oneluffy.set;

import java.util.LinkedHashMap;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class TreeSetDemo {

    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet();
        treeSet.add("1");
        treeSet.add("2");
        treeSet.add("a");
        treeSet.add("b");
        treeSet.add("c");
        treeSet.add("3");
        treeSet.add("d");

        treeSet.forEach(System.out::println); // 1 2 3 a b c d  自动排序

        String join = String.join(",", treeSet); // 1,2,3,a,b,c,d
        System.out.println(join);

        String collect = treeSet.stream().collect(Collectors.joining(",")).toString();
        System.out.println(collect); // 1,2,3,a,b,c,d


        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("1", "1");
        map.put("a", "a");
        map.put("b", "b");
        map.put("2", "2");

        map.forEach((k, v) -> System.out.println(k + " " + v)); // 1 1 a a b b 2 2


    }
}
