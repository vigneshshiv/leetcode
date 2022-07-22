package code.ctci.stacks;

import java.util.Arrays;

public class FixedArrayStack {

    private int numberOfStacks = 3;
    private int stackCapacity;
    private int[] values;
    private int[] sizes;

    public FixedArrayStack(int stackCapacity) {
        this.stackCapacity = stackCapacity;
        values = new int[numberOfStacks * stackCapacity];
        sizes = new int[numberOfStacks];
    }

    /**
     * Push
     */
    public void push(int stackNum, int value) {
        if (isFull(stackNum)) {
            System.out.println("Stack size is full for StackNumber - " + stackNum);
            return;
        }
        // Assign the value
        values[indexOfTop(stackNum)] = value;
        // Increase the size+1
        sizes[(stackNum - 1)]++;
    }

    /**
     * Pop
     */
    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            System.out.println("Stack is empty for StackNumber - " + stackNum);
            return -1;
        }
        int topIndex = indexOfTop(stackNum) + 1;
        int value = values[topIndex];
        values[topIndex] = 0; // Reset to 0
        sizes[(stackNum - 1)]--; // Decrease the size by 1
        return value;
    }

    /**
     * Peek
     */
    public int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            System.out.println("Stack is empty for StackNumber - " + stackNum);
            return -1;
        }
        return values[indexOfTop(stackNum) + 1];
    }

    /**
     * Check if Stack is Empty
     */
    public boolean isEmpty(int stackNum) {
        return sizes[(stackNum - 1)] == 0;
    }

    /**
     * Check if Stack is Full
     */
    public boolean isFull(int stackNum) {
        return sizes[(stackNum - 1)] == stackCapacity;
    }

    /**
     * Top of Index
     */
    public int indexOfTop(int stackNum) {
        int offset = stackNum * stackCapacity;
        int size = sizes[(stackNum - 1)];
        return offset - size - 1;
    }

    /**
     * Print stack data
     */
    public void printStack(int stackNum) {
        if (isEmpty(stackNum)) {
            System.out.println("Nothing to Print, since Stack is empty for StackNumber - " + stackNum);
            return;
        }
        int start = (stackNum * stackCapacity) - stackCapacity;
        int end = (stackNum * stackCapacity) - 1;
        for (int i = end; i >= start; i--) {
            System.out.print(values[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Array1 - [3,5,2,8,6] - Stack based, last added item will be accessed 1st (Eg: 6 is the 1st item in 0th index)
        // Array2 - [7,4,1]
        // Array3 - [9]
        int stackCapacity = 5;
        FixedArrayStack arrayStack = new FixedArrayStack(stackCapacity);
        // 1st Stack
        System.out.println("Array Stack size initial for 1st Stack (should be zero) - " + arrayStack.sizes[1]);
        arrayStack.printStack(1);
        arrayStack.push(1, 3);
        arrayStack.push(1, 5);
        arrayStack.push(1, 2);
        arrayStack.push(1, 8);
        arrayStack.push(1, 6);
        System.out.println("After 1st Stack is Full, printing elements");
        arrayStack.printStack(1);
        System.out.println("Printing Whole Array Size - " + Arrays.toString(arrayStack.sizes));
        System.out.println("Printing Whole Array - " + Arrays.toString(arrayStack.values));
        arrayStack.push(1, 7); // Test case for Negative case
        System.out.println("Peek 1st Stack element (first is 6) - " + (arrayStack.peek(1)));
        System.out.println("Pop 1st Stack element (6 is removed) - " + (arrayStack.pop(1)));
        // After Pop, size of 1st stack
        System.out.println("After 1 element removal, 1st Stack size (should be 4) - " + Arrays.toString(arrayStack.sizes));
        System.out.println("After 1 element removal, Current peek element (8) - " + arrayStack.peek(1));
        System.out.println("Printing Whole Array - " + Arrays.toString(arrayStack.values));
        // 2nd Stack
        arrayStack.printStack(2);
        arrayStack.push(2, 7);
        arrayStack.push(2, 4);
        arrayStack.push(2, 1);
        System.out.println("After 2nd Stack is filled with 3 elements, printing elements");
        arrayStack.printStack(2);
        System.out.println("Peek 2nd Stack element (first is 1) - " + (arrayStack.peek(2)));
        System.out.println("Pop 2nd Stack element (1 is removed) - " + (arrayStack.pop(2)));
        // After Pop, size of 2nd stack
        System.out.println("After 1 element removal, 2nd Stack size (should be 2) - " + Arrays.toString(arrayStack.sizes));
        System.out.println("After 1 element removal, Current peek element (4) - " + arrayStack.peek(2));
        // After 1 new element push 2nd stack
        arrayStack.push(2, 5);
        System.out.println("Peek 2nd Stack element (first is 5) - " + (arrayStack.peek(2)));
        System.out.println("Printing Whole Array - " + Arrays.toString(arrayStack.values));
        System.out.println("After 1 element added, 2nd Stack size (should be 3) - " + Arrays.toString(arrayStack.sizes));
        // 3rd Stack
        arrayStack.push(3, 9);
        System.out.println("After 1 element added, 3rd Stack size (should be 1) - " + Arrays.toString(arrayStack.sizes));
        System.out.println("Peek 3rd Stack element (first is 9) - " + (arrayStack.peek(3)));
        System.out.println("Printing Whole Array - " + Arrays.toString(arrayStack.values));
        System.out.println("Peek 3rd Stack element (first is 9) - " + (arrayStack.peek(3)));
        System.out.println("Pop 3rd Stack element (9 is removed) - " + (arrayStack.pop(3)));
        System.out.println("Printing Whole Array - " + Arrays.toString(arrayStack.values));
        System.out.println("After 1 element removal, 3rd Stack size (should be 0) - " + Arrays.toString(arrayStack.sizes));
    }

}
