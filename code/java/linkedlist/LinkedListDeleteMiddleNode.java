package code.java.linkedlist;

/**
 * https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 */
public class LinkedListDeleteMiddleNode {

    private static LinkedListNode deleteMiddle(LinkedListNode head) {
        if (head.next == null) return null;
        LinkedListNode slow = head;
        LinkedListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    public static void main(String[] args) {
        LinkedListNode head = LinkedListNode.createNodes(new int[] {1, 3, 4, 7, 1, 2, 6});
        LinkedListNode.printData(head);
        deleteMiddle(head);
        LinkedListNode.printData(head);
        System.out.println();
        //
        head = LinkedListNode.createNodes(new int[] {1, 2, 3, 4});
        LinkedListNode.printData(head);
        deleteMiddle(head);
        LinkedListNode.printData(head);
        System.out.println();
        //
        head = LinkedListNode.createNodes(new int[] {2, 1});
        LinkedListNode.printData(head);
        deleteMiddle(head);
        LinkedListNode.printData(head);
        System.out.println();
        //
        head = LinkedListNode.createNodes(new int[] {1});
        LinkedListNode.printData(head);
        deleteMiddle(head);
        LinkedListNode.printData(head);
        System.out.println();
    }

}
