package code.java.linkedlist;

/**
 * https://leetcode.com/problems/rotate-list/
 */
public class LinkedListRotation {

    /**
     * Right rotation, similar to array rotation approach with two pointer
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static LinkedListNode rotateRight(LinkedListNode head, int k) {
        if (k <= 0 || head == null || head.next == null) {
            return head;
        }
        int length = 1;
        LinkedListNode current = head;
        while (current.next != null) {
            current = current.next;
            length += 1;
        }
        int rotation = k % length;
        if (rotation == 0) return head;
        //
        current.next = head;
        LinkedListNode last = head;
        for (int i = 0; i < length - rotation - 1; i++) {
            last = last.next;
        }
        head = last.next;
        last.next = null;
        //
        return head;
    }

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(4);
        head.next.next.next.next = new LinkedListNode(5);
        //
        LinkedListNode.printData(head);
        LinkedListNode result = rotateRight(head, 10);
        LinkedListNode.printData(result);
        System.out.println();
    }

}
