package code.java.linkedlist;

public class LinkedListNode {

    public LinkedListNode next, prev, last;
    public int data;

    public LinkedListNode() {
    }

    public LinkedListNode(int data) {
        this.data = data;
    }

    public LinkedListNode(int data, LinkedListNode nextNode, LinkedListNode prevNode) {
        this(data);
        this.setNext(nextNode);
        this.setPrevious(prevNode);
    }

    /**
     * Set Next node
     * - If this node is last node then change the lastNode pointer to nextNode
     * - nextNode prev is not pointing to current node (this) then change the nextNode prev pointer to current (this) node
     */
    public void setNext(LinkedListNode nextNode) {
        this.next = nextNode;
        if (last == this) {
            last = nextNode;
        }
        if (nextNode != null && nextNode.prev != this) {
            nextNode.setPrevious(this);
        }
    }

    /**
     * Set Previous node
     * - prevNode next is not pointing to current node (this) then change the prevNode next pointer to current (this) node
     */
    public void setPrevious(LinkedListNode prevNode) {
        this.prev = prevNode;
        if (prevNode != null && prevNode.next != this) {
            prevNode.setNext(this);
        }
    }

    /**
     * Clone
     */
    public LinkedListNode clone() {
        LinkedListNode copy = null;
        if (next != null) {
            copy = next.clone();
        }
        LinkedListNode head = new LinkedListNode(data, copy, null);
        return head;
    }

    /**
     * Get linked list length / size
     */
    public static int length(LinkedListNode head) {
        int count = 0;
        while (head != null) {
            count += 1;
            head = head.next;
        }
        return count;
    }

    /**
     * Is Two lists are Equal?
     */
    public static boolean isEqual(LinkedListNode node1, LinkedListNode node2) {
        LinkedListNode head1 = node1;
        LinkedListNode head2 = node2;
        while (head1 != null && head2 != null) {
            if (head1.data != head2.data) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1 == null && head2 == null;
    }

    /**
     * Print data
     */
    public static void printData(LinkedListNode head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.print("null");
        System.out.println();
    }

    /**
     * Print Current, Prev & Next pointer data
     */
    public static void printDeepData(LinkedListNode head) {
        while (head != null) {
            int prevData = head.prev != null ? head.prev.data : -1;
            int nextData = head.next != null ? head.next.data : -1;
            System.out.println(head.data + ", prev - " + prevData + ", next - " + nextData);
            head = head.next;
        }
        System.out.println();
    }

    /**
     * Create Nodes from array of elements
     */
    public static LinkedListNode createNodes(int[] nums) {
        LinkedListNode head = new LinkedListNode();
        LinkedListNode current = head;
        for (int num : nums) {
            current.next = new LinkedListNode(num);
            current = current.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        // Doubly Linked List -- 3 -> 7 -> 4
        LinkedListNode head = new LinkedListNode(3);
        LinkedListNode middle = new LinkedListNode(7);
        LinkedListNode last = new LinkedListNode(4);
        //
        head.next = middle;
        //
        middle.prev = head;
        middle.next = last;
        middle.last = last;
        //
        last.prev = middle;
        last.next = null;
        //
        printData(head);
        printDeepData(head);
    }

}
