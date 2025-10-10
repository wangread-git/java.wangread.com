package com.read.test.algorithm.link;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeSortedLinkedList {


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode p = dummy;

        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        return dummy.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        int capacity = Math.max(lists.length, 1);
        PriorityQueue<ListNode> queue = new PriorityQueue<>(
                capacity, Comparator.comparingInt(a -> a.val));
        ListNode p = dummy;

        for (ListNode header : lists) {
            if (header != null) {
                queue.add(header);
            }
        }

        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            p.next = node;
            if (node.next != null) {
                queue.add(node.next);
            }
            p = p.next;
        }
        return dummy.next;
    }
}
