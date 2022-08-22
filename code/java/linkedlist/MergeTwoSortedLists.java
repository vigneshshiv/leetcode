package code.java.linkedlist;

import java.util.Objects;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoSortedLists {

    /**
     * Time complexity: O(min(m, n))
     * Space complexity: O(1)
     */
    private static LinkedListNode mergeTwoLists(LinkedListNode list1, LinkedListNode list2) {
        // Base case
        if (Objects.isNull(list1) && Objects.isNull(list2)) {
            return list1;
        }
        if (Objects.isNull(list1)) {
            return list2;
        }
        if (Objects.isNull(list2)) {
            return list1;
        }
        LinkedListNode head = null;
        if (list1.data <= list2.data) {
            head = list1;
            list1 = list1.next;
        } else {
            head = list2;
            list2 = list2.next;
        }
        LinkedListNode node = head;
        while (Objects.nonNull(list1) && Objects.nonNull(list2)) {
            if (list1.data <= list2.data) {
                node.next = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }
        if (Objects.nonNull(list1)) {
            node.next = list1;
        }
        if (Objects.nonNull(list2)) {
            node.next = list2;
        }
        return head;
    }

    /**
     * Time complexity: O(min(m, n))
     * Space complexity: O(min(m, n))
     */
    private static LinkedListNode mergeTwoListsRecursive(LinkedListNode list1, LinkedListNode list2) {
        // Base case
        if (Objects.isNull(list1) && Objects.isNull(list2)) {
            return list1;
        }
        if (Objects.isNull(list1)) {
            return list2;
        }
        if (Objects.isNull(list2)) {
            return list1;
        }
        LinkedListNode head = null;
        if (list1.data <= list2.data) {
            head = list1;
            list1 = list1.next;
        } else {
            head = list2;
            list2 = list2.next;
        }
        LinkedListNode node = head;
        node.next = mergeRecursive(list1, list2, node.next);
        return head;
    }

    private static LinkedListNode mergeRecursive(LinkedListNode list1, LinkedListNode list2, LinkedListNode head) {
        // Base case
        if (Objects.isNull(list1) && Objects.isNull(list2)) {
            return list1;
        }
        if (Objects.isNull(list1)) {
            return list2;
        }
        if (Objects.isNull(list2)) {
            return list1;
        }
        if (list1.data <= list2.data) {
            head = list1;
            list1 = list1.next;
        } else {
            head = list2;
            list2 = list2.next;
        }
        head.next = mergeRecursive(list1, list2, head.next);
        return head;
    }

    public static void main(String[] args) {
        LinkedListNode node1 = new LinkedListNode(1);
        LinkedListNode node1_1 = new LinkedListNode(2);
        LinkedListNode node1_2 = new LinkedListNode(4);
        node1.next = node1_1;
        node1_1.next = node1_2;
        //
        LinkedListNode node2 = new LinkedListNode(1);
        LinkedListNode node2_1 = new LinkedListNode(3);
        LinkedListNode node2_2 = new LinkedListNode(4);
        node2.next = node2_1;
        node2_1.next = node2_2;
        // Print both
        LinkedListNode.printData(node1);
        LinkedListNode.printData(node2);
        //
        // LinkedListNode result = mergeTwoLists(node1, node2);
        LinkedListNode result = mergeTwoListsRecursive(node1, node2);
        LinkedListNode.printData(result);
    }

}
