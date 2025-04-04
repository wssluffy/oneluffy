package com.oneluffy.user.zt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
012 【单词接龙】
单词接龙的规则是：可用于接龙的单词首字母必须要前一个单词的尾字母相同；当存在多个首字母相同的单词时，取长度最长的单词，如果长度也相等，则取字典序最小的单词；已经参与接龙的单词不能重复使用。
现给定一组全部由小写字母组成单词数组，并指定其中的一个单词作为起始单词，进行单词接龙，请输出最长的单词串，单词串是单词拼接而成，中间没有空格。
输入描述:
输入的第一行为一个非负整数，表示起始单词在数组中的索引K，0 <= K < N ；
输入的第二行为一个非负整数，表示单词的个数N；
接下来的N行，分别表示单词数组中的单词。
输出描述:
输出一个字符串，表示最终拼接的单词串。
示例1：
输入
0
6
word
dd
da
dc
dword
d
输出
worddwordda
说明
先确定起始单词word，再接以d开头的且长度最长的单词dword，剩余以d开头且长度最长的有dd、da、dc，则取字典序最小的da，所以最后输出worddwordda。
示例2：
输入
4
6
word
dd
da
dc
dword
d
输出
dwordda
说明
先确定起始单词dword，剩余以d开头且长度最长的有dd、da、dc，则取字典序最小的da，所以最后输出dwordda。
备注:
单词个数N的取值范围为[1, 20]；
单个单词的长度的取值范围为[1, 30]；

*/


/*这个问题可以通过使用深度优先搜索（DFS）来解决。以下是解决这个问题的步骤：

构建图：首先，我们需要构建一个图，其中每个节点代表一个单词，边表示可以从一个单词接龙到另一个单词。
深度优先搜索：从起始单词开始，使用深度优先搜索来找到最长的单词接龙。
记录路径：在搜索过程中，我们需要记录当前路径上的单词，以避免重复使用。
比较和选择：在搜索过程中，如果有多个单词可以接龙，选择长度最长的单词；如果长度相同，则选择字典序最小的单词。*/

public class WordChain {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int startIndex = scanner.nextInt();
    int n = scanner.nextInt();
    List<String> words = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      words.add(scanner.next());
    }
    scanner.close();

    String result = findLongestWordChain(words, startIndex);
    System.out.println(result);
  }

  private static String findLongestWordChain(List<String> words, int startIndex) {
    String startWord = words.get(startIndex);
    List<String> usedWords = new ArrayList<>();
    usedWords.add(startWord);
    return startWord + dfs(words, usedWords, startWord.charAt(startWord.length() - 1));
  }

  private static String dfs(List<String> words, List<String> usedWords, char lastChar) {
    List<String> candidates = new ArrayList<>();
    for (String word : words) {
      if (!usedWords.contains(word) && word.charAt(0) == lastChar) {
        candidates.add(word);
      }
    }
    candidates.sort((a, b) -> {
      if (a.length() != b.length()) {
        return b.length() - a.length();
      } else {
        return a.compareTo(b);
      }
    });

    if (candidates.isEmpty()) {
      return "";
    }

    StringBuilder longestChain = new StringBuilder();
    for (String candidate : candidates) {
      usedWords.add(candidate);
      String chain = candidate + dfs(words, usedWords, candidate.charAt(candidate.length() - 1));
      if (chain.length() > longestChain.length()) {
        longestChain.setLength(0);
        longestChain.append(chain);
      }
      usedWords.remove(candidate);
    }
    return longestChain.toString();
  }
}
