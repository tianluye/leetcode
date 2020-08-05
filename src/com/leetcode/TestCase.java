package com.leetcode;

import java.util.HashSet;
import java.util.Set;

public class TestCase {

    public static void main(String[] args) {
        TestCase testCase = new TestCase();

        boolean result = testCase.isMatch("abbcb", "a.*");
        System.out.println(result);
    }

    public boolean isMatch(String s, String p) {
        // S串当前下标位置，刚开始为0
        int index = 0;
        // 下一个状态集合
        // 这里状态用p串的下标表示，代表下一次可以从p的这些下标开始匹配
        Set<Integer> nextState = new HashSet<>();
        // 由于是刚开始匹配，这时候p下一个下标是0
        nextMatch(p, 0, nextState);
        // nextState不为空时，表示还有合法的下一个状态，匹配继续
        while (!nextState.isEmpty()){
            // 当前状态就是上一次的nextState
            Set<Integer> nowState = nextState;
            // 创建新的nextState
            nextState = new HashSet<>();
            // 测试s[index]和集合里的状态是否有匹配
            for (int state : nowState){
                // 如果同时到达s和p串末尾，匹配成功
                if (state >= p.length() && index >= s.length()){
                    return true;
                }
                // 仅仅p到达末尾还不行
                else if (state >= p.length()){
                    continue;
                }
                // s和p都未到达末尾
                else if (index < s.length()){
                    // 这里是匹配上的情况
                    if (p.charAt(state) == '.' || s.charAt(index) == p.charAt(state)){
                        // 如果p串的下一个字符是'*'，当前状态可以匹配任意多次，所以下一个状态还是当前
                        if (state+1 < p.length() && p.charAt(state+1) == '*'){
                            nextMatch(p, state, nextState);
                        }
                        // 否则，下一个状态就是state+1
                        else {
                            nextMatch(p, state+1, nextState);
                        }
                    }
                }
            }
            index++;
        }
        // 此时，nextState为空，代表没有合法的下一个状态了，匹配失败
        return false;
    }

    // p:正则表达式
    // state:下一个状态
    // nextState:下一个状态集合，无重复
    private void nextMatch(String p, int state, Set<Integer> nextState){
        // 首先加上下一个状态到状态集中
        nextState.add(state);
        // 这里是判断下一个字符是'*'的情况，由于此时匹配次数可以是0，所以state+2也是合法的下一个状态
        if (state+1 < p.length() && p.charAt(state+1) == '*'){
            nextMatch(p, state+2, nextState);
        }
    }

}
