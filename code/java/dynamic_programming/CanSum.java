package code.java.dynamic_programming;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Write a function `canSum(targetSum, numbers)` that takes in a target and an array of numbers as arguments.
 *
 * The function should return a boolean indicating whether or not it is possible to generate the targetSum using numbers from the array.
 *
 * You may use an element of the array as many times as needed.
 *
 * You may assume that all input numbers are non-negative.
 */
public class CanSum {

    /**
     * Brute force solution
     *
     * Time complexity: O(n^m), where n is the length of the array, and m is the target sum
     * Space complexity: O(m) space
     */
    static boolean canSum(int targetSum, int[] numbers) {
        if (targetSum == 0) return true;
        if (targetSum < 0) return false;
        for (int num : numbers) {
            var remainder = targetSum - num;
            if (canSum(remainder, numbers)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Time complexity: O(n * m) time, where n is the length of the array, and m is the target sum
     * Space complexity: O(m) space
     */
    static boolean canSumMemo(int targetSum, int[] numbers, Map<Integer, Boolean> memo) {
        if (memo.containsKey(targetSum)) {
            return memo.get(targetSum);
        }
        if (targetSum == 0) return true;
        if (targetSum < 0) return false;
        for (int num : numbers) {
            var remainder = targetSum - num;
            if (canSum(remainder, numbers)) {
                memo.put(targetSum, true);
                return true;
            }
        }
        memo.put(targetSum, false);
        return false;
    }

    /**
     * Time complexity: O(n * m), where n is the targetSum, m is the number's length
     * Space complexity: O(m)
     */
    static boolean canSumTabulation(int targetSum, int[] numbers) {
        boolean[] table = new boolean[targetSum + 1];
        table[0] = true;
        for (int i = 0; i < table.length; i++) {
            if (table[i]) {
                for (int j = 0; j < numbers.length; j++) {
                    var index = i + numbers[j];
                    if (index <= targetSum) {
                        table[index] = true;
                    }
                }
            }
        }
        return table[targetSum];
    }

    public static void main(String[] args) {
        BiFunction<Integer, String, String> getMsg = (targetSum, numbers) -> "Target Sum - " + targetSum + ", Input - " + numbers;
        BiConsumer<String, Boolean> logger = (msg, result) -> System.out.println(msg + ": " + result);
        //
        /*
        logger.accept(getMsg.apply(7, "[2, 3]"), canSum(7, new int[]{2, 3})); // true
        logger.accept(getMsg.apply(7, "[5, 3, 4, 7]"), canSum(7, new int[]{5, 3, 4, 7})); // true
        logger.accept(getMsg.apply(7, "[2, 4]"), canSum(7, new int[]{2, 4})); // false
        logger.accept(getMsg.apply(8, "[2, 3, 5]"), canSum(8, new int[]{2, 3, 5})); // true
        logger.accept(getMsg.apply(300, "[7, 14]"), canSum(300, new int[]{7, 14})); // false
        */
        // Memoization
        /*
        logger.accept(getMsg.apply(7, "[2, 3]"), canSumMemo(7, new int[]{2, 3}, new HashMap<>())); // true
        logger.accept(getMsg.apply(7, "[5, 3, 4, 7]"), canSumMemo(7, new int[]{5, 3, 4, 7}, new HashMap<>())); // true
        logger.accept(getMsg.apply(7, "[2, 4]"), canSumMemo(7, new int[]{2, 4}, new HashMap<>())); // false
        logger.accept(getMsg.apply(8, "[2, 3, 5]"), canSumMemo(8, new int[]{2, 3, 5}, new HashMap<>())); // true
        logger.accept(getMsg.apply(300, "[7, 14]"), canSumMemo(300, new int[]{7, 14}, new HashMap<>())); // false
        */
        // Tabulation
        logger.accept(getMsg.apply(7, "[2, 3]"), canSumTabulation(7, new int[]{2, 3})); // true
        logger.accept(getMsg.apply(7, "[5, 3, 4, 7]"), canSumTabulation(7, new int[]{5, 3, 4, 7})); // true
        logger.accept(getMsg.apply(7, "[2, 4]"), canSumTabulation(7, new int[]{2, 4})); // false
        logger.accept(getMsg.apply(8, "[2, 3, 5]"), canSumTabulation(8, new int[]{2, 3, 5})); // true
        logger.accept(getMsg.apply(300, "[7, 14]"), canSum(300, new int[]{7, 14})); // false
    }

}
