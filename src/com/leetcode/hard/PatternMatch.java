package com.leetcode.hard;

/**
 * @author 10005655 tianluye
 *
 * https://leetcode-cn.com/problems/regular-expression-matching/
 */
public class PatternMatch {

    public static void main(String[] args) {
        PatternMatch patternMatch = new PatternMatch();
        // "aaa" "ab*a*c*a"
        boolean result = patternMatch.isMatch("aab", "c*a*b");
        System.out.println(result);
    }

    public boolean isMatch(String s, String p) {
        if (null == s || null == p) {
            return false;
        }
        if (p.startsWith("*")) {
            return isMatch(s, p.substring(1));
        }
        int m = s.length();
        int n = p.length();
        boolean dp[][] = new boolean[m + 1][n + 1];
        // 若 s和 p都是空串，则返回 true
        dp[0][0] = true;
        // i 从 0开始，是因为 "" 与 "a*" 是匹配的
        for (int i = 0; i <= m; ++i) {
            // 因为 dp的定义是位置，而不是字符串的下标，所以 j从 1开始
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    // * 前面的字符与源串位置 i的字符是否相等
                    if (matchChar(s, p, i, j - 1)) {
                        // 要么 * 参与匹配, 要么不参与匹配
                        // 参与匹配的情况下, dp[i][j] = dp[i - 1][j]
                        // 不参与匹配的情况下, dp[i][j] = dp[i][j - 2]
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    } else {
                        // i位置与 j位置前一个元素不匹配。
                        // s: abc, p: ad*, i: 1, j: 3. 因为 a != d, 所以 d*将不参与匹配, 则 dp[1][3] = dp[1][1];
                        dp[i][j] = dp[i][j - 2];
                    }
                } else {
                    // 若 i与 j的位置字符匹配, 则 dp[i][j] = dp[i - 1][j - 1]
                    dp[i][j] = matchChar(s, p, i, j) && dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    /**
     * 源字符串的第 i位置字符与匹配串的第 j的字符是否匹配
     *
     * @param s 源字符串
     * @param p 匹配串
     * @param i 位置
     * @param j 位置
     * @return bool 是否匹配
     */
    private boolean matchChar(String s, String p, int i, int j) {
        // 只要与源串的第 0个位置字符比较，都为 false
        if (i == 0) {
            return false;
        }
        char pJ = p.charAt(j - 1);
        // 若 p在 j的位置是 . 或者 s在 i的位置字符等于 p在 j位置的字符
        return '.' == pJ || s.charAt(i - 1) == pJ;
    }

}
