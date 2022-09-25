package code.java.linkedlist;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 */
public class ReverseKNodesInGroup {

    private static LinkedListNode reverseKGroup(LinkedListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        LinkedListNode result = new LinkedListNode(-1);
        result.next = head;
        LinkedListNode last = result;
        while (last != null) {
            LinkedListNode current = last;
            // Check whether k nodes are there to reverse
            for (int i = 0; i < k && current != null; i++) {
                current = current.next;
            }
            if (current == null) {
                break;
            }
            last = reverse(last, k);
        }
        return result.next;
    }

    private static LinkedListNode reverse(LinkedListNode last, int k) {
        LinkedListNode prev = null;
        LinkedListNode current = last.next;
        for (int i = 0; i < k; i++) {
            LinkedListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        LinkedListNode tail = last.next; // Consider last (just reference) as first, because first node becomes last
        tail.next = current; // join last node to next starting node
        last.next = prev;
        return tail;
    }

    /**
     * Recursive approach
     */
    private static LinkedListNode reverseKGroupRecursive(LinkedListNode head, int k) {
        LinkedListNode current = head;
        int idx = 0;
        while (current != null && idx != k) {
            current = current.next;
            idx++;
        }
        if (idx == k) {
            current = reverseKGroupRecursive(current, k);
            while (idx-- > 0) {
                LinkedListNode next = head.next;
                head.next = current;
                current = head;
                head = next;
            }
            head = current;
        }
        return head;
    }

    public static void main(String[] args) {
        int k = 2;
        LinkedListNode head = LinkedListNode.createNodes(new int[] {1, 2, 3, 4, 5});
        LinkedListNode result = reverseKGroup(head, k);
        LinkedListNode.printData(result);
        //
        head = LinkedListNode.createNodes(new int[] {1, 2, 3, 4, 5});
        result = reverseKGroupRecursive(head, k);
        LinkedListNode.printData(result);
        //
        k = 3;
        head = LinkedListNode.createNodes(new int[] {1, 2, 3, 4, 5});
        result = reverseKGroup(head, k);
        LinkedListNode.printData(result);
        //
        head = LinkedListNode.createNodes(new int[] {1, 2, 3, 4, 5});
        result = reverseKGroupRecursive(head, k);
        LinkedListNode.printData(result);
    }

}
