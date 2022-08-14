package code.ctci.stacks;

import java.util.LinkedList;

public class MyStackQueue<T> {

    private LinkedList<T> front, rear;

    public MyStackQueue() {
        this.front = new LinkedList<>();
        this.rear = new LinkedList<>();
    }

    /**
     * Push onto queue front, which always has the oldest element on top
     */
    public void push(T value) {
        front.add(value);
    }

    /**
     * Remove the element from queue rear
     */
    public T pop() {
        shiftQueues();
        return rear.pop();
    }

    /**
     * Peek element from queue rear
     */
    public T peek() {
        shiftQueues();
        return rear.peek();
    }

    public int size() {
        return front.size() + rear.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    private void shiftQueues() {
        if (rear.isEmpty()) {
            while (!front.isEmpty()) {
                rear.add(front.pollLast());
            }
        }
    }

    /**
     * Print Queue front element
     */
    public void printQueueFrontPeek() {
        if (front.isEmpty()){
            System.out.println("Queue Front is Empty");
            return;
        }
        System.out.println("Queue Front Last element added - " + front.peek());
    }

    /**
     * Print Queue rear element
     */
    public void printQueueRearPeek() {
        if (rear.isEmpty()){
            System.out.println("Queue Rear is Empty");
            return;
        }
        System.out.println("Queue Rear Last element added - " + rear.peek());
    }

    public static void main(String[] args) {
        MyStackQueue<Integer> stackQueue = new MyStackQueue<>();
        stackQueue.push(2);
        stackQueue.push(3);
        System.out.println("Queue Front 2 New elements added");
        stackQueue.printQueueFrontPeek();
        stackQueue.printQueueRearPeek();
        System.out.println();
        //
        stackQueue.push(7);
        System.out.println("Queue Front 1 New element added - Last added item 7");
        stackQueue.printQueueFrontPeek();
        System.out.println();
        //
        System.out.println("Peek operation performed, Queue Rear peek element should be 7 (last added) - ? " + stackQueue.peek());
        System.out.println("Queue Front should be empty - Size - " + stackQueue.front.size());
        System.out.println();
        //
        stackQueue.push(5);
        System.out.println("Queue Front 1 New element added - Last added item 5");
        stackQueue.printQueueFrontPeek();
        stackQueue.printQueueRearPeek();
        System.out.println();
    }

}
