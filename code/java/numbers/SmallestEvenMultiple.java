package code.java.numbers;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/smallest-even-multiple/
 */
public class SmallestEvenMultiple {

    private static int smallestEvenMultipleOptimal(int n) {
        return n * (n % 2 + 1);
    }

    private static int smallestEvenMultipleByBits(int n) {
        return n << (n & 1);
    }

    private static int smallestEvenMultipleEasy(int n) {
        return (n & 1) == 0 ? n : 2 * n;
    }

    public static void main(String[] args) {
        BiConsumer<Integer, Integer> logger = (num, result) -> System.out.println("Num - " + num + ", Result - " + result);
        //
        int n = 3, result = smallestEvenMultipleOptimal(n);
        logger.accept(n, result);
        result = smallestEvenMultipleByBits(n);
        logger.accept(n, result);
        result = smallestEvenMultipleEasy(n);
        logger.accept(n, result);
        //
        n = 4; result = smallestEvenMultipleOptimal(n);
        logger.accept(n, result);
        result = smallestEvenMultipleByBits(n);
        logger.accept(n, result);
        result = smallestEvenMultipleEasy(n);
        logger.accept(n, result);
    }

}
