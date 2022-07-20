package code.ctci.linkedlist;

public class LinkedListKthLastElement {

    /**
     * Recursive solution
     *
     * This algorithm recurses through the linked list.
     * When it hits the end, it returns the counter as 0. Each parent call adds 1 to this counter.
     * When the counter equals k, we know we have reached the kth to last element of the linked list.
     *
     * Time complexity: O(n) time, where n is the number of nodes in the linked list
     * Space complexity: O(n) space - each call stack until node reaches end
     *
     */
    static int findKthToLastElement(LinkedListNode node, int k) {
        if (node == null) return 0;
        int index = findKthToLastElement(node.next, k) + 1;
        if (index == k) {
            System.out.println(k + "th to the last node is " + node.data);
        }
        return index;
    }

    /**
     * Iterative approach - more optimal solution
     *
     * Use two pointers approach
     * - move one pointer (p1) till k length, and iterate 2nd pointer (p2) till p1 hit end of the list
     *
     * Time complexity: O(n) time
     * Space complexity: O(1) space
     */
    static void findKthToLastElementOptimal(LinkedListNode head, int k) {
        LinkedListNode p1 = head;
        LinkedListNode p2 = head;
        int pos = k;
        while (pos-- > 0) {
            if (p1 == null) return; // Out of bounds
            p1 = p1.next;
        }
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        System.out.println(k + "th to the last node is " + p2.data);
    }

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(3);
        head.next = new LinkedListNode(5);
        head.next.next = new LinkedListNode(8);
        head.next.next.next = new LinkedListNode(5);
        head.next.next.next.next = new LinkedListNode(10); // k = 3
        head.next.next.next.next.next = new LinkedListNode(2);
        head.next.next.next.next.next.next = new LinkedListNode(1);
        //
        LinkedListNode.printData(head);
        findKthToLastElement(head, 3);
        System.out.println();
        //
        LinkedListNode test = new LinkedListNode(3);
        test.next = new LinkedListNode(1);
        test.next.next = new LinkedListNode(5); // k = 2
        test.next.next.next = new LinkedListNode(8);
        //
        LinkedListNode.printData(test);
        findKthToLastElementOptimal(test, 2);
    }

}
