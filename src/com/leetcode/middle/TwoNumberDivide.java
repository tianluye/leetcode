package com.leetcode.middle;

/**
 * 题目描述：
 *      给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *      返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 示例：
 *      输入: dividend = 10, divisor = 3
 *      输出: 3
 *
 * 说明：
 *      1、被除数和除数均为 32 位有符号整数。
 *      2、除数不为 0。
 *      3、假设我们的环境只能存储 32位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 */
public class TwoNumberDivide {

    public static void main(String[] args) {
        TwoNumberDivide object = new TwoNumberDivide();
        System.out.println(object.divide(-2147483648, -2147483647));
    }

    private int divide(int dividend, int divisor) {
        if (0 == divisor) return Integer.MAX_VALUE;
        if (dividend == 0) return 0;
        if (divisor == Integer.MIN_VALUE && dividend == divisor) return 1;
        if (divisor == Integer.MIN_VALUE) return 0;
        if (Math.abs(dividend) < Math.abs(divisor) && dividend != Integer.MIN_VALUE) return 0;

        // 判断最终的结果的正负
        int plusFlag = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? 1 : -1;
        // 因为后面想要将负数转为正数处理，需要处理下 -2147483648 转为正数的情况
        // -2147483648 = -2147483648 + Math.abs(divisor)
        dividend = dividend - (dividend > 0 ? Math.abs(divisor) : -Math.abs(divisor));
        int result = doDivide(dividend, divisor, plusFlag);
        // 根据结果的正负，判断是加 1还是减 1
        return result == Integer.MAX_VALUE ? result : result + plusFlag;
    }

    private int doDivide(int dividend, int divisor, int plusFlag) {
        // 转为正数
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        // 存放累加值
        int[] tap = new int[32];
        // 存放 2^pow的值
        int[] dp = new int[32];
        int tmpDivisor = divisor, mark = 0;
        // tmpDivisor > 0防止数值过大变为负数了
        while (dividend >= tmpDivisor && tmpDivisor > 0) {
            tap[mark] = tmpDivisor;
            // 不断乘 2
            tmpDivisor += tmpDivisor;
            dp[mark] = (int) Math.pow(2, mark);
            mark++;
        }
        int result = 0;
        while (dividend >= divisor && mark > 0) {
            mark -= 1;
            // 逐步尝试减去 tap的最大值
            if (dividend - tap[mark] >= 0) {
                result += dp[mark];
                dividend -= tap[mark];
            }
        }
        return plusFlag > 0 ? result : -result;
    }

}
