package code.java.linkedlist;

/**
 * https://leetcode.com/problems/reorder-list/
 */
public class ReorderList {

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static void reorderList(LinkedListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        LinkedListNode mid = middleNode(head);
        LinkedListNode headSecond = reverseList(mid);
        LinkedListNode headFirst = head;
        //
        while (headFirst != null && headSecond != null) {
            LinkedListNode temp = headFirst.next;
            headFirst.next = headSecond;
            headFirst = temp;
            //
            temp = headSecond.next;
            headSecond.next = headFirst;
            headSecond = temp;
        }
        // Next of tail to null
        if (headFirst != null) {
            System.out.println("Head data - " + headFirst.data);
            headFirst.next = null;
        }
    }

    private static LinkedListNode middleNode(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * n - # of nodes in the linked list
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static LinkedListNode reverseList(LinkedListNode head) {
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

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(4);
        head.next.next.next.next = new LinkedListNode(5);
        //
        LinkedListNode.printData(head);
        reorderList(head);
        LinkedListNode.printData(head);
    }

}
