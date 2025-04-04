package com.oneluffy.zt;

import java.util.Scanner;

public class ReverseWordsInRange {
    public static String reverseWords(String s, int start, int end) {
        // 去除字符串前后的空格
        s = s.trim();
        if (s.isEmpty()) {
            return "EMPTY";
        }

        // 分割字符串为单词数组
        String[] words = s.split("\\s+");
        if (start < 0 || end >= words.length || start > end) {
            return "EMPTY";
        }

        // 翻转指定区间的单词
        reverseArray(words, start, end);

        // 重新拼接单词数组为字符串
        return String.join(" ", words);
    }

    private static void reverseArray(String[] array, int start, int end) {
        while (start < end) {
            String temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        int start = Integer.parseInt(sc.nextLine().trim());
        int end = Integer.parseInt(sc.nextLine().trim());
        System.out.println(reverseWords(s, start, end));
    }
}
