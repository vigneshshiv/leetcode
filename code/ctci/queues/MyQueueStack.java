package code.ctci.queues;

import java.util.Stack;

public class MyQueueStack<T> {

    private Stack<T> stackNewest, stackOldest;

    public MyQueueStack() {
        this.stackNewest = new Stack<>();
        this.stackOldest = new Stack<>();
    }

    /**
     * Push onto stackNewest, which always has the newest element on top
     */
    public void add(T value) {
        stackNewest.push(value);
    }

    /**
     * Remove the element from stackOldest
     */
    public T remove() {
        shiftStacks();
        return stackOldest.pop();
    }

    /**
     * Peek element from stackOldest
     */
    public T peek() {
        shiftStacks();
        return stackOldest.peek();
    }

    /**
     * Print Stack Newest
     */
    public void printStackNewestPeek() {
        if (stackNewest.isEmpty()){
            System.out.println("Stack Newest is Empty");
            return;
        }
        System.out.println("Stack Newest Last element added - " + stackNewest.peek());
    }

    /**
     * Print Stack Oldest
     */
    public void printStackOldestPeek() {
        if (stackOldest.isEmpty()){
            System.out.println("Stack Oldest is Empty");
            return;
        }
        System.out.println("Stack Oldest Last element added - " + stackOldest.peek());
    }

    /**
     * Move elements from stackNewest to stackOldest. So that we can do operations on stackOldest
     * - Item will be reversed and stackOldest acts like a queue
     */
    private void shiftStacks() {
        if (stackOldest.isEmpty()) {
            while (!stackNewest.isEmpty()) {
                stackOldest.push(stackNewest.pop());
            }
        }
    }

    public int size() {
        return stackNewest.size() + stackOldest.size();
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
        System.out.println("Stack Newest should be empty - Size - " + queueStack.stackNewest.size());
        System.out.println();
        //
        queueStack.add(2);
        System.out.println("Stack Newest 1 New element added - Last added item 2");
        queueStack.printStackNewestPeek();
        queueStack.printStackOldestPeek();
        System.out.println();
        //
        System.out.println("Remove operation performed, Stack removed item - " + queueStack.remove());
        System.out.println("Stack Newest Size - " + queueStack.stackNewest.size());
        System.out.println("Stack Oldest Size - " + queueStack.stackOldest.size());
        queueStack.printStackNewestPeek();
        queueStack.printStackOldestPeek();
    }

}
