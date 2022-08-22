package code.java.linkedlist;

import java.util.Objects;

public class ZipperLists {

    /**
     * Time complexity: O(min(m, n)), where m is the length of head1, and n is the head2 length
     * Space complexity: O(1)
     */
    private static LinkedListNode mergeLists(LinkedListNode head1, LinkedListNode head2) {
        int count = 0;
        LinkedListNode tail = head1;
        LinkedListNode current1 = head1.next;
        LinkedListNode current2 = head2;
        //
        while (Objects.nonNull(current1) && Objects.nonNull(current2)) {
            if ((count & 1) == 0) {
                tail.next = current2;
                current2 = current2.next;
            } else {
                tail.next = current1;
                current1 = current1.next;
            }
            tail = tail.next;
            count += 1;
        }
        if (Objects.nonNull(current1)) {
            tail.next = current1;
        }
        if (Objects.nonNull(current2)) {
            tail.next = current2;
        }
        return head1;
    }

    /**
     * Time complexity: O(min(m, n)), where m is the length of head1, and n is the head2 length
     * Space complexity: O(min(m, n))
     */
    private static LinkedListNode mergeListsRecursive(LinkedListNode head1, LinkedListNode head2) {
        if (Objects.isNull(head1) && Objects.isNull(head2)) {
            return null;
        }
        if (Objects.isNull(head1)) return head2;
        if (Objects.isNull(head2)) return head1;
        //
        LinkedListNode next1 = head1.next;
        LinkedListNode next2 = head2.next;
        // Attach pointers
        head1.next = head2;
        head2.next = mergeListsRecursive(next1, next2);
        return head1;
    }

    public static void main(String[] args) {
        LinkedListNode node1 = new LinkedListNode(1);
        LinkedListNode node1_1 = new LinkedListNode(3);
        LinkedListNode node1_2 = new LinkedListNode(5);
        node1.next = node1_1;
        node1_1.next = node1_2;
        //
        LinkedListNode node2 = new LinkedListNode(2);
        LinkedListNode node2_1 = new LinkedListNode(4);
        LinkedListNode node2_2 = new LinkedListNode(6);
        node2.next = node2_1;
        node2_1.next = node2_2;
        // Print both
        LinkedListNode.printData(node1);
        LinkedListNode.printData(node2);
        //
        // LinkedListNode result = mergeLists(node1, node2);
        LinkedListNode result = mergeListsRecursive(node1, node2);
        LinkedListNode.printData(result);
    }

}
