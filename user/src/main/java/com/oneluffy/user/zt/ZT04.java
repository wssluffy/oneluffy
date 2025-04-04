package com.oneluffy.user.zt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
/*
004 【VLAN资源池】
VLAN是一种对局域网设备进行逻辑划分的技术，为了标识不同的VLAN，引入VLAN ID(1-4094之间的整数)的概念。定义一个VLAN ID的资源池(下称VLAN资源池)，资源池中连续的VLAN用开始VLAN-结束VLAN表示，不连续的用单个整数表示，所有的VLAN用英文逗号连接起来。现在有一个VLAN资源池，业务需要从资源池中申请一个VLAN，需要你输出从VLAN资源池中移除申请的VLAN后的资源池。
输入描述:
第一行为字符串格式的VLAN资源池，第二行为业务要申请的VLAN，VLAN的取值范围为[1,4094]之间的整数。
输出描述:
从输入VLAN资源池中移除申请的VLAN后字符串格式的VLAN资源池，输出要求满足题目描述中的格式，并且按照VLAN从小到大升序输出。
如果申请的VLAN不在原VLAN资源池内，输出原VLAN资源池升序排序后的字符串即可。
示例1：
输入
1-5
2
输出
1,3-5
说明
原VLAN资源池中有VLAN 1、2、3、4、5，从资源池中移除2后，剩下VLAN 1、3、4、5，按照题目描述格式并升序后的结果为1,3-5。
示例2：
输入
20-21,15,18,30,5-10
15
输出
5-10,18,20-21,30
说明
原VLAN资源池中有VLAN 5、6、7、8、9、10、15、18、20、21、30，从资源池中移除15后，资源池中剩下的VLAN为 5、6、7、8、9、10、18、20、21、30，按照题目描述格式并升序后的结果为5-10,18,20-21,30。
示例3：
输入
5,1-3
10
输出
1-3,5
说明
原VLAN资源池中有VLAN 1、2、3，5，申请的VLAN 10不在原资源池中，将原资源池按照题目描述格式并按升序排序后输出的结果为1-3,5。
备注:
输入VLAN资源池中VLAN的数量取值范围为[2-4094]间的整数，资源池中VLAN不重复且合法([1,4094]之间的整数)，输入是乱序的。
*/

//  有问题？？？？？
public class ZT04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(",");
        int search = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            if (input[i].contains("-")){
                String[] num = input[i].split("-");
                int start = Integer.parseInt(num[0]);
                int end = Integer.parseInt(num[1]);
                for (int j = start; j <end+1 ; j++) {
                    list.add(j);
                }
            }else {
                list.add(Integer.parseInt(input[i]));
            }
        }

        Collections.sort(list);
        list.remove((Object) search);
        //输出
        int idx = 0;
        int start = 0;//1 3 4 5
        int tem = 1;
        StringBuffer sb = new StringBuffer();
        while (idx < list.size()){
            if (start == 0){
                start = list.get(idx);
            }
            if (idx+1 == list.size()){//保证下一位一定存在
                sb.append(start);
                break;
            }
            if (list.get(idx+1) == start+tem){//下一位与当前相等 继续后推
                idx ++;
                tem ++;
                continue;
            }else if (start == list.get(idx)){//输出当前值加,
                sb.append(start).append(",");
                start = 0;
                tem = 1;
            }else {
                sb.append(start).append("-").append(list.get(idx));
                start = 0;
                tem = 1;
                if (idx +1 < list.size()){//当前不是最后一个数据
                    sb.append(",");
                }
            }
            idx ++;
        }
        System.out.println(sb);
    }
}
