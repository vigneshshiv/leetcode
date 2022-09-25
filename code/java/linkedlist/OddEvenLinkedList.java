package code.java.linkedlist;

/**
 * https://leetcode.com/problems/odd-even-linked-list/
 */
public class OddEvenLinkedList {

    private static LinkedListNode oddEvenList(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListNode odd = head, even = head.next;
        LinkedListNode evenHead = even;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public static void main(String[] args) {
        LinkedListNode head = LinkedListNode.createNodes(new int[] {1, 2, 3, 4, 5});
        LinkedListNode result = oddEvenList(head);
        LinkedListNode.printData(result);
        //
        head = LinkedListNode.createNodes(new int[] {1, 2, 3, 4});
        result = oddEvenList(head);
        LinkedListNode.printData(result);
        //
        head = LinkedListNode.createNodes(new int[] {2, 1, 3, 5, 6, 4, 7});
        result = oddEvenList(head);
        LinkedListNode.printData(result);
    }

}
