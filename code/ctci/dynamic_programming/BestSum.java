package code.ctci.dynamic_programming;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Write a function `bestSum(targetSum, numbers)` that takes in a targetSum and an array of numbers as arguments.
 *
 * The function should return an array containing the shortest combination of numbers that add up to exactly the targetSum.
 *
 * If there is a tie for the shortest combination, you may return any one of the shortest.
 */
public class BestSum {

    private static Supplier<List<Integer>> emptyList = () -> new ArrayList<>();

    private static BiFunction<List<Integer>, Integer, List<Integer>> combiner = (remainderResult, number) -> {
        List<Integer> current = new ArrayList<>();
        current.addAll(remainderResult);
        current.add(number);
        return current;
    };

    /**
     * Time complexity: O(n^m * m), where n is the length of the array, and m is the target sum
     *
     * Space complexity: O(m^2), shortCombination array of length of M recursive call.
     *      Maximum stack depth is still M, However now, storing stack frame in array or arraylist
     */
    private static List<Integer> targetSumCombination(int targetSum, int[] numbers) {
        if (targetSum == 0) return emptyList.get();
        if (targetSum < 0) return null;
        List<Integer> shortestCombination = null;
        for (int num : numbers) {
            var remainder = targetSum - num;
            var remainderResult = targetSumCombination(remainder, numbers);
            if (Objects.nonNull(remainderResult)) {
                var combination = combiner.apply(remainderResult, num);
                if ((Objects.isNull(shortestCombination) || shortestCombination.isEmpty())
                        || combination.size() < shortestCombination.size()) {
                    shortestCombination = combination;
                }
            }
        }
        return shortestCombination;
    }

    /**
     * Time complexity: O(n * m^2), where n is the length of the array, and m is the target sum
     *
     * Space complexity: O(m^2), shortCombination array of length of M recursive call.
     *      Maximum stack depth is still M, However now, storing stack frame in array or arraylist
     */
    private static List<Integer> targetSumCombinationMemo(int targetSum, int[] numbers, Map<Integer, List<Integer>> memo) {
        if (targetSum == 0) return emptyList.get();
        if (targetSum < 0) return null;
        List<Integer> shortestCombination = null;
        for (int num : numbers) {
            var remainder = targetSum - num;
            var remainderResult = targetSumCombinationMemo(remainder, numbers, memo);
            if (Objects.nonNull(remainderResult)) {
                var combination = combiner.apply(remainderResult, num);
                if ((Objects.isNull(shortestCombination) || shortestCombination.isEmpty())
                        || combination.size() < shortestCombination.size()) {
                    shortestCombination = combination;
                }
            }
        }
        memo.put(targetSum, shortestCombination);
        return shortestCombination;
    }

    /**
     * Time complexity: O(n * m^2), where n is the length of the array, and m is the target sum
     *
     * Space complexity: O(m^2), shortCombination array of length of M recursive call.
     *      Maximum stack depth is still M, However now, storing stack frame in array or arraylist
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
                        var combinedResult = combiner.apply((List<Integer>) combination, numbers[j]);
                        if (Objects.isNull(table[index])
                                || (Objects.nonNull(table[index]) && combinedResult.size() < ((List<Integer>) table[index]).size())) {
                            table[index] = combinedResult;
                        }
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
        logger.accept(getMsg.apply(7, "[5, 3, 4, 7]"), targetSumCombination(7, new int[]{5, 3, 4, 7})); // [7]
        logger.accept(getMsg.apply(8, "[2, 3, 5]"), targetSumCombination(8, new int[]{2, 3, 5})); // [5, 3]
        logger.accept(getMsg.apply(8, "[1, 4, 5]"), targetSumCombination(8, new int[]{1, 4, 5})); // [4, 4]
        logger.accept(getMsg.apply(100, "[1, 2, 5, 25]"), targetSumCombination(100, new int[]{1, 2, 5, 25})); // null
        */
        // Memoization
        /*
        logger.accept(getMsg.apply(7, "[5, 3, 4, 7]"), targetSumCombinationMemo(7, new int[]{5, 3, 4, 7}, new HashMap<>())); // [7]
        logger.accept(getMsg.apply(8, "[2, 3, 5]"), targetSumCombinationMemo(8, new int[]{2, 3, 5}, new HashMap<>())); // [5, 3]
        logger.accept(getMsg.apply(8, "[1, 4, 5]"), targetSumCombinationMemo(8, new int[]{1, 4, 5}, new HashMap<>())); // [4, 4]
        logger.accept(getMsg.apply(100, "[5, 25]"), targetSumCombinationMemo(100, new int[]{5, 25}, new HashMap<>())); // [25, 25, 25, 25]
        */
        // Tabulation
        logger.accept(getMsg.apply(7, "[5, 3, 4, 7]"), targetSumCombinationTabulation(7, new int[]{5, 3, 4, 7})); // [7]
        logger.accept(getMsg.apply(8, "[2, 3, 5]"), targetSumCombinationTabulation(8, new int[]{2, 3, 5})); // [5, 3]
        logger.accept(getMsg.apply(8, "[1, 4, 5]"), targetSumCombinationTabulation(8, new int[]{1, 4, 5})); // [4, 4]
        logger.accept(getMsg.apply(100, "[5, 25]"), targetSumCombinationTabulation(100, new int[]{5, 25})); // [25, 25, 25, 25]
    }

}
