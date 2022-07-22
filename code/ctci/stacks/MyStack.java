package code.ctci.stacks;

public class MyStack<T> {

    private static class StaticNode<T> {
        private T data;
        private StaticNode<T> next;

        public StaticNode(T data) {
            this.data = data;
        }
    }

    private int capacity;
    private int size;
    private StaticNode<T> top;

    /**
     * Initialize with stack storage capacity
     */
    public MyStack(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Remove the item from top of the stack
     */
    public T pop() {
        if (isEmpty()) return null;
        // Item to be removed
        T item = top.data;
        top = top.next;
        return item;
    }

    /**
     * Add an item to the top of the stack
     */
    public void push(T item) {
        StaticNode<T> first = new StaticNode<T>(item);
        first.next = top;
        top = first;
    }

    /**
     * Return the item from the top of the stack
     */
    public T peek() {
        if (isEmpty()) return null;
        return top.data;
    }

    /**
     * Return true if and only if the stack is empty
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Is Stack is Full
     */
    public boolean isFull() {
        return size == capacity;
    }

}
