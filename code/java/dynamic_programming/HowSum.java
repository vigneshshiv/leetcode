package code.java.dynamic_programming;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Write a function `howSum(target, numbers)` that takes in a targetSum and an array of numbers as arguments.
 *
 * The function should return an array containing any combination of elements that add up to exactly the targetSum.
 * If there is no combination that adds up to the targetSum, then return null.
 *
 * If there are multiple combinations possible, you may return any single one.
 */
public class HowSum {

    private static Supplier<List<Integer>> emptyList = () -> new ArrayList<>();

    private static BiFunction<List<Integer>, Integer, List<Integer>> combiner = (remainderResult, number) -> {
        List<Integer> current = new ArrayList<>();
        current.addAll(remainderResult);
        current.add(number);
        return current;
    };

    /**
     * Time complexity: O(n^m * m), where n is the length of the array, and m is the target sum
     * Space complexity: O(m)
     */
    private static List<Integer> targetSumCombination(int targetSum, int[] numbers) {
        if (targetSum == 0) return emptyList.get();
        if (targetSum < 0) return null;
        for (int num : numbers) {
            var remainder = targetSum - num;
            var remainderResult = targetSumCombination(remainder, numbers);
            if (Objects.nonNull(remainderResult)) {
                return combiner.apply(remainderResult, num);
            }
        }
        return null;
    }

    /**
     * Time complexity: O(n * m^2), where n is the length of the array, and m is the target sum
     * Space complexity: O(m^2)
     */
    private static List<Integer> targetSumCombinationMemo(int targetSum, int[] numbers, Map<Integer, List<Integer>> memo) {
        if (memo.containsKey(targetSum)) {
            return memo.get(targetSum);
        }
        if (targetSum == 0) return emptyList.get();
        if (targetSum < 0) return null;
        for (int num : numbers) {
            var remainder = targetSum - num;
            var remainderResult = targetSumCombinationMemo(remainder, numbers, memo);
            if (Objects.nonNull(remainderResult)) {
                memo.put(targetSum, combiner.apply(remainderResult, num));
                return memo.get(targetSum);
            }
        }
        memo.put(targetSum, null);
        return null;
    }

    /**
     * Time complexity: O(n * m^2), where n is the length of the array, and m is the target sum
     * Space complexity: O(m^2)
     */
    private static List<Integer> targetSumCombinationTabulation(int targetSum, int[] numbers) {
        Object[] table = new Object[targetSum + 1];
        table[0] = emptyList.get();
        var i = 0;
        for (Object combination : table) {
            if (Objects.nonNull(combination)) {
                for (int j = 0; j < numbers.length; j++) {
                    var index = i + numbers[j];
                    if (index <= targetSum) {
                        table[index] = combiner.apply((List<Integer>) combination, numbers[j]);
                    }
                }
            }
            i++;
        }
        return (List<Integer>) table[targetSum];
    }

    public static void main(String[] args) {
        BiFunction<Integer, String, String> getMsg = (targetSum, numbers) -> "Target Sum - " + targetSum + ", Input - " + numbers;
        BiConsumer<String, List<Integer>> logger = (msg, combination) ->
                System.out.println(msg + ": Result - " + (Objects.nonNull(combination) ? combination.toString() : null));
        //
        /*
        logger.accept(getMsg.apply(7, "[2, 3]"), targetSumCombination(7, new int[]{2, 3})); // [3, 2, 2]
        logger.accept(getMsg.apply(7, "[5, 3, 4, 7]"), targetSumCombination(7, new int[]{5, 3, 4, 7})); // [4, 3]
        logger.accept(getMsg.apply(7, "[2, 4]"), targetSumCombination(7, new int[]{2, 4})); // null
        logger.accept(getMsg.apply(8, "[2, 3, 5]"), targetSumCombination(8, new int[]{2, 3, 5})); // [2, 2, 2, 2]
        logger.accept(getMsg.apply(300, "[7, 14]"), targetSumCombination(300, new int[]{7, 14})); // null
        */
        // Memoization
        /*
        logger.accept(getMsg.apply(7, "[2, 3]"), targetSumCombinationMemo(7, new int[]{2, 3}, new HashMap<>())); // [3, 2, 2]
        logger.accept(getMsg.apply(7, "[5, 3, 4, 7]"), targetSumCombinationMemo(7, new int[]{5, 3, 4, 7}, new HashMap<>())); // [4, 3]
        logger.accept(getMsg.apply(7, "[2, 4]"), targetSumCombinationMemo(7, new int[]{2, 4}, new HashMap<>())); // null
        logger.accept(getMsg.apply(8, "[2, 3, 5]"), targetSumCombinationMemo(8, new int[]{2, 3, 5}, new HashMap<>())); // [2, 2, 2, 2]
        logger.accept(getMsg.apply(300, "[7, 14]"), targetSumCombinationMemo(300, new int[]{7, 14}, new HashMap<>())); // null
        */
        // Tabulation
        logger.accept(getMsg.apply(7, "[2, 3]"), targetSumCombinationTabulation(7, new int[]{2, 3})); // [3, 2, 2]
        logger.accept(getMsg.apply(7, "[5, 3, 4, 7]"), targetSumCombinationTabulation(7, new int[]{3, 4})); // [4, 3]
        logger.accept(getMsg.apply(7, "[2, 4]"), targetSumCombinationTabulation(7, new int[]{2, 4})); // null
        logger.accept(getMsg.apply(8, "[2, 3, 5]"), targetSumCombinationTabulation(8, new int[]{2, 3, 5})); // [2, 2, 2, 2]
        logger.accept(getMsg.apply(6, "[2, 4, 6, 10]"), targetSumCombinationTabulation(6, new int[]{2, 4, 6, 10})); // [2, 2, 2, 2]
        // logger.accept(getMsg.apply(300, "[7, 14]"), targetSumCombinationTabulation(300, new int[]{7, 14})); // null
    }

}
