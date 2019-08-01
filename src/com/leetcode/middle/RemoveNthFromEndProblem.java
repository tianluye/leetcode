package com.leetcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目描述：
 *      给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 *      给定一个链表: 1->2->3->4->5, 和 n = 2.
 *      当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *      给定的 n 保证是有效的。
 * 进阶：
 *      你能尝试使用一趟扫描实现吗？
 */
public class RemoveNthFromEndProblem {

    public static void main(String[] args) {
        ListNode node = genListNode(Arrays.asList(1,2,3,4,5));
//        ListNode result = removeNthFromEndOne(node, 1);
        ListNode result = removeNthFromEndTwo(node, 2);
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
     * resolve method two
     *
     * 移除倒数第 n个节点，返回移除后的链表信息
     * 思路：
     *      定义 2个指针，指向链表的头部;
     *      先移动指针 first n + 1次，再同时移动 first last指针，直到末尾;
     *      那这个时候 last指向的节点就是倒数第 n个节点的前一个节点;
     *      最后让 last.next = last.next.next.
     *
     * @param head 链表
     * @param n    n
     * @return 链表
     */
    private static ListNode removeNthFromEndTwo(ListNode head, int n) {
        if (null == head) return null;
        // 兼容要移除的节点是第一个节点，所以构造一个虚拟节点作为头节点
        ListNode dummyNode = new ListNode(1);
        dummyNode.next = head;
        // 定义 2个指针
        ListNode first = dummyNode, last = dummyNode;
        // 让 last指针与 first指针之间的距离为 n个节点
        for (int i = 1; i <= n + 1; i++) {
            if (null == first) return head;
            first = first.next;
        }
        // 直到遍历链表结束，此时 last指向的节点就是要删除节点的前一个节点
        while (first != null) {
            first = first.next;
            last = last.next;
        }
        // 移除操作
        last.next = last.next.next;
        // 去除虚拟节点
        return dummyNode.next;
    }

    /**
     * resolve method one
     *
     * 移除倒数第 n个节点，返回移除后的链表信息
     * 思路：
     *      先全部遍历一遍链表，将每个节点的数据加入到 list中;
     *      找到要移除的元素 a, 那么它的前一个元素 b.next = a.next;
     *      最后返回 list.get(0).
     *
     * @param head 链表
     * @param n    n
     * @return 链表
     */
    private static ListNode removeNthFromEndOne(ListNode head, int n) {
        List<ListNode> nodeList = new ArrayList<>();
        if (null == head) return null;
        ListNode temp = head;
        // 遍历，并缓存每个节点的数据
        while (temp != null) {
            nodeList.add(temp);
            temp = temp.next;
        }
        int nodeSize = nodeList.size();
        if (n > nodeSize || n < 1) return nodeList.get(0);
        int loc = nodeSize - n;
        if (loc == 0) {
            // 如果要移除的元素是链表的第一个元素
            nodeList.remove(0);
        } else if (loc == nodeSize - 1) {
            // 如果要移除的元素是链表的最后一个元素
            nodeList.get(loc - 1).next = null;
        } else {
            // 如果要移除的元素是链表的中间元素
            nodeList.get(loc - 1).next = nodeList.get(loc + 1);
        }
        return nodeList.size() == 0 ? null : nodeList.get(0);
    }

    /**
     * 链表的数据结构
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

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