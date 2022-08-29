package code.java.linkedlist;

/**
 * https://leetcode.com/problems/middle-of-the-linked-list/
 */
public class MiddleOfTheLinkedList {

    private static LinkedListNode middleNode(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;
        //
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        LinkedListNode listNode = new LinkedListNode(1);;
        listNode.next = new LinkedListNode(2);
        listNode.next.next = new LinkedListNode(3);
        listNode.next.next.next = new LinkedListNode(4);
        listNode.next.next.next.next = new LinkedListNode(5);
        LinkedListNode.printData(listNode);
        LinkedListNode.printData(middleNode(listNode));
        //
        listNode.next.next.next.next.next = new LinkedListNode(6);
        LinkedListNode.printData(listNode);
        LinkedListNode.printData(middleNode(listNode));
    }

}
