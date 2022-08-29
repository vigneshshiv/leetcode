package code.java.linkedlist;

public class SinglyLinkedList {

    private Node head;
    private Node tail;

    private int size;

    public SinglyLinkedList() {
        this.size = 0;
    }

    public void insertFirst(int data) {
        Node node = new Node(data);
        node.next = head;
        head = node;
        //
        if (tail == null) {
            tail = head;
        }
        //
        size += 1;
    }

    public void insertLast(int data) {
        if (tail == null) {
            insertFirst(data);
            return;
        }
        Node node = new Node(data);
        // Consider tail not provided and move the pointer to last and add it & Change the tail pointer
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = node;
        // Tail
        tail = node;
        //
        size += 1;
    }

    public void insertAfterByRecursion(int data, int index) {
        // Check Index
        if (index < 0 || index > size) {
            throw new UnsupportedOperationException("Index out of bound operation");
        }
        head = insertAfterByRecursion(head, data, index);
    }

    private Node insertAfterByRecursion(Node current, int data, int index) {
        if (index == 0) {
            Node node = new Node(data);
            node.next = current;
            return node;
        }
        current.next = insertAfterByRecursion(current.next, data, index - 1);
        return current;
    }

    public void insertAfter(int data, int index) {
        if (index == 0) {
            insertFirst(data);
            return;
        }
        if (index == size) {
            insertLast(data);
            return;
        }
        // Check Index
        if (index < 0 || index > size) {
            throw new UnsupportedOperationException("Index out of bound operation");
        }
        Node current = head;
        while (index >= 0) {
            current = current.next;
            index -= 1;
        }
        Node node = new Node(data, current.next);
        current.next = node;
        //
        size += 1;
    }

    public void insertAt(int data, int index) {
        // Check Index
        if (size == 0 || index < 0 || index > size - 1) {
            throw new UnsupportedOperationException("Index out of bound operation");
        }
        Node node = get(index);
        // Change the data
        node.val = data;
    }

    public int deleteFirst() {
        // Nothing to delete
        if (head == null) return -1;
        //
        int data = head.val;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size -= 1;
        return data;
    }

    public int deleteLast() {
        // Nothing to delete
        if (tail == null) return -1;
        // Both points to same node
        if (head == tail) {
            return deleteFirst();
        }
        // Move the pointer to last before and delete
        Node node = get(size - 2);
        int data = tail.val;
        node.next = null;
        tail = node;
        //
        size -= 1;
        return data;
    }

    public int delete(int index) {
        if (index == 0) {
            return deleteFirst();
        }
        if (index == size - 1) {
            return deleteLast();
        }
        Node prev = get(index - 1);
        int data = prev.next.val;
        prev.next = prev.next.next;
        //
        size -= 1;
        return data;
    }

    public Node find(int data) {
        if (head == null) {
            throw new UnsupportedOperationException("Data is empty");
        }
        Node current = head;
        while (current != null && current.val != data) {
            current = current.next;
        }
        return current;
    }

    private Node get(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public void print() {
        if (head == null) return;
        Node node = head;
        while (node != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println("END");
    }

    private class Node {
        private int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insertFirst(2);
        list.insertFirst(4);
        list.insertFirst(1);
        // Display
        list.print();
        list.insertLast(5);
        list.insertLast(7);
        list.print();
        list.insertAfter(11, 3);
        list.print();
        list.insertAt(12, 0);
        list.print();
        list.insertAt(24, 5);
        list.print();
        System.out.println(list.deleteFirst());
        list.print();
        System.out.println(list.deleteLast());
        list.print();
        System.out.println(list.delete(2));
        list.print();
        Node node = list.find(7);
        System.out.println("Node value - " + node != null ? node.val : "No data");
        list.insertAfterByRecursion(15, 2);
        list.print();
    }

}
