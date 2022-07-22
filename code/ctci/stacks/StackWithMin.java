package code.ctci.stacks;

import java.util.Stack;

public class StackWithMin extends Stack<Integer> {

    private Stack<Integer> minStack;

    public StackWithMin() {
        minStack = new Stack<>();
    }

    /**
     * Stack new item
     * - If it's less than old min stack it
     */
    public void push(int value) {
        if (value < min()) {
            minStack.push(value);
        }
        super.push(value);
    }

    /**
     * Remove the last added item
     * - If it's same as Stack min then pop it from stackMin too
     */
    public Integer pop() {
        int value = super.pop();
        if (value == min()) {
            minStack.pop();
        }
        return value;
    }

    /**
     * Calculating min value
     */
    private int min() {
        if (this.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return minStack.peek();
    }

    public static void main(String[] args) {
        StackWithMin stackWithMin = new StackWithMin();
        stackWithMin.push(5);
        System.out.println("New stack min (1st 5 added) - " + stackWithMin.min());
        stackWithMin.push(3);
        System.out.println("New stack min should be 3 (last) - " + stackWithMin.min());
        stackWithMin.push(8);
        System.out.println("After 8 added, New stack min should be 3 (same) - " + stackWithMin.min());
        stackWithMin.pop(); // 8 removal
        System.out.println("8 is removed, New stack min should be 3 (no change) - " + stackWithMin.min());
        stackWithMin.pop(); // 3 removal
        System.out.println("3 is removed, New stack min should be 5 (changed) - " + stackWithMin.min());
        stackWithMin.push(2);
        System.out.println("New stack min should be 2 (newly added) - " + stackWithMin.min());
    }

}
