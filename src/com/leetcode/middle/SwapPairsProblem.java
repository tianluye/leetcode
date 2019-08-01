package com.leetcode.middle;

import java.util.Arrays;
import java.util.List;

/**
 * 题目描述：
 *      1、给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *      2、你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例:
 *      给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class SwapPairsProblem {

    public static void main(String[] args) {
        ListNode node = genListNode(Arrays.asList(1,2,3,4,5));
//        ListNode result = swapPairsOne(node);
        ListNode result = swapPairsTwo(node);
        System.out.println(result);
    }

    /**
     * 根据输入的 list组装链表
     *
     * @param valList 链表值集合
     * @return 链表
     */
    private static ListNode genListNode(List<Integer> valList) {
        ListNode temp = new ListNode(-1);
        ListNode listNode = temp;
        for (Integer val : valList) {
            temp.next = new ListNode(val);
            temp = temp.next;
        }
        return listNode.next;
    }

    /**
     * 转换链表
     *
     * @param head 链表
     * @return 转换后的链表
     */
    private static ListNode swapPairsOne(ListNode head) {
        // 保证链表有数据
        if (null == head) return null;
        // 新开辟一个链表 node，并创建一个临时节点指向 node
        ListNode nodeTemp = new ListNode(-1);
        ListNode node = nodeTemp;
        int time = 0;
        int a = 0, b;
        while (null != head) {
            // 每 2个节点进行一次交换
            time++;
            if (1 == time) {
                a = head.val;
            } else {
                b = head.val;
                // 设置节点
                nodeTemp.next = new ListNode(b);
                nodeTemp.next.next = new ListNode(a);
                // 改变 nodeTemp变量的指向为子节点的子节点
                nodeTemp = nodeTemp.next.next;
                // 清空 time
                time = 0;
            }
            head = head.next;
        }
        // 处理链表长度为奇数的情况
        if (time == 1) {
            nodeTemp.next = new ListNode(a);
        }
        // 返回转换后的链表
        return node.next;
    }

    /**
     * 递归求解
     * [1,2,3,4,5] == [1,2] -> [3,4,5] == [2,1] -> [3,4] -> [5]
     *
     * @param head 头指针
     * @return 结果
     */
    private static ListNode swapPairsTwo(ListNode head) {
        // 若当前 head节点为空或者其子节点为空，则不需要进行 swap
        if (null == head || null == head.next) return head;
        ListNode next = head.next;
        // 头指针指向下次递归的结果
        head.next = swapPairsTwo(next.next);
        // 头指针指向其子节点的 next
        next.next = head;
        return next;
    }

    /**
     * 链表的数据结构
     */
    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int x) { val = x; }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("ListNode: [");
            ListNode node = this;
            while (null != node) {
                builder.append(node.val).append("->");
                node = node.next;
            }
            String result = builder.toString();
            if (result.lastIndexOf(">") == result.length() - 1) {
                result = result.substring(0, result.length() - 2);
            }
            return result + "]";
        }
    }

}
