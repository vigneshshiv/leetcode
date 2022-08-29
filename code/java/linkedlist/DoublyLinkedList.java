package code.java.linkedlist;

public class DoublyLinkedList {

    private Node head;

    private int size;

    public  DoublyLinkedList() {
        this.size = 0;
    }

    public void insertFirst(int data) {
        Node node = new Node(data);
        node.next = head;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        //
        size += 1;
    }

    public void insertLast(int data) {
        Node node = new Node(data);
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = node;
        node.prev = current;
        //
        size += 1;
    }

    public void print() {
        if (head == null) return;
        Node node = head;
        Node last = null;
        while (node != null) {
            System.out.print(node.val + " -> ");
            last = node;
            node = node.next;
        }
        System.out.println("END");
        // Reverse
        while (last != null) {
            System.out.print(last.val + " <- ");
            last = last.prev;
        }
        System.out.println("START");
    }

    private class Node {
        private int val;
        private Node next;
        private Node prev;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.insertFirst(2);
        list.insertFirst(4);
        list.insertFirst(1);
        // Display
        list.print();
        //
        list.insertLast(5);
        list.insertLast(7);
        list.print();
    }

}
