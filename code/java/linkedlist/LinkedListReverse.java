package code.java.linkedlist;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 *
 * https://leetcode.com/problems/reverse-linked-list-ii/
 */
public class LinkedListReverse {

    /**
     * n - # of nodes in the linked list
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static LinkedListNode reverseByIteration(LinkedListNode head) {
        if (head == null) return null;
        LinkedListNode prev = null;
        LinkedListNode current = head;
        //
        while (current != null) {
            LinkedListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    private static LinkedListNode reverseByRecursion(LinkedListNode head) {
        if (head == null) return null;
        return reverseList(head, null);
    }

    /**
     * n - # of nodes in the linked list
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    private static LinkedListNode reverseList(LinkedListNode head, LinkedListNode prev) {
        if (head == null) return prev;
        LinkedListNode next = head.next;
        head.next = prev;
        return reverseList(next, head);
    }

    private static LinkedListNode reverseBetweenNodes(LinkedListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        LinkedListNode prev = null;
        LinkedListNode current = head;
        for (int i = 0; i < left - 1 && current != null; i++) {
            prev = current;
            current = current.next;
        }
        LinkedListNode start = prev; // Before first, needs for link with new first
        LinkedListNode last = current; // Current first becomes new last (end node)
        // Reverse between left and right
        int idx = 0;
        int range = right - left + 1;
        while (current != null && idx < range) {
            LinkedListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            idx += 1;
        }
        // Link with prev (which is the last node in the traversal)
        if (start != null) {
            start.next = prev; // initial first becomes last now
        } else {
            head = prev;
        }
        // Change last pointer to current (traversed ahead of 1 node element)
        last.next = current;
        return head;
    }

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(5);
        head.next = new LinkedListNode(4);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(2);
        head.next.next.next.next = new LinkedListNode(1);
        //
        LinkedListNode.printData(head);
        LinkedListNode result = reverseByIteration(head);
        System.out.println("Iterative reverse list version ");
        LinkedListNode.printData(result);
        System.out.println();
        //
        head = new LinkedListNode(5);
        head.next = new LinkedListNode(4);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(2);
        head.next.next.next.next = new LinkedListNode(1);
        //
        LinkedListNode.printData(head);
        System.out.println("Recursive reverse list version ");
        result = reverseByRecursion(head);
        LinkedListNode.printData(result);
        System.out.println();
        //
        head = new LinkedListNode(5);
        head.next = new LinkedListNode(4);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(2);
        head.next.next.next.next = new LinkedListNode(1);
        //
        LinkedListNode.printData(head);
        System.out.println("Recursive in between reverse from 2 to 4 nodes ");
        result = reverseBetweenNodes(head, 2, 4);
        LinkedListNode.printData(result);
    }

}
