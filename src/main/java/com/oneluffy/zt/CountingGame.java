package com.oneluffy.zt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
007 【报数游戏】
  100个人围成一圈，每个人有一个编码，编号从1开始到100。他们从1开始依次报数，报到为M的人自动退出圈圈，然后下一个人接着从1开始报数，直到剩余的人数小于M。请问最后剩余的人在原先的编号为多少？
输入描述:
输入一个整数参数M
输出描述:
如果输入参数M小于等于1或者大于等于100，输出“ERROR!”；否则按照原先的编号从小到大的顺序，以英文逗号分割输出编号字符串
示例1：
输入
3
输出
58,91
说明
输入M为3，最后剩下两个人
示例2：
输入
4
输出
34,45,97
说明
输入M为4，最后剩下三个人
*/

public class CountingGame {
  public static String lastRemaining(int m) {
    if (m <= 1 || m >= 100) {
      return "ERROR!";
    }

    List<Integer> people = new ArrayList<>();
    for (int i = 1; i <= 100; i++) {
      people.add(i);
    }

    int index = 0;
    while (people.size() >= m) {
      index = (index + m - 1) % people.size();
      people.remove(index);
    }

    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < people.size(); i++) {
      result.add(people.get((index + i) % people.size()));
    }

    // 排序
    result.sort(Integer::compareTo);

    return result.toString().replaceAll("\\[|\\]", "").replaceAll(", ", ",");
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int m = sc.nextInt();
    System.out.println(lastRemaining(m));
  }
}
