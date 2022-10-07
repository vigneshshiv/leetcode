package code.java.linkedlist;

import java.util.Stack;

/**
 * https://leetcode.com/problems/add-two-numbers-ii/
 */
public class AddTwoNumbersII {

    private static LinkedListNode addTwoNumbers(LinkedListNode l1, LinkedListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                s1.push(l1.data);
                l1 = l1.next;
            }
            if (l2 != null) {
                s2.push(l2.data);
                l2 = l2.next;
            }
        }
        LinkedListNode head = new LinkedListNode();
        LinkedListNode current = null, prev = null;
        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int sum = carry;
            if (!s1.isEmpty()) sum += s1.pop().intValue();
            if (!s2.isEmpty()) sum += s2.pop().intValue();
            carry = sum / 10;
            current = new LinkedListNode(sum % 10);
            current.next = prev;
            head.next = current;
            prev = current;
        }
        if (carry == 1) {
            current = new LinkedListNode(carry);
            current.next = prev;
            head.next = current;
        }
        return head.next;
    }

    public static void main(String[] args) {
        LinkedListNode root1 = LinkedListNode.createNodes(new int[] {1, 2, 3});
        LinkedListNode root2 = LinkedListNode.createNodes(new int[] {2, 3, 4});
        LinkedListNode result = addTwoNumbers(root1, root2);
        LinkedListNode.printData(result);
        //
        root1 = LinkedListNode.createNodes(new int[] {2, 4, 3});
        root2 = LinkedListNode.createNodes(new int[] {5, 6, 4});
        result = addTwoNumbers(root1, root2);
        LinkedListNode.printData(result);
        //
        root1 = LinkedListNode.createNodes(new int[] {7, 2, 4, 3});
        root2 = LinkedListNode.createNodes(new int[] {5, 6, 4});
        result = addTwoNumbers(root1, root2);
        LinkedListNode.printData(result);
        //
        root1 = LinkedListNode.createNodes(new int[] {0});
        root2 = LinkedListNode.createNodes(new int[] {0});
        result = addTwoNumbers(root1, root2);
        LinkedListNode.printData(result);
        //
        root1 = LinkedListNode.createNodes(new int[] {5});
        root2 = LinkedListNode.createNodes(new int[] {5});
        result = addTwoNumbers(root1, root2);
        LinkedListNode.printData(result);
    }

}
