package code.java.stacks;

import java.util.Stack;

/**
 * https://leetcode.com/problems/min-stack/
 */
public class MinStack {

    private Stack<Integer> stack;
    private int min = Integer.MAX_VALUE;

    public MinStack() {
        this.stack = new Stack<>();
    }

    // -2, 0, -3
    // p(-2), min = -2, S -> INF, -2
    // p(0), min = -2, S -> INF, -2, 0
    // p(-3), min = -3, S -> INF, -2, 0, -2, -3
    public void push(int val) {
        if (val <= min) {
            stack.push(min);
            min = val;
        }
        stack.push(val);
    }

    public void pop() {
        int top = stack.pop();
        if (top == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        System.out.println("Get Min - " + obj.getMin());
        obj.pop();
        System.out.println("Top - " + obj.top());
        System.out.println("Get Min - " + obj.getMin());
        //
        obj = new MinStack();
        obj.push(0);
        obj.push(1);
        obj.push(0);
        System.out.println("Get Min - " + obj.getMin());
        obj.pop();
        System.out.println("Get Min - " + obj.getMin());
    }

}
