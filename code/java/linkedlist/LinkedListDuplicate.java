package code.java.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkedListDuplicate {

    /**
     * Time complexity: O(n) time, where n is the number of nodes in the list
     * Space complexity: O(n) space (HashSet)
     */
    static void deleteDuplicatesWithBuffer(LinkedListNode node) {
        Set<Integer> dict = new HashSet<>();
        LinkedListNode previous = null;
        while (node != null) {
            if (dict.add(node.data)) {
                previous = node;
            } else {
                previous.next = node.next;
            }
            node = node.next;
        }
    }

    /**
     * This code runs in 2 pointer approach to reduce space complexity (without any buffer)
     *
     * Time complexity: O(n^2) time
     * Space complexity: O(1) space
     */
    static void deleteDuplicatesWithoutBuffer(LinkedListNode node) {
        LinkedListNode current = node;
        while (current.next != null) {
            LinkedListNode runner = current.next;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    public static void main(String[] args) {
        // Input
        LinkedListNode head = new LinkedListNode(3);
        head.next = new LinkedListNode(7);
        head.next.next = new LinkedListNode(2);
        head.next.next.next = new LinkedListNode(7);
        head.next.next.next.next = new LinkedListNode(1);
        //
        deleteDuplicatesWithBuffer(head);
        LinkedListNode.printData(head);
        //
        LinkedListNode node = new LinkedListNode(3);
        node.next = new LinkedListNode(7);
        node.next.next = new LinkedListNode(2);
        node.next.next.next = new LinkedListNode(7);
        node.next.next.next.next = new LinkedListNode(1);
        //
        deleteDuplicatesWithoutBuffer(node);
        LinkedListNode.printData(node);
    }

}
