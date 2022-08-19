package code.java.linkedlist;

public class LinkedListSumList {

    /**
     * List in forward order, 1s place in end of list (tail)
     *
     * Input: (6 -> 1 -> 7) + (2 -> 9 -> 5) => 617 + 295
     * Output: 9 -> 1 -> 2 => 912
     *
     */
    static LinkedListNode addListsForwardOrder(LinkedListNode n1, LinkedListNode n2) {
        int l1 = LinkedListNode.length(n1);
        int l2 = LinkedListNode.length(n2);
        // Pad the shorter list with zeros
        if (l1 < l2) {
            n1 = padList(n1, l2 - l1);
        } else {
            n2 = padList(n2, l1 - l2);
        }
        // Add lists
        PartialSum partialSum = addListsHelper(n1, n2);
        // If there was a carry value left over, insert this at the front of the list. Otherwise, just return the linked list
        if (partialSum.carry == 0) {
            return partialSum.node;
        } else {
            LinkedListNode result = insertBefore(partialSum.node, partialSum.carry);
            return result;
        }
    }

    /**
     * Add List helper
     */
    static PartialSum addListsHelper(LinkedListNode n1, LinkedListNode n2) {
        if (n1 == null && n2 == null) {
            PartialSum partialSum = new PartialSum();
            return partialSum;
        }
        // Add smaller digits recursively
        PartialSum helper = addListsHelper(n1.next, n2.next);
        int value = helper.carry + n1.data + n2.data;
        //
        LinkedListNode result = insertBefore(helper.node, value % 10);
        //
        helper.node = result;
        helper.carry = value / 10;
        return helper;
    }

    /**
     * Padding node list at head position
     */
    static LinkedListNode padList(LinkedListNode node, int padding) {
        LinkedListNode head = node;
        for (int i = 0; i < padding; i++) {
            head = insertBefore(head, 0);
        }
        return head;
    }

    /**
     * Create a head node and add it to the head.next position
     */
    static LinkedListNode insertBefore(LinkedListNode node, int data) {
        LinkedListNode head = new LinkedListNode(data);
        if (node != null) {
            head.next = node;
        }
        return head;
    }

    /**
     * List in reverse order, 1s place starts from head
     *
     * Input: (7 -> 1 -> 6) + (5 -> 9 -> 2) => 617 + 295
     * Output: 2 -> 1 -> 9 => 912
     *
     * Time complexity: O(n) time
     * Space complexity: O(n) space
     */
    static LinkedListNode addLists(LinkedListNode n1, LinkedListNode n2, int carry) {
        if (n1 == null && n2 == null && carry == 0) {
            return null;
        }
        LinkedListNode result = new LinkedListNode();
        //
        int value = carry;
        if (n1 != null) value += n1.data;
        if (n2 != null) value += n2.data;
        //
        result.data = value % 10;
        // Recurse till end
        if (n1 != null || n2 != null) {
            LinkedListNode more = addLists(n1 != null ? n1.next : null, n2 != null ? n2.next : null, value >= 10 ? 1 : 0);
            result.setNext(more);
        }
        return result;
    }

    public static void main(String[] args) {
        // N1
        LinkedListNode n1 = new LinkedListNode(7);
        n1.next = new LinkedListNode(1);
        n1.next.next = new LinkedListNode(6);
        // N2
        LinkedListNode n2 = new LinkedListNode(5);
        n2.next = new LinkedListNode(9);
        n2.next.next = new LinkedListNode(2);
        //
        LinkedListNode result = addLists(n1, n2, 0);
        LinkedListNode.printData(result);
        // L1
        LinkedListNode l1 = new LinkedListNode(6);
        l1.next = new LinkedListNode(1);
        l1.next.next = new LinkedListNode(7);
        // L2
        LinkedListNode l2 = new LinkedListNode(2);
        l2.next = new LinkedListNode(9);
        l2.next.next = new LinkedListNode(5);
        //
        result = addListsForwardOrder(l1, l2);
        LinkedListNode.printData(result);
    }

}

class PartialSum {
    public LinkedListNode node = null;
    public int carry = 0;
}
