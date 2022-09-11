package code.java.linkedlist;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNodeFromEndOfList {

    private static LinkedListNode removeNthFromEnd(LinkedListNode head, int n) {
        LinkedListNode prev = null;
        LinkedListNode current = head;
        int count = 0;
        while (current != null) {
            count += 1;
            current = current.next;
        }
        if (count == n) return head.next;
        current = head;
        while (--count > n - 1) {
            prev = current;
            current = current.next;
        }
        prev.next = current.next;
        return head;
    }

    /**
     * Follow up: Could you do this in one pass?
     * Using Two Pointer we can solve this.
     *
     */
    private static LinkedListNode removeNthFromEndOnePass(LinkedListNode head, int n) {
        LinkedListNode current = new LinkedListNode();
        current.next = head;
        LinkedListNode slow = current, fast = current;
        while (fast.next != null) {
            fast = fast.next;
            if (n <= 0) slow = slow.next;
            n -= 1;
        }
        slow.next = slow.next.next;
        return current.next;
    }


    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(4);
        head.next.next.next.next = new LinkedListNode(5);
        int n = 2;
        LinkedListNode.printData(head);
        head = removeNthFromEndOnePass(head, n);
        LinkedListNode.printData(head);
        //
        n = 1;
        head = new LinkedListNode(1);
        LinkedListNode.printData(head);
        head = removeNthFromEndOnePass(head, n);
        LinkedListNode.printData(head);
        //
        n = 2;
        head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        LinkedListNode.printData(head);
        head = removeNthFromEndOnePass(head, n);
        LinkedListNode.printData(head);
    }

}
