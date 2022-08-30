package code.java.linkedlist;

import java.util.Stack;

/**
 * https://leetcode.com/problems/palindrome-linked-list/
 */
public class LinkedListPalindrome {

    /**
     * Simplest approach
     *  - Find the middle node
     *  - Reverse a list from middle
     *  - Compare both halves
     *  - Re-reverse the list and return true if either of halves are empty
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     */
    private static boolean checkPalindrome(LinkedListNode head) {
        LinkedListNode mid = middleNode(head);
        LinkedListNode secondHead = reverseList(mid);
        LinkedListNode reverseHead = secondHead;
        // Compare head with middle head
        while (head != null && secondHead != null) {
            if (head.data != secondHead.data) {
                break;
            }
            head = head.next;
            secondHead = secondHead.next;
        }
        reverseList(reverseHead);
        return head == null || secondHead == null;
    }

    /**
     * n - # of nodes in the linked list
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static LinkedListNode reverseList(LinkedListNode head) {
        if (head == null) return null;
        LinkedListNode prev = null;
        LinkedListNode current = head;
        //
        while (current != null) {
            LinkedListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    private static LinkedListNode middleNode(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * Reverse and Compare approach
     *
     * - While comparing the linked list with reversed list, we only need to compare the first half of the list
     * - If the first half of the normal list matches the first half of the reversed list
     *      then the second half must & should match (because it's reversed)
     *
     * Time complexity: O(n) time
     * Space complexity: O(n) space
     */
    static void palindrome(LinkedListNode head) {
        HeadAndTail reversed = reverse(head);
        boolean isEqual = LinkedListNode.isEqual(head, reversed.head);
        LinkedListNode.printData(head);
        System.out.print("The above is palindrome? - " + isEqual);
    }

    /**
     * Recursive approach
     */
    static HeadAndTail reverse(LinkedListNode head) {
        if (head == null) return null;
        HeadAndTail result = reverse(head.next);
        LinkedListNode clonedHead = head.clone();
        clonedHead.next = null;
        if (result == null) {
            return new HeadAndTail(clonedHead, clonedHead);
        }
        result.tail.next = clonedHead;
        return new HeadAndTail(result.head, clonedHead);
    }

    /**
     * Iterative approach using Stack
     * - Push first half of the elements onto a stack
     *
     * - Iterate through the linked list, using fast runner / slow runner technique
     * - At each step, push the data from the slow runner onto a stack
     * - When the fast runner hits the end of the list, the slow runner will have reached the middle of the list.
     *
     * - By this point, the stack will have all the elements from the front of the linked list, but in reverse order
     * - Last, iterate through the rest of the slow runner node, and compare the node to the top of the stack.
     */
    static void isPalindromeByStack(LinkedListNode head) {
        LinkedListNode fast = head;
        LinkedListNode slow = head;
        // Stack
        Stack<Integer> stack = new Stack<>();
        // Fast & Slow runner iteration
        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            fast = fast.next.next;
            slow = slow.next;
        }
        // If list has odd number of elements, Skip the middle element
        if (fast != null) {
            slow = slow.next;
        }
        boolean isPalindrome = true;
        while (slow != null) {
            int value = stack.pop().intValue();
            if (slow.data != value) {
                isPalindrome = false;
                break;
            }
            slow = slow.next;
        }
        System.out.println("Stack approach - Is palindrome? - " + isPalindrome);
    }

    /**
     * Recursive Solution
     *
     * Idea - compare 0 and n-1 && compare 1 and n-2 elements and so on...
     *
     * Pass list length in the recursive by (length - 2), when the length equals 0 or 1 we are at the middle element.
     *
     * if it's 0, return the node & compare the node in the call stack
     * if it's 1, return next node & compare the node in the call stack
     */
    static void isPalindromeByRecursion(LinkedListNode head) {
        int length = LinkedListNode.length(head);
        NodeResult result = recurse(head, length);
        System.out.println("Recursive approach - Is palindrome? - " + result.result);
    }

    /**
     * Recursive
     */
    static NodeResult recurse(LinkedListNode node, int length) {
        if (node == null || length == 0) { // Even number of nodes
            return new NodeResult(node, true);
        } else if (length == 1) { // Odd number of nodes
            return new NodeResult(node.next, true);
        }
        NodeResult nodeResult = recurse(node.next, length - 2);
        // If a child calls are not palindrome, pass back up a failure
        if (!nodeResult.result || nodeResult.node == null) {
            return nodeResult;
        }
        // Check the node with other side node
        nodeResult.result = node.data == nodeResult.node.data;
        nodeResult.node = nodeResult.node.next;
        return nodeResult;
    }

    public static void main(String[] args) {
        // Reverse and Compare
        LinkedListNode head = new LinkedListNode(0);
        head.next = new LinkedListNode(1);
        head.next.next = new LinkedListNode(2);
        head.next.next.next = new LinkedListNode(1);
        head.next.next.next.next = new LinkedListNode(0);
        // Not palindrome testing
        // head.next.next.next.next.next = new LinkedListNode(3);
        palindrome(head);
        System.out.println();
        // Stack
        System.out.println("2 5 3 5 2");
        LinkedListNode node = new LinkedListNode(2);
        head.next = new LinkedListNode(5);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(5);
        head.next.next.next.next = new LinkedListNode(2);
        isPalindromeByStack(node);
        // Recursive
        System.out.println("5 1 4 1 5");
        LinkedListNode listNode = new LinkedListNode(5);
        listNode.next = new LinkedListNode(1);
        listNode.next.next = new LinkedListNode(4);
        listNode.next.next.next = new LinkedListNode(1);
        listNode.next.next.next.next = new LinkedListNode(5);
        isPalindromeByRecursion(listNode);
    }

}

class HeadAndTail {
    public LinkedListNode head;
    public LinkedListNode tail;

    public HeadAndTail(LinkedListNode head, LinkedListNode tail) {
        this.head = head;
        this.tail = tail;
    }
}

class NodeResult {
    public LinkedListNode node;
    public boolean result;
    public int size;

    public NodeResult(LinkedListNode node, boolean result) {
        this.node = node;
        this.result = result;
    }

    public NodeResult(LinkedListNode node, int size) {
        this.node = node;
        this.size = size;
    }
}