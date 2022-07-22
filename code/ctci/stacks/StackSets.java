package code.ctci.stacks;

import java.util.ArrayList;
import java.util.List;

public class StackSets<T> {

    private static final int STACK_CAPACITY = 10;

    List<MyStack> stacks = new ArrayList<>();

    /**
     * Push
     */
    public void push(T value) {
        MyStack stack = getLastStack();
        if (stack != null && !stack.isFull()) {
            stack.push(value);
        } else {
            stack = new MyStack<>(STACK_CAPACITY);
            stack.push(value);
            stacks.add(stack);
        }
    }

    /**
     * Pop
     */
    public T pop() {
        MyStack stack = getLastStack();
        if (stack == null) {
            System.out.println("Last Stack is Empty");
            return null;
        }
        T data = (T) stack.pop();
        if (stack.isEmpty()) {
            stacks.remove(stacks.size() - 1);
        }
        return data;
    }

    /**
     * Peek
     */
    public T peek() {
        MyStack stack = getLastStack();
        return stack != null && stack.isEmpty() ? (T) stack.peek() : null;
    }

    /**
     * Get Last stack
     */
    private MyStack getLastStack() {
        return stacks.get(stacks.size() - 1);
    }

    public static void main(String[] args) {

    }

}
