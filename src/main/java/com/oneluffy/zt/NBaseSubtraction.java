package com.oneluffy.zt;

import java.util.Scanner;

public class NBaseSubtraction {
  public static void main(String[] args) {
    // 示例输入
//    int base = 2;
//    String num1 = "11";
//    String num2 = "1";
    Scanner sc = new Scanner(System.in);
    String[] input = sc.nextLine().split(" ");
    int base = Integer.parseInt(input[0]);
    String num1 = input[1];
    String num2 = input[2];
    // 调用减法方法
    subtractNBase(base, num1, num2);
  }

  public static void subtractNBase(int base, String num1, String num2) {
    if (!isValid(base, num1) || !isValid(base, num2)) {
      System.out.println("-1");
      return;
    }

    // 将字符串转换为数值数组
    int[] values1 = stringToValues(num1, base);
    int[] values2 = stringToValues(num2, base);

    // 确保被减数不小于减数
    boolean isNegative = compare(values1, values2, base) < 0;
    if (isNegative) {
      // 如果被减数小于减数，交换数值并标记结果为负数
      int[] temp = values1;
      values1 = values2;
      values2 = temp;
    }

    // 执行减法运算
    int[] result = new int[values1.length];
    int borrow = 0;
    for (int i = values1.length - 1; i >= 0; i--) {
      int diff = values1[i] - borrow - (i >= values2.length ? 0 : values2[i]);
      if (diff < 0) {
        diff += base;
        borrow = 1;
      } else {
        borrow = 0;
      }
      result[i] = diff;
    }

    // 移除前导零
    int startIndex = 0;
    while (startIndex < result.length - 1 && result[startIndex] == 0) {
      startIndex++;
    }

    // 将结果数组转换回字符串
    StringBuilder resultStr = new StringBuilder();
    for (int i = startIndex; i < result.length; i++) {
      resultStr.append(Character.forDigit(result[i], base));
    }

    // 输出结果
    System.out.println((isNegative ? 1 : 0) + " " + resultStr);
  }

  private static boolean isValid(int base, String num) {
    if (num.isEmpty() || (num.length() > 1 && num.charAt(0) == '0')) {
      return false;
    }
    for (char c : num.toCharArray()) {
      int value = Character.digit(c, base);
      if (value == -1 || value >= base) {
        return false;
      }
    }
    return true;
  }

  private static int[] stringToValues(String num, int base) {
    int[] values = new int[num.length()];
    for (int i = 0; i < num.length(); i++) {
      values[i] = Character.digit(num.charAt(i), base);
    }
    return values;
  }

  private static int compare(int[] values1, int[] values2, int base) {
    if (values1.length != values2.length) {
      return values1.length - values2.length;
    }
    for (int i = 0; i < values1.length; i++) {
      if (values1[i] != values2[i]) {
        return values1[i] - values2[i];
      }
    }
    return 0;
  }
}
