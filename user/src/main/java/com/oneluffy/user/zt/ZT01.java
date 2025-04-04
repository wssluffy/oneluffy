package com.oneluffy.user.zt;

import java.util.Scanner;

//001 【5键键盘的输出】
//有一个特殊的5键键盘，上面有a，ctrl-c，ctrl-x，ctrl-v，ctrl-a五个键。a键在屏幕上输出一个字母a；ctrl-c将当前选择的字母复制到剪贴板；ctrl-x将当前选择的字母复制到剪贴板，并清空选择的字母；ctrl-v将当前剪贴板里的字母输出到屏幕；ctrl-a选择当前屏幕上的所有字母。注意：
//  1剪贴板初始为空，新的内容被复制到剪贴板时会覆盖原来的内容
//2当屏幕上没有字母时，ctrl-a无效
//3当没有选择字母时，ctrl-c和ctrl-x无效
//4当有字母被选择时，a和ctrl-v这两个有输出功能的键会先清空选择的字母，再进行输出
//给定一系列键盘输入，输出最终屏幕上字母的数量。
//输入描述:
//输入为一行，为简化解析，用数字1 2 3 4 5代表a，ctrl-c，ctrl-x，ctrl-v，ctrl-a五个键的输入，数字用空格分隔 输出描述:
//输出一个数字，为最终屏幕上字母的数量
//示例1：
//输入 1 1 1
//输出 3
//说明 连续键入3个a，故屏幕上字母的长度为3
//示例2：
//输入 1 1 5 1 5 2 4 4
//输出 2
//说明 输入两个a后ctrl-a选择这两个a，再输入a时选择的两个a先被清空，所以此时屏幕只有一个a，后续的ctrl-a，ctrl-c选择并复制了这一个a，最后两个ctrl-v在屏幕上输出两个a，故屏幕上字母的长度为2（第一个ctrl-v清空了屏幕上的那个a）

public class ZT01 {
    private static String screen = "";
    private static String choose = "";
    private static String jianQie = "";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        for (int i = 0; i < input.length; i++) {
            operate(Integer.parseInt(input[i]));
        }
        System.out.println(screen.length());
    }//a

    public static void operate(int num){
        if (num == 1){//a screen输入一个a
            if (choose.equals("")){
                screen += "a";
            }else {
                choose = "";
                screen = "a";
            }
            return;
        }
        if (num == 2){//ctrl-c
            jianQie = choose;
            return;
        }
        if (num == 3){//ctrl-x
            jianQie = choose;
            choose = "";
            screen = "";
            return;
        }
        if (num ==4){//ctrl-v
            if (choose.equals("")){
                screen += jianQie;
            }else {
                screen = jianQie;
                choose = "";
            }
            return;
        }
        if (num == 5){//ctrl-a
            choose = screen;
        }
    }
}
