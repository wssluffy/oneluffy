package com.oneluffy.zt;

import java.util.Scanner;

public class ZT06 {

    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    int start = Integer.parseInt(sc.nextLine());
    int end = Integer.parseInt(sc.nextLine());
    System.out.println(reverseWords(input, start, end));
  }

  private static String reverseWords(String str, int start, int end) {
    // 去除字符串前后的空格
    str = str.trim();
    if (str.isEmpty()) {
      return "EMPTY";
    }

    // 分割字符串为单词数组
    String[] content = str.split(" ");
    if (start < 0 || end >= content.length || start > end) {
      return "EMPTY";
    }
    while (start < end) {
      String temp;
      temp = content[end];
      content[end] = content[start];
      content[start] = temp;
      start++;
      end--;
    }
    // 重新拼接单词数组为字符串
    return String.join(" ", content);
  }
}
