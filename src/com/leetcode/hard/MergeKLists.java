package com.leetcode.hard;

/**
 * @author 10005655 tianluye
 *
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
public class MergeKLists {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        MergeKLists main = new MergeKLists();
        ListNode[] lists = new ListNode[2];

        ListNode lN1 = new ListNode(1);
        ListNode lN2 = new ListNode(4);
        ListNode lN3 = new ListNode(5);
        lN2.next = lN3;
        lN1.next = lN2;
        lists[0] = lN1;

        ListNode lN4 = new ListNode(1);
        ListNode lN5 = new ListNode(3);
        ListNode lN6 = new ListNode(4);
        lN5.next = lN6;
        lN4.next = lN5;
        lists[1] = lN4;

        main.mergeKLists(lists);
    }

    public ListNode mergeKLists(ListNode[] lists) {
//        ListNode listNode = new ListNode(0);
//        mergeProcess(listNode, lists);
//        return listNode.next;
        if(lists.length == 0)
            return null;
        if(lists.length == 1)
            return lists[0];
        if(lists.length == 2){
            return mergeTwoLists(lists[0],lists[1]);
        }

        int mid = lists.length/2;
        ListNode[] l1 = new ListNode[mid];
        for(int i = 0; i < mid; i++){
            l1[i] = lists[i];
        }

        ListNode[] l2 = new ListNode[lists.length-mid];
        for(int i = mid,j=0; i < lists.length; i++,j++){
            l2[j] = lists[i];
        }

        return mergeTwoLists(mergeKLists(l1),mergeKLists(l2));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 存在为空的链表，则直接返回不为空的链表
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null;
        if (l1.val <= l2.val){
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }

    private void mergeProcess(ListNode listNode, ListNode[] lists) {
        int len = lists.length;
        int min = Integer.MAX_VALUE, index = 0;
        for (int i = 0; i < len; i++) {
            if (null != lists[i] && min > lists[i].val) {
                index = i;
                min = lists[i].val;
            }
        }
        if (min == Integer.MAX_VALUE) {
            return;
        }
        listNode.next = new ListNode(min);
        lists[index] = lists[index].next;
        mergeProcess(listNode.next, lists);
    }

}
