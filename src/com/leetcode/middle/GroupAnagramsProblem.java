package com.leetcode.middle;

import java.util.ArrayList;
import java.util.List;

public class GroupAnagramsProblem {

    public static void main(String[] args) {
        String[] params = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagramsOne(params));
    }

    public static List<List<String>> groupAnagramsTwo(String[] strs) {

        return null;
    }

    /**
     * 方法 1：两层 for循环进行比较，外层真正执行 for循环的次数，就是结果的 size
     *
     * @param strs <br>
     * @return <br>
     */
    public static List<List<String>> groupAnagramsOne(String[] strs) {
        List<List<String>> resultList = new ArrayList<>();
        List<String> result;
        // 存放是否已经加入到结果中
        boolean[] isUsed = new boolean[strs.length];
        for (int i = 0, len = strs.length; i < len; i++) {
            if (isUsed[i]) continue;
            isUsed[i] = true;
            result = new ArrayList<>();
            result.add(strs[i]);
            // 进行一一比较
            for (int j = i + 1; j < len; j++) {
                if (isUsed[j]) continue;
                if (compareStr(strs[i], strs[j])) {
                    result.add(strs[j]);
                    isUsed[j] = true;
                }
            }
            resultList.add(result);
        }
        return resultList;
    }

    /**
     * 比较 2个字符串是否是字母异位词
     *
     * @param one <br>
     * @param two <br>
     * @return true or false
     */
    private static boolean compareStr(String one, String two) {
        // 若比较的两个参数长度不一致，那么肯定不是【字母异位词】
        if (one.length() != two.length()) return false;
        // 存放 26个字母出现的次数
        int[] times = new int[26];
        char[] ones = one.toCharArray();
        char[] twos = two.toCharArray();
        for (int i = 0, len = ones.length; i < len; i++) {
            // 前者加 1，后者减 1
            times[ones[i] - 'a'] += 1;
            times[twos[i] - 'a'] -= 1;
        }
        for (int j = 0; j < 26; j++) {
            if (times[j] != 0) return false;
        }
        return true;
    }

}
