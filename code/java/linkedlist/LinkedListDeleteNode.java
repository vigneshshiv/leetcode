package code.java.linkedlist;

public class LinkedListDeleteNode {

    /**
     * Linked list delete node
     *
     * Given a node to delete
     * - simple solution to copy the data from the next node over to the current node, and then delete the next node
     *
     * 3 -> (5) -> 7 -> 8
     * 3 -> 7 -> [7] -> 8, [] position ignored after copied to current node
     *
     */
    static LinkedListNode deleteNode(LinkedListNode node) {
        if (node == null || node.next == null) {
            System.out.println("Can't delete since node or node next element is empty");
            return null;
        }
        int data = node.data;
        LinkedListNode next = node.next;
        node.data = next.data;
        node.next = next.next;
        System.out.println("Node " + data + " successfully deleted");
        return node;
    }

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(3);
        LinkedListNode deleteNode = new LinkedListNode(5);
        LinkedListNode middle = new LinkedListNode(7);
        LinkedListNode last = new LinkedListNode(8);
        //
        head.next = deleteNode;
        deleteNode.next = middle;
        middle.next = last;
        //
        LinkedListNode.printData(head);
        deleteNode(deleteNode);
        LinkedListNode.printData(head);
    }

}
