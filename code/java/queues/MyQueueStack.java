package code.java.queues;

import java.util.Stack;

/**
 * https://leetcode.com/problems/implement-queue-using-stacks/
 */
public class MyQueueStack<T> {

    private Stack<T> front, rear;

    public MyQueueStack() {
        this.front = new Stack<>();
        this.rear = new Stack<>();
    }

    /**
     * Push onto stackNewest, which always has the newest element on top
     */
    public void add(T value) {
        rear.push(value);
    }

    /**
     * Remove the element from stackOldest
     */
    public T remove() {
        shiftStacks();
        return front.pop();
    }

    /**
     * Peek element from stackOldest
     */
    public T peek() {
        shiftStacks();
        return front.peek();
    }

    /**
     * Print Stack Newest
     */
    public void printStackNewestPeek() {
        if (front.isEmpty()){
            System.out.println("Stack Newest is Empty");
            return;
        }
        System.out.println("Stack Newest Last element added - " + front.peek());
    }

    /**
     * Print Stack Oldest
     */
    public void printStackOldestPeek() {
        if (rear.isEmpty()){
            System.out.println("Stack Oldest is Empty");
            return;
        }
        System.out.println("Stack Oldest Last element added - " + rear.peek());
    }

    /**
     * Move elements from stackNewest to stackOldest. So that we can do operations on stackOldest
     * - Item will be reversed and stackOldest acts like a queue
     */
    private void shiftStacks() {
        if (front.isEmpty()) {
            while (!rear.isEmpty()) {
                front.push(rear.pop());
            }
        }
    }

    public int size() {
        return front.size() + rear.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public static void main(String[] args) {
        MyQueueStack<Integer> queueStack = new MyQueueStack<>();
        queueStack.add(5);
        queueStack.add(10);
        System.out.println("Stack Newest 2 New elements added");
        queueStack.printStackNewestPeek();
        queueStack.printStackOldestPeek();
        System.out.println();
        //
        queueStack.add(7);
        System.out.println("Stack Newest 1 New element added - Last added item 7");
        queueStack.printStackNewestPeek();
        System.out.println();
        //
        System.out.println("Peek operation performed, Stack Oldest peek element should be 5 (first added) - ? " + queueStack.peek());
        System.out.println("Stack Newest should be empty - Size - " + queueStack.front.size());
        System.out.println();
        //
        queueStack.add(2);
        System.out.println("Stack Newest 1 New element added - Last added item 2");
        queueStack.printStackNewestPeek();
        queueStack.printStackOldestPeek();
        System.out.println();
        //
        System.out.println("Remove operation performed, Stack removed item - " + queueStack.remove());
        System.out.println("Stack Newest Size - " + queueStack.front.size());
        System.out.println("Stack Oldest Size - " + queueStack.rear.size());
        queueStack.printStackNewestPeek();
        queueStack.printStackOldestPeek();
    }

}
