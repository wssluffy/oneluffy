package com.oneluffy.zt;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
011 【查找众数及中位数】
磁盘容量排序
磁盘的容量单位常用的有M，G，T这三个等级，它们之间的换算关系为1T = 1024G，1G = 1024M，现在给定n块磁盘的容量，请对它们按从小到大的顺序进行稳定排序，例如给定5块盘的容量，1T，20M，3G，10G6T，3M12G9M排序后的结果为20M，3G，3M12G9M，1T，10G6T。注意单位可以重复出现，上述3M12G9M表示的容量即为3M+12G+9M，和12M12G相等。
输入描述:
输入第一行包含一个整数n(2 <= n <= 100)，表示磁盘的个数，接下的n行，每行一个字符串(长度大于2，小于30)，表示磁盘的容量，由一个或多个格式为mv的子串组成，其中m表示容量大小，v表示容量单位，例如20M，1T，30G，10G6T，3M12G9M。
磁盘容量m的范围为1到1024的正整数，容量单位v的范围只包含题目中提到的M，G，T三种，换算关系如题目描述。
输出描述:
输出n行，表示n块磁盘容量排序后的结果。
示例1：
输入
3
1G
2G
1024M
输出
1G
1024M
2G
说明
1G和1024M容量相等，稳定排序要求保留它们原来的相对位置，故1G在1024M之前
示例2：
输入
3
2G4M
3M2G
1T
输出
3M2G
2G4M
1T
说明
1T的容量大于2G4M，2G4M的容量大于3M2G
*/

public class DiskSorter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] disks = new String[n];
        for (int i = 0; i < n; i++) {
            disks[i] = scanner.next();
        }
        scanner.close();

        Arrays.sort(disks, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                long size1 = parseDiskSize(o1);
                long size2 = parseDiskSize(o2);
                return Long.compare(size1, size2);
            }
        });

        for (String disk : disks) {
            System.out.println(disk);
        }
    }

    private static long parseDiskSize(String diskSize) {
        long totalSizeInM = 0;
        Matcher matcher = Pattern.compile("(\\d+)([A-Z])").matcher(diskSize);
        while (matcher.find()) {
            int size = Integer.parseInt(matcher.group(1));
            char unit = matcher.group(2).charAt(0);
            switch (unit) {
                case 'M':
                    totalSizeInM += size;
                    break;
                case 'G':
                    totalSizeInM += size * 1024;
                    break;
                case 'T':
                    totalSizeInM += size * 1024 * 1024;
                    break;
            }
        }
        return totalSizeInM;
    }
}
