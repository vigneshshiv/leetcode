package code.java.stacks;

/**
 * https://leetcode.com/problems/min-stack/
 */
public class MinStackNode {
    record Node(int val, int min, Node next) {};
    // Head
    private Node head;

    public void push(int val) {
        if (head == null) {
            head = new Node(val, val, null);
        } else {
            head = new Node(val, Math.min(val, head.min), head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    public static void main(String[] args) {
        MinStackNode obj = new MinStackNode();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        System.out.println("Get Min - " + obj.getMin());
        obj.pop();
        System.out.println("Top - " + obj.top());
        System.out.println("Get Min - " + obj.getMin());
        //
        obj = new MinStackNode();
        obj.push(0);
        obj.push(1);
        obj.push(0);
        System.out.println("Get Min - " + obj.getMin());
        obj.pop();
        System.out.println("Get Min - " + obj.getMin());
    }

}
