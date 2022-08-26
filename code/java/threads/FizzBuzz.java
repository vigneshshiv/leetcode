package code.java.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * https://leetcode.com/problems/fizz-buzz/
 */
public class FizzBuzz {

    public static void fizzbuzz(int n) {
        Consumer<String> printer = System.out::println;
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) printer.accept("FizzBuzz");
            else if (i % 3 == 0) printer.accept("Fizz");
            else if (i % 5 == 0) printer.accept("Buzz");
            else printer.accept(String.valueOf(i));
        }
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    private static List<String> fizzBuzzStrings(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) list.add("FizzBuzz");
            else if (i % 3 == 0) list.add("Fizz");
            else if (i % 5 == 0) list.add("Buzz");
            else list.add(String.valueOf(i));
        }
        return list;
    }

    public static void main(String[] args) {
        // fizzbuzz(15);
        List<String> result = fizzBuzzStrings(15);
        System.out.println(result);
    }

}
