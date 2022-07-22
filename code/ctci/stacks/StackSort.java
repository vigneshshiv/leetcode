package code.ctci.stacks;

import java.util.Stack;

public class StackSort {

    /**
     * Stack Sorting with 2 stacks
     *
     * Time complexity: O(N^2) time
     * Space complexity: O(N) space
     */
    static Stack<Integer> sort(Stack<Integer> numbers) {
        Stack<Integer> sorted = new Stack<>();
        while (!numbers.isEmpty()) {
            int lastElement = numbers.pop();
            while (!sorted.isEmpty() && sorted.peek() > lastElement) {
                numbers.push(sorted.pop());
            }
            sorted.push(lastElement);
        }
        return sorted;
    }

    public static void main(String[] args) {
        Stack<Integer> numbers = new Stack<>();
        numbers.push(7);
        numbers.push(10);
        numbers.push(5);
        //
        Stack<Integer> sorted = sort(numbers);
        //
        System.out.println("Stack Elements are sorted");
        sorted.forEach(System.out::println); // Insertion order iteration
    }

}
