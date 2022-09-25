package code.java.linkedlist;

import java.util.Objects;

/**
 * https://leetcode.com/problems/sort-list/
 */
public class SortList {

    /**
     * Merge sort linked list
     */
    private static LinkedListNode sortList(LinkedListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return head;
        }
        LinkedListNode mid = middleNode(head);
        LinkedListNode left = sortList(head);
        LinkedListNode right = sortList(mid);
        return MergeTwoSortedLists.mergeTwoLists(left, right);
    }

    private static LinkedListNode middleNode(LinkedListNode head) {
        LinkedListNode midPrev = null;
        //
        while (head != null && head.next != null) {
            midPrev = (midPrev == null) ? head : midPrev.next;
            head = head.next.next;
        }
        LinkedListNode mid = midPrev.next;
        // Cut the reference to the next pointer (mid), so that head remains from start to mid.
        midPrev.next = null;
        return mid;
    }

    public static void main(String[] args) {
        LinkedListNode listNode = new LinkedListNode(4);;
        listNode.next = new LinkedListNode(2);
        listNode.next.next = new LinkedListNode(1);
        listNode.next.next.next = new LinkedListNode(5);
        listNode.next.next.next.next = new LinkedListNode(3);
        LinkedListNode.printData(listNode);
        LinkedListNode.printData(sortList(listNode));
    }

}
