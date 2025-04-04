package com.oneluffy.user.zt;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//003 【TLV解码】
//TLV编码是按[Tag Length Value]格式进行编码的，一段码流中的信元用Tag标识，Tag在码流中唯一不重复，Length表示信元Value的长度，Value表示信元的值。
//码流以某信元的Tag开头，Tag固定占一个字节，Length固定占两个字节，字节序为小端序。
//现给定TLV格式编码的码流，以及需要解码的信元Tag，请输出该信元的Value。
//输入码流的16机制字符中，不包括小写字母，且要求输出的16进制字符串中也不要包含小写字母；码流字符串的最大长度不超过50000个字节。
//输入描述:
//输入的第一行为一个字符串，表示待解码信元的Tag；
//输入的第二行为一个字符串，表示待解码的16进制码流，字节之间用空格分隔。
//输出描述:
//输出一个字符串，表示待解码信元以16进制表示的Value。
//示例1：
//输入
//31
//  32 01 00 AE 90 02 00 01 02 30 03 00 AB 32 31 31 02 00 32 33 33 01 00 CC
//  输出
//32 33
//说明
//需要解析的信元的Tag是31，从码流的起始处开始匹配，Tag为32的信元长度为1（01 00，小端序表示为1）；第二个信元的Tag是90，其长度为2；第三个信元的Tag是30，其长度为3；第四个信元的Tag是31，其长度为2（02 00），所以返回长度后面的两个字节即可，即32 33。

/**
 * TLV解码
 * tag 一个字节 //第一行
 * length占2个字节
 * 字节为小端序
 * 1各字节占8位 length 16位
 * 1个16进制数 占2进制多少位？ 4位 所以length 占4个数
 *  * //32 00 01 AE
 * //90 00 02 01 02
 * //30 00 03 AB 32 31
 * //31 00 02 32 33
 * //33 0 01 CC
 *  */
public class ZT03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String tag = sc.nextLine();//需要解析的tag
        String stream = sc.nextLine();//所有的tag内容
        Map<String,Inner> map = new HashMap<>();
        //分割所有码流数据，塞到map中
        String[] input = stream.split(" ");
        int idx = 0;
        while (idx < input.length){
            String tagTemp = input[idx];
            String lengthStr = input[idx+2] + input[idx+1];
            idx += 3;
            //计算内容的长度
            int length = Integer.parseInt(lengthStr,16);
            StringBuilder content = new StringBuilder();
            for (int i = idx; i < idx + length; i++) {
                if (i == idx+length -1){
                    content.append(input[i]);
                }else {
                    content.append(input[i]).append(" ");
                }
            }
            map.put(tagTemp,new Inner(length,content.toString()));
            idx += length;
        }

        Inner inner = map.get(tag);
        System.out.println(inner.content);
    }

    static class Inner{
        private int length;
        private String content;

        public Inner(int length, String content) {
            this.length = length;
            this.content = content;
        }
    }
}
