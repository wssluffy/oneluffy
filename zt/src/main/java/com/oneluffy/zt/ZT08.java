package com.oneluffy.zt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ZT08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] num = sc.nextLine().split(",");
        int ma = Integer.parseInt(num[0]);//教练
        int no = Integer.parseInt(num[1]);//选手
        if (ma>10 || ma<3 || no>100 || no<3){
            System.out.println(-1);
            return;
        }
        List<String[]> list = new ArrayList<>();
        for (int i = 0; i < ma; i++) {
            list.add(sc.nextLine().split(","));
        }
        //收集选手信息
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < no; i++) {//第i个选手
            int total = 0;
            List<Integer> listScore = new ArrayList<>();
            for (int j = 0; j < ma; j++) {//第j个裁判
                String[] strings = list.get(j);
                int score = Integer.parseInt(strings[i]);
                if (score<0 || score>10){
                    System.out.println(-1);
                    return;
                }
                listScore.add(score);
                total+= score;
            }
            players.add(new Player(i,total,listScore));
        }
        //比较选手分数
        Collections.sort(players);
        for (int i = 0; i < 3; i++) {
            if (i == 2){
                System.out.println(players.get(i).idx+1);
            }else {
                System.out.print(players.get(i).idx+1 + ",");
            }
        }

    }
    static class Player implements Comparable<Player>{
        private final int idx;
        private final int total;
        private final List<Integer> scores;

        public Player(int idx,int total, List<Integer> scores) {
            this.idx = idx;
            this.total = total;
            this.scores = scores;
        }

        private int checkCount(List<Integer> list,int count){
            int cou = 0;
            for (Integer integer : list) {
                if (integer == count) {
                    cou++;
                }
            }
            return cou;
        }

        @Override
        public int compareTo(Player ply) {
            //先比较总分
            if (ply.total < this.total){
                return -1;
            }else if (ply.total > this.total){
                return 1;
            }else {
                //后比较最高分的数量
                List<Integer> scPly = ply.scores;
                List<Integer> scores = this.scores;
                for (int i = 10; i > 0; i--) {
                    int ipl = checkCount(scPly, i);
                    int ith = checkCount(scores, i);
                    if (ipl < ith){
                        return -1;
                    }
                }
            }
            return 0;
        }
    }
}
