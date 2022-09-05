package code.java.operators;

import code.java.linkedlist.LinkedListNode;

/**
 * https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
 */
public class ConvertToBinaryNumber {

    private static int getDecimalValue(LinkedListNode head) {
        int num = 0;
        LinkedListNode current = head;
        while (current != null) {
            num <<= 1;
            num |= current.data;
            current = current.next;
        }
        return num;
    }

    private static int getDecimalValueByBase2Conversion(LinkedListNode head) {
        int num = 0;
        LinkedListNode current = head;
        while (current != null) {
            num = 2 * num + current.data;
            current = current.next;
        }
        return num;
    }

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        head.next = new LinkedListNode(0);
        head.next.next = new LinkedListNode(1);
        System.out.println("Decimal value of 101 - " + getDecimalValueByBase2Conversion(head));
        System.out.println("Decimal value of 0 - " + getDecimalValue(new LinkedListNode(0)));
    }

}
