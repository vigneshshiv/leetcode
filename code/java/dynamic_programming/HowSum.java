package code.java.dynamic_programming;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
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
        current.add(number);
        remainderResult.addAll(current);
        return remainderResult;
    };

    /**
     * Time complexity: O(n^m * m), where n is the length of the array, and m is the target sum
     * Space complexity: O(m)
     */
    private static List<Integer> targetSumCombinations(int targetSum, int[] numbers) {
        if (targetSum == 0) return emptyList.get();
        if (targetSum < 0) return null;
        for (int num : numbers) {
            var remainder = targetSum - num;
            var remainderResult = targetSumCombinations(remainder, numbers);
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
    private static List<Integer> targetSumCombinationsMemo(int targetSum, int[] numbers, Map<Integer, List<Integer>> memo) {
        if (memo.containsKey(targetSum)) {
            return memo.get(targetSum);
        }
        if (targetSum == 0) return emptyList.get();
        if (targetSum < 0) return null;
        for (int num : numbers) {
            var remainder = targetSum - num;
            var remainderResult = targetSumCombinations(remainder, numbers);
            if (Objects.nonNull(remainderResult)) {
                memo.put(targetSum, combiner.apply(remainderResult, num));
                return memo.get(targetSum);
            }
        }
        memo.put(targetSum, null);
        return null;
    }

    public static void main(String[] args) {
        BiFunction<Integer, String, String> getMsg = (targetSum, numbers) -> "Target Sum - " + targetSum + ", Input - " + numbers;
        BiConsumer<String, List<Integer>> logger = (msg, combination) ->
                System.out.println(msg + ": Result - " + (Objects.nonNull(combination) ? combination.toString() : null));
        //
        /*
        logger.accept(getMsg.apply(7, "[2, 3]"), targetSumCombinations(7, new int[]{2, 3})); // [3, 2, 2]
        logger.accept(getMsg.apply(7, "[5, 3, 4, 7]"), targetSumCombinations(7, new int[]{5, 3, 4, 7})); // [4, 3]
        logger.accept(getMsg.apply(7, "[2, 4]"), targetSumCombinations(7, new int[]{2, 4})); // null
        logger.accept(getMsg.apply(8, "[2, 3, 5]"), targetSumCombinations(8, new int[]{2, 3, 5})); // [2, 2, 2, 2]
        logger.accept(getMsg.apply(300, "[7, 14]"), targetSumCombinations(300, new int[]{7, 14})); // null
        */
        logger.accept(getMsg.apply(7, "[2, 3]"), targetSumCombinationsMemo(7, new int[]{2, 3}, new HashMap<>())); // [3, 2, 2]
        logger.accept(getMsg.apply(7, "[5, 3, 4, 7]"), targetSumCombinationsMemo(7, new int[]{5, 3, 4, 7}, new HashMap<>())); // [4, 3]
        logger.accept(getMsg.apply(7, "[2, 4]"), targetSumCombinationsMemo(7, new int[]{2, 4}, new HashMap<>())); // null
        logger.accept(getMsg.apply(8, "[2, 3, 5]"), targetSumCombinationsMemo(8, new int[]{2, 3, 5}, new HashMap<>())); // [2, 2, 2, 2]
        logger.accept(getMsg.apply(300, "[7, 14]"), targetSumCombinationsMemo(300, new int[]{7, 14}, new HashMap<>())); // null
    }

}
