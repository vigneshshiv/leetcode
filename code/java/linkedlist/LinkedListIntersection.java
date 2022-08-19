package code.java.linkedlist;

public class LinkedListIntersection {

    /**
     * Find an intersecting node
     * - Run through each linked list to get the lengths and the tails
     * - Compare the tails, if they are different (by reference, not by value) return immediately. There's no intersection
     * - Set two pointers to the start of each linked list
     * - On the longer linked list, advance the pointer by difference in lengths
     * - Now, traverse on each linked list until the pointers are same
     *
     * Time complexity: O(n + m) time, where n and m are the length of the linked lists.
     * Space complexity: O(1) space
     */
    static void findIntersection(LinkedListNode n1, LinkedListNode n2) {
        if (n1 == null || n2 == null) {
            System.out.println("One of the Linked list is empty so no intersecting point");
            return;
        }
        NodeResult nodeResult1 = getTailAndSize(n1);
        NodeResult nodeResult2 = getTailAndSize(n2);
        // If different tail nodes, then there's no intersection
        if (nodeResult1.node != nodeResult2.node) {
            System.out.println("Linked list has different tail nodes so no intersecting point");
            return;
        }
        // Set pointers to start of the linked list
        LinkedListNode shorter = nodeResult1.size < nodeResult2.size ? n1 : n2;
        LinkedListNode longer = nodeResult1.size < nodeResult2.size ? n2 : n1;
        // Advance the pointer for the longer linked list by difference in lengths
        longer = getKthNode(longer, Math.abs(nodeResult1.size - nodeResult2.size));
        // Move the pointers until you have a collision
        while (shorter != longer) {
            shorter = shorter.next;
            longer = longer.next;
        }
        System.out.println("Intersecting Collision Spot - " + longer.data);
    }

    /**
     * Get last node and linked list length
     */
    static NodeResult getTailAndSize(LinkedListNode node) {
        if (node == null) {
            return null;
        }
        int count = 1;
        LinkedListNode current = node;
        while (current.next != null) {
            count++;
            current = current.next;
        }
        return new NodeResult(current, count);
    }

    /**
     * Fast-forward to the node pointer to kth element
     */
    static LinkedListNode getKthNode(LinkedListNode head, int k) {
        LinkedListNode current = head;
        while (k-- > 0 && current != null) {
            current = current.next;
        }
        return current;
    }

    public static void main(String[] args) {
        LinkedListNode intersectingNode = new LinkedListNode(7);
        intersectingNode.next = new LinkedListNode(2);
        intersectingNode.next.next = new LinkedListNode(1);
        // N1
        LinkedListNode n1 = new LinkedListNode(3);
        n1.next = new LinkedListNode(1);
        n1.next.next = new LinkedListNode(5);
        n1.next.next.next = new LinkedListNode(9);
        n1.next.next.next.next = intersectingNode;
        // N2
        LinkedListNode n2 = new LinkedListNode(4);
        n2.next = new LinkedListNode(6);
        n2.next.next = intersectingNode;
        //
        findIntersection(n1, n2);
    }

}
