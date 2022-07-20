package code.ctci.linkedlist;

public class LinkedListPartition {

    /**
     * Leetcode solution
     *
     * Time complexity: O(N), where N is the number of nodes in the linked list
     * Space complexity: O(1), we have not utilized any extra space
     */
    static void partitionWithFourPointers(LinkedListNode node, int x) {
        LinkedListNode before_head = new LinkedListNode(-1);
        LinkedListNode before = before_head;
        LinkedListNode after_head = new LinkedListNode(-1);
        LinkedListNode after = after_head;
        //
        while (node != null) {
            if (node.data < x) {
                before.next = node;
                before = before.next;
            } else {
                after.next = node;
                after = after.next;
            }
            node = node.next;
        }
        after.next = null;
        // Once all the nodes are correctly assigned to the two lists,
        // combine them to form a single list which would be returned.
        before.next = after_head.next;
        //
        LinkedListNode.printData(before_head.next);
    }

    /**
     * Time complexity: O(n) time
     * Space complexity: O(1) space
     */
    static void partitionWithTwoPointers(LinkedListNode node, int x) {
        LinkedListNode head = node;
        LinkedListNode tail = node;
        while (node != null) {
            LinkedListNode next = node.next;
            if (node.data < x) {
                // Insert node at head
                node.next = head;
                head = node;
            } else {
                // Insert node at tail
                tail.next = node;
                tail = node;
            }
            node = next;
        }
        tail.next = null;
        // Print
        LinkedListNode.printData(head);
    }

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(3);
        head.next = new LinkedListNode(5);
        head.next.next = new LinkedListNode(8);
        head.next.next.next = new LinkedListNode(5);
        head.next.next.next.next = new LinkedListNode(10);
        head.next.next.next.next.next = new LinkedListNode(2);
        head.next.next.next.next.next.next = new LinkedListNode(1);
        //
        LinkedListNode test = new LinkedListNode(3);
        test.next = new LinkedListNode(1);
        test.next.next = new LinkedListNode(2);
        test.next.next.next = new LinkedListNode(8);
        // Print
        LinkedListNode.printData(test);
        // Partition
        partitionWithFourPointers(test, 2);
    }

}
