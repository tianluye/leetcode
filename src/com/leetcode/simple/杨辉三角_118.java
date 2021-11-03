package com.leetcode.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 10005655
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 *
 * numRows = 5
 * [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 */
public class 杨辉三角_118 {

    public static void main(String[] args) {
        杨辉三角_118 var = new 杨辉三角_118();
        List<List<Integer>> generate = var.generate(5);
        System.out.println(generate);
    }

    public List<List<Integer>> generate(int numRows) {
        if (numRows < 0) {
            return null;
        }
        if (numRows == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            // 循环的是每一行的数据
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (i == 0 || j == 0 || i == j) {
                    tmp.add(1);
                } else {
                    int next = result.get(i - 1).get(j - 1) + result.get(i - 1).get(j);
                    tmp.add(next);
                }
            }
            result.add(tmp);
        }
        return result;
    }

}
