package com.leetcode.middle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 题目描述：给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *      [
 *          "((()))",
 *          "(()())",
 *          "(())()",
 *          "()(())",
 *          "()()()"
 *      ]
 */
public class GenerateParenthesisProblem {

    public static void main(String[] args) {
        // call for method one
        // System.out.println(generateParenthesis(3));

        // call for method two
        System.out.println(generateMethodTwo(4));
    }

    private static List<String> generateParenthesis(int n) {
        List<String> resultList = new ArrayList<>();
        if (n < 1) return resultList;
        generate(0, 0, new StringBuilder(""), n, resultList);
        return resultList;
    }

    /**
     * 递归求解
     *
     * @param left    左括号 ( 的个数
     * @param right   右括号 ) 的个数
     * @param builder 当前递归的结果
     * @param n       n对括号
     * @param result  返回值集合
     */
    private static void generate(int left, int right, StringBuilder builder, int n, List<String> result) {
        // 设置临时变量，不用进行回退
        StringBuilder tempSb = new StringBuilder(builder);
        // 如果左括号的个数小于 n，那么可以尝试加入左括号
        if (left < n) {
            tempSb.append("(");
            // 加入后，left与 builder的值发生变化，进行下一次的递归
            generate(left + 1, right, tempSb, n, result);
        }
        // 若右括号的个数小于左括号的个数，可以尝试加入右括号
        if (right < left) {
            builder.append(")");
            // 加入后，right与 builder的值发生变化，进行下一次的递归
            generate(left, right + 1, builder, n, result);
        }
        // 若左右括号的个数相等，且等于 n，则说明匹配成功
        if (left == right && left == n) {
            result.add(builder.toString());
        }
    }

    //****** Method: 2 ******/
    private static List<String> generateMethodTwo(int n) {
        // 利用 dp动态规划的思想求出 dp[i]，而 dp[i + 1]依赖于前者
        List<List<String>> dpResultList = new ArrayList<>();
        // n == 1的时候有且只有一个解 [()]
        dpResultList.add(Collections.singletonList("()"));
        // 2 - n的情况
        for (int i = 1; i < n; i++) {
            // 获取前一组的解
            List<String> beforeDpList = dpResultList.get(i - 1);
            List<String> nowDpList = new ArrayList<>();
            // nowDpList = ['()' + beforeDpList] + [beforeDpList + '()'] + ['(' + beforeDpList + ')']
            // 其中需要过滤掉相同的场景，比如 '()' + '()()', '()()' + '()'
            for (int j = 0, len = beforeDpList.size(); j < len; j++) {
                // 故意将重复的场景放在列表的第一位，便于区分
                nowDpList.add("()" + beforeDpList.get(j));
                if (j != 0) {
                    nowDpList.add(beforeDpList.get(j) + "()");
                }
                nowDpList.add("(" + beforeDpList.get(j) + ")");
            }
            dpResultList.add(nowDpList);
        }
        // 返回 n对括号对应的结果
        return dpResultList.get(n - 1);
    }

}
