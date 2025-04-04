package com.oneluffy.user.zt;

import java.util.Scanner;
/*
009 【查找接口成功率最优时间段】
服务之间交换的接口成功率作为服务调用关键质量特性，某个时间段内的接口失败率使用一个数组表示，数组中每个元素都是单位时间内失败率数值，数组中的数值为0~100的整数，给定一个数值(minAverageLost)表示某个时间段内平均失败率容忍值，即平均失败率小于等于minAverageLost，找出数组中最长时间段，如果未找到则直接返回NULL。
输入描述:
输入有两行内容，第一行为{minAverageLost}，第二行为{数组}，数组元素通过空格(" “)分隔，minAverageLost及数组中元素取值范围为0~100的整数，数组元素的个数不会超过100个。
输出描述:
找出平均值小于等于minAverageLost的最长时间段，输出数组下标对，格式{beginIndex}-{endIndx}(下标从0开始)，如果同时存在多个最长时间段，则输出多个下标对且下标对之间使用空格(” ")拼接，多个下标对按下标从小到大排序。
示例1：
输入
1
0 1 2 3 4
输出
0-2
说明
A、输入解释：minAverageLost=1，数组[0, 1, 2, 3, 4]
B、前3个元素的平均值为1，因此数组第一个至第三个数组下标，即0-2
示例2：
输入
2
0 0 100 2 2 99 0 2
输出
0-1 3-4 6-7
说明
A、输入解释：minAverageLost=2，数组[0, 0, 100, 2, 2, 99, 0, 2]
B、通过计算小于等于2的最长时间段为：数组下标为0-1即[0, 0]，数组下标为3-4即[2, 2]，数组下标为6-7即[0, 2]，这三个部分都满足平均值小于等2的要求，因此输出0-1 3-4 6-7
*/

public class ZT09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        String[] arr0 = sc.nextLine().split(" ");
        int[] arr = new int[arr0.length];
        for (int i = 0; i < arr0.length; i++) {
            arr[i] = Integer.parseInt(arr0[i]);
        }
        int left = 0;
        int right = 0;
        StringBuilder sb = new StringBuilder();
        while (right < arr.length){
            if (left == right){
                right++;
            }
            if (checkAvMin(arr,left,right,num)){//需要查找到最大的数组？
                while (right <arr.length && checkAvMin(arr,left,right,num)){
                    right++;
                }
                right--;
                sb.append(left).append("-").append(right).append(" ");
                right++;
            }else {
                left++;
            }
        }
        System.out.println(sb);

    }

    //给出数组，求平均值是否小于等于某个期望值
    private static boolean checkAvMin(int[] arr,int start,int end,int target){
        double total = 0;
        for (int i = start; i < end+1; i++) {
            total += arr[i];
        }
        double res = total/(end - start +1);
        return res <= target;
    }
}
