package code.java.queues;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueExample {

    public static void main(String[] args) {
        // Natural ordering
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        //
        priorityQueue.add("aby");
        priorityQueue.add("xyz");
        priorityQueue.add("123");
        priorityQueue.add("ngc");
        //
        System.out.println("Priority Queue - Initial values - " + priorityQueue);
        priorityQueue.offer("abc");
        priorityQueue.add("cde");
        System.out.println("Priority Queue - 1st Changes - " + priorityQueue);
        //
        System.out.println("Peek (123) - " + priorityQueue.peek() + ", Element - " + priorityQueue.element());
        System.out.println("Is Peek vs Element same? - " + (priorityQueue.peek() == priorityQueue.element()));
        //
        System.out.println("Poll (123) - " + priorityQueue.poll());
        System.out.println("Remove (abc) - " + priorityQueue.remove());
        System.out.println("Priority Queue - 2nd Changes - " + priorityQueue);
        // With Comparator
        Comparator<Integer> descendingOrder = (x, y) -> y.compareTo(x);
        PriorityQueue<Integer> priorityQueueNumbers = new PriorityQueue<>(descendingOrder);
        priorityQueueNumbers.add(4);
        priorityQueueNumbers.add(5);
        priorityQueueNumbers.add(2);
        priorityQueueNumbers.add(8);
        System.out.println("Priority Queue Numbers - Initial values - " + priorityQueueNumbers);
        priorityQueueNumbers.offer(7);
        System.out.println("Priority Queue Numbers - 1st changes - " + priorityQueueNumbers);
        System.out.println("Peek (2) - " + priorityQueueNumbers.peek());
        Arrays.sort(priorityQueueNumbers.toArray());
        System.out.println("Priority Queue after Sorted - " + priorityQueueNumbers);
    }

}
