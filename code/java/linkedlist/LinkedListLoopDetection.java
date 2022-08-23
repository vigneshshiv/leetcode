package code.java.linkedlist;

/**
 * https://leetcode.com/problems/linked-list-cycle/
 *
 * https://leetcode.com/problems/linked-list-cycle-ii/
 *
 */
public class LinkedListLoopDetection {

    /**
     * Find loop beginning
     * - Create two pointers, "FastPointer" & "SlowPointer"
     * - Move "FastPointer" at a rate of 2 steps and "SlowPointer" at a rate of 1 step
     * - When they collide, move "SlowPointer" to linked list HEAD, Keep "FastPointer" where it is.
     *  -- "FastRunner" is LOOP_SIZE - K steps behind the LOOP start.
     * - Move "SlowPointer" and "FastPointer" at a rate of one step, Return the collision spot.
     *
     * Eg: LinkedList size - 11, (4th node starting) - If the LOOP Size is 8, they met the "Collision Spot" at 5.
     *  -- So they both (LOOP_SIZE - K) => (8 - 5) => 3 steps behind starting of the loop.
     *  -- Both "Collision Spot" and "Linked list HEAD" are k nodes from the start of the loop
     *
     * Time complexity: O(n) time
     * Space complexity: O(1) space
     *
     */
    static void findLoopEntryNode(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;
        // Find meeting point, This will be LOOP_SIZE - k steps into the linked list
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { // Collision
                break;
            }
        }
        // Check if no meeting point, therefore, no loop
        if (fast == null || fast.next == null) {
            System.out.println("Linked list has No LOOP detection ");
            return;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        System.out.println("Linked List LOOP detected, LOOP Starting node - " + slow.data);
    }

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        // Loop start node
        LinkedListNode loopStartNode = new LinkedListNode(3); // Loop Start node
        loopStartNode.next = new LinkedListNode(4);
        loopStartNode.next.next = new LinkedListNode(5);
        // Loop end node
        LinkedListNode loopEndNode = loopStartNode;
        // Reference it to the cycle
        loopStartNode.next.next.next = loopEndNode;
        head.next.next = loopStartNode;
        //
        findLoopEntryNode(head);
        // 1 - 2 -> (1)
        head = new LinkedListNode(1);
        LinkedListNode tail = new LinkedListNode(2);
        head.next = tail;
        tail.next = head;
        findLoopEntryNode(head);
        //
        head = new LinkedListNode(1);
        findLoopEntryNode(head);
        //
        head = new LinkedListNode(3);
        LinkedListNode loopNode = new LinkedListNode(2);
        head.next = loopNode;
        loopNode.next = new LinkedListNode(0);
        loopNode.next.next = new LinkedListNode(-4);
        loopNode.next.next.next = loopNode;
        findLoopEntryNode(head);
    }

}
