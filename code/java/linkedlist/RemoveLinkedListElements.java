package code.java.linkedlist;

/**
 * https://leetcode.com/problems/remove-linked-list-elements/
 */
public class RemoveLinkedListElements {

    private static LinkedListNode removeElements(LinkedListNode head, int data) {
        if (head == null) return null;
        LinkedListNode node = new LinkedListNode();
        LinkedListNode current = head, prev = node;
        while (current != null) {
            if (current.data != data) {
                prev.next = current;
                prev = prev.next;
            }
            current = current.next;
        }
        prev.next = current; // Last element to be removed
        return node.next;
    }

    private static LinkedListNode removeElementsRecursiveOptimal(LinkedListNode head, int data) {
        if (head == null) return null;
        head.next = removeElementsRecursiveOptimal(head.next, data);
        return head.data == data ? head.next : head;
    }

    private static LinkedListNode removeElementsRecursive(LinkedListNode head, int data) {
        if (head == null) return null;
        LinkedListNode node = new LinkedListNode();
        node = removeRecursive(head, node, data);
        return node.next;
    }

    private static LinkedListNode removeRecursive(LinkedListNode head, LinkedListNode prev, int data) {
        if (head == null) {
            prev.next = null;
            return prev;
        }
        if (head.data == data) {
            return removeRecursive(head.next, prev, data);
        }
        prev.next = removeRecursive(head.next, head, data);
        return prev;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        int dataToBeRemoved = 6;
        LinkedListNode head = LinkedListNode.createNodes(nums);
        LinkedListNode.printData(head);
        head = removeElementsRecursiveOptimal(head, dataToBeRemoved);
        LinkedListNode.printData(head);
        //
        nums = new int[] {7, 7, 7, 7}; dataToBeRemoved = 7;
        head = LinkedListNode.createNodes(nums);
        LinkedListNode.printData(head);
        head = removeElementsRecursive(head, dataToBeRemoved);
        LinkedListNode.printData(head);
        //
        nums = new int[] {7, 7, 7, 7, 6}; dataToBeRemoved = 7;
        head = LinkedListNode.createNodes(nums);
        LinkedListNode.printData(head);
        head = removeElementsRecursive(head, dataToBeRemoved);
        LinkedListNode.printData(head);
        //
        nums = new int[] {8, 7, 7, 7, 7, 6}; dataToBeRemoved = 8;
        head = LinkedListNode.createNodes(nums);
        LinkedListNode.printData(head);
        head = removeElementsRecursive(head, dataToBeRemoved);
        LinkedListNode.printData(head);
        //
        nums = new int[] {5}; dataToBeRemoved = 5;
        head = LinkedListNode.createNodes(nums);
        LinkedListNode.printData(head);
        head = removeElements(head, dataToBeRemoved);
        LinkedListNode.printData(head);
        //
        nums = new int[] {}; dataToBeRemoved = 5;
        head = LinkedListNode.createNodes(nums);
        LinkedListNode.printData(head);
        head = removeElements(head, dataToBeRemoved);
        LinkedListNode.printData(head);
        //
        nums = new int[] {8, 7, 7, 7, 7, 6, 8}; dataToBeRemoved = 8;
        head = LinkedListNode.createNodes(nums);
        LinkedListNode.printData(head);
        head = removeElements(head, dataToBeRemoved);
        LinkedListNode.printData(head);
    }

}
