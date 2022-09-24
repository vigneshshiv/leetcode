package code.java.linkedlist;

import code.java.utils.MethodsUtility;

/**
 * https://leetcode.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {

    private static LinkedListNode addTwoNumbers(LinkedListNode l1, LinkedListNode l2) {
        int carry = 0;
        LinkedListNode result = new LinkedListNode();
        LinkedListNode current = result;
        while (l1 != null && l2 != null) {
            int sum = carry + l1.data + l2.data;
            carry = sum / 10;
            current.next = new LinkedListNode(sum % 10);
            l1 = l1.next; l2 = l2.next; current = current.next;
        }
        // One is non null and Carry is available
        while (l1 != null) {
            int sum = carry + l1.data;
            carry = sum / 10;
            current.next = new LinkedListNode(sum % 10);
            l1 = l1.next; current = current.next;
        }
        while (l2 != null) {
            int sum = carry + l2.data;
            carry = sum / 10;
            current.next = new LinkedListNode(sum % 10);
            l2 = l2.next; current = current.next;
        }
        if (carry > 0) {
            current.next = new LinkedListNode(carry);
        }
        return result.next;
    }

    private static LinkedListNode addTwoNumbersOptimal(LinkedListNode l1, LinkedListNode l2) {
        int sum = 0;
        LinkedListNode head = new LinkedListNode();
        LinkedListNode current = head;
        while (l1 != null || l2 != null) {
            sum /= 10;
            if (l1 != null) {
                sum += l1.data;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.data;
                l2 = l2.next;
            }
            current.next = new LinkedListNode(sum % 10);
            current = current.next;
        }
        if (sum > 9) current.next = new LinkedListNode(1);
        return head.next;
    }


    public static void main(String[] args) {
        LinkedListNode root1 = LinkedListNode.createNodes(new int[] {2, 4, 3});
        LinkedListNode root2 = LinkedListNode.createNodes(new int[] {5, 6, 4});
        LinkedListNode result = addTwoNumbers(root1, root2);
        LinkedListNode.printData(result);
        result = addTwoNumbersOptimal(root1, root2);
        LinkedListNode.printData(result);
        //
        root1 = LinkedListNode.createNodes(new int[] {0});
        root2 = LinkedListNode.createNodes(new int[] {0});
        result = addTwoNumbers(root1, root2);
        LinkedListNode.printData(result);
        result = addTwoNumbersOptimal(root1, root2);
        LinkedListNode.printData(result);
        //
        root1 = LinkedListNode.createNodes(new int[] {9, 9, 9, 9, 9, 9, 9});
        root2 = LinkedListNode.createNodes(new int[] {9, 9, 9, 9});
        result = addTwoNumbers(root1, root2);
        LinkedListNode.printData(result);
        result = addTwoNumbersOptimal(root1, root2);
        LinkedListNode.printData(result);
        //
        root1 = LinkedListNode.createNodes(new int[] {9, 9, 1});
        root2 = LinkedListNode.createNodes(new int[] {1});
        result = addTwoNumbers(root1, root2);
        LinkedListNode.printData(result);
        result = addTwoNumbersOptimal(root1, root2);
        LinkedListNode.printData(result);
    }

}
