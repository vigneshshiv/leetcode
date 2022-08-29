package code.java.linkedlist;

import java.util.Objects;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 */
public class RemoveDuplicates {

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static LinkedListNode removeDuplicates(LinkedListNode head) {
        if (Objects.isNull(head)) {
            return head;
        }
        LinkedListNode current = head;
        while (current != null) {
            if (Objects.nonNull(current.next) && current.data == current.next.data) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    private static LinkedListNode removeDuplicatesRecursive(LinkedListNode head) {
        if (head == null) {
            return null;
        }
        if (Objects.nonNull(head.next) && head.data == head.next.data) {
            head.next = head.next.next;
            return removeDuplicatesRecursive(head);
        }
        head.next = removeDuplicatesRecursive(head.next);
        return head;
    }

    public static void main(String[] args) {
        LinkedListNode listNode = new LinkedListNode(1);;
        listNode.next = new LinkedListNode(1);
        listNode.next.next = new LinkedListNode(2);
        //
        LinkedListNode.printData(listNode);
        // LinkedListNode head = removeDuplicatesRecursive(listNode);
        LinkedListNode head = removeDuplicates(listNode);
        LinkedListNode.printData(head);
        System.out.println();
        //
        listNode = new LinkedListNode(1);;
        listNode.next = new LinkedListNode(1);
        listNode.next.next = new LinkedListNode(2);
        listNode.next.next.next = new LinkedListNode(3);
        listNode.next.next.next.next = new LinkedListNode(3);
        LinkedListNode.printData(listNode);
        // head = removeDuplicatesRecursive(listNode);
        head = removeDuplicates(listNode);
        LinkedListNode.printData(head);
        System.out.println();
        //
        listNode = new LinkedListNode(1);;
        listNode.next = new LinkedListNode(1);
        listNode.next.next = new LinkedListNode(1);
        listNode.next.next.next = new LinkedListNode(2);
        listNode.next.next.next.next = new LinkedListNode(4);
        listNode.next.next.next.next.next = new LinkedListNode(4);
        LinkedListNode.printData(listNode);
        // head = removeDuplicatesRecursive(listNode);
        head = removeDuplicates(listNode);
        LinkedListNode.printData(head);
    }

}
